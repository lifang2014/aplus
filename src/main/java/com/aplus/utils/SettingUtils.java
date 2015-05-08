package com.aplus.utils;

import com.aplus.params.CommonAttributes;
import com.aplus.params.EnumConverter;
import com.aplus.params.Setting;
import com.aplus.params.XMLConfig;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class SettingUtils {

    private static Logger logger = LoggerFactory.getLogger(SettingUtils.class);

    private static final CacheManager cacheManager = CacheManager.create();

    private static final BeanUtilsBean beanUtils;

    private static Setting setting;

    static {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
            @Override
            public String convert(Object value) {
                if (value != null) {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null) {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray() && type.getComponentType().isEnum()) {
                        if (super.lookup(type) == null) {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String value, Class clazz) {
                if (clazz.isEnum() && super.lookup(clazz) == null) {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String[] values, Class clazz) {
                if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Object value, Class targetType) {
                if (super.lookup(targetType) == null) {
                    if (targetType.isEnum()) {
                        super.register(new EnumConverter(targetType), targetType);
                    } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
                        ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtilsBean.register(dateConverter, Date.class);
        beanUtils = new BeanUtilsBean(convertUtilsBean);
    }

    private SettingUtils() {
    }

    /**
     * 获取文件
     *
     * @return
     * @throws java.io.IOException
     */
    private static File getFile() throws IOException {
        return new ClassPathResource(CommonAttributes.M_SETTING_XML_PATH).getFile();
    }

    public synchronized static Setting get() {
        Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
        net.sf.ehcache.Element cacheElement = cache.get(Setting.CACHE_KEY);
        if (cacheElement != null) {
            setting = (Setting) cacheElement.getObjectValue();
        } else {
            setting = new Setting();
            try {
                File file = getFile();
                Document document = new SAXReader().read(file);
                List<Element> elements = document.selectNodes(CommonAttributes.M_SETTING_XML_ELEMENT);
                for (Element element : elements) {
                    XMLConfig config = new XMLConfig();
                    List<Element> childElements = element.selectNodes(CommonAttributes.M_SETTING_XML_CHLID_ELEMENT_MAP);
                    String name = element.attributeValue(CommonAttributes.M_SETTING_XML_NODE_NAME);
                    String enabled = element.attributeValue(CommonAttributes.M_SETTING_XML_NODE_ENABLED);
                    String rootValue = element.attributeValue(CommonAttributes.M_SETTING_XML_NODE_VALUE);
                    config.setName(name);
                    config.setEnabled(Boolean.valueOf(enabled));
                    config.setValue(rootValue);
                    if (childElements.isEmpty()) {//不存在子节点
                        String value = element.attributeValue(CommonAttributes.M_SETTING_XML_NODE_VALUE);
                        config.setValue(value);
                    } else {//存在子节点
                        List<Map<String, String>> lists = new LinkedList<Map<String, String>>();
                        for (Element ele : childElements) {
                            Map<String, String> map = new HashMap<String, String>();
                            String key = ele.attributeValue(CommonAttributes.M_SETTING_XML_NODE_KEY);
                            String value = ele.attributeValue(CommonAttributes.M_SETTING_XML_NODE_VALUE);
                            map.put(key, value);
                            lists.add(map);
                        }
                        config.setLists(lists);
                    }
                    if (StringUtils.isBlank(config.getName())) {
                        logger.warn("setting.xml configuration error,missing attribute:", CommonAttributes.M_SETTING_XML_NODE_NAME);
                        throw new Exception("setting.xml configuration error,missing attribute:" + CommonAttributes.M_SETTING_XML_NODE_NAME);
                    }
                    beanUtils.setProperty(setting, config.getName(), config);
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
            cache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, setting));
        }
        return setting;
    }

    @SuppressWarnings("unchecked")
    public static void set(Setting setting) {
        try {
            File file = getFile();
            Document document = new SAXReader().read(file);
            List<Element> elements = document.selectNodes(CommonAttributes.M_SETTING_XML_ELEMENT);
            for (Element element : elements) {
                try {
                    String name = element.attributeValue(CommonAttributes.M_SETTING_XML_NODE_NAME);
                    String value = beanUtils.getProperty(setting, name);
                    Attribute attribute = element.attribute(CommonAttributes.M_SETTING_XML_NODE_VALUE);
                    attribute.setValue(value);
                } catch (IllegalAccessException e) {
                    logger.warn(e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.warn(e.getMessage());
                } catch (NoSuchMethodException e) {
                    logger.warn(e.getMessage());
                }
            }

            FileOutputStream fileOutputStream = null;
            XMLWriter xmlWriter = null;
            try {
                OutputFormat outputFormat = OutputFormat.createPrettyPrint();
                outputFormat.setEncoding("UTF-8");
                outputFormat.setIndent(true);
                outputFormat.setIndent("	");
                outputFormat.setNewlines(true);
                fileOutputStream = new FileOutputStream(file);
                xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
                xmlWriter.write(document);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (xmlWriter != null) xmlWriter.close();
                } catch (IOException e) {
                    logger.warn(e.getMessage());
                }
                IOUtils.closeQuietly(fileOutputStream);
            }
            Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
            cache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, setting));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}