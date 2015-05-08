package com.aplus.listener;

import com.aplus.entity.*;
import com.aplus.exception.InitSystemException;
import com.aplus.params.Setting;
import com.aplus.params.XMLConfig;
import com.aplus.services.*;
import com.aplus.utils.CipherUtils;
import com.aplus.utils.SettingUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Spring容器启动后执行,初始化信息
 *
 * @author jerry
 *         2014/08/27
 */

@Component
public class InitSysListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(InitSysListener.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SubMenuService subMenuService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionSourceService permissionSourceService;

    private static String splitSymbol = "";

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Setting setting = SettingUtils.get();
            boolean boolInit = getBoolInit(setting);
            if (boolInit) {
                splitSymbol = getSplitSymbol(setting);
                if (StringUtils.isNotBlank(splitSymbol)) {
                    logger.info("===================================================");
                    logger.info("==============start init system data===============");
                    logger.info("===================================================");
                    initCompany(setting);
                    initAdmin(setting);
                    initMenu(setting);
                    initEmployeeType(setting);
                    initPermissionSource(setting);
                    initRole(setting);
                    logger.info("===================================================");
                    logger.info("===============end init system data==============");
                    logger.info("===================================================");
                }
            }
        }
    }

    /**
     * 获取是否开启初始化功能
     *
     * @param setting
     * @return
     */
    private boolean getBoolInit(Setting setting) {
        XMLConfig config = setting.getBoolInit();
        logger.info("Whether to enable the initialization:{}", config.isEnabled());
        return config.isEnabled();
    }

    /**
     * 初始化员工状态信息
     *
     * @param setting
     */
    private void initEmployeeType(Setting setting) {
        XMLConfig config = setting.getEmployeeType();
        logger.info("Init employee type : {}", config.isEnabled());
        for (Map<String, String> map : config.getLists()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue();
                MemberTypeEntity memberTypeEntity = memberTypeService.findByName(name);
                if (memberTypeEntity == null) {
                    memberTypeEntity = new MemberTypeEntity();
                }
                memberTypeEntity.setName(name);
                memberTypeEntity.setSort(Integer.valueOf(value));
                String companyNo = null;
                for (Map.Entry<String, String> mapCompany : setting.getCompany().getLists().get(0).entrySet()) {
                    companyNo = mapCompany.getKey();
                }
                CompanyEntity companyEntity = companyService.findByCompanyNo(companyNo);
                memberTypeEntity.setCompanyId(companyEntity.getId());
                memberTypeService.merge(memberTypeEntity);
            }
        }
    }

    /**
     * 获取XML内容分隔符
     *
     * @param setting
     */
    private String getSplitSymbol(Setting setting) {
        XMLConfig config = setting.getSplit();
        logger.info("get xml split sybmol : {}", config.isEnabled());
        if (config.isEnabled()) {
            return StringUtils.trim(config.getValue());
        }
        return null;
    }

    /**
     * 初始化公司
     *
     * @param setting
     */
    private void initCompany(Setting setting) {
        XMLConfig config = setting.getCompany();
        logger.info("init company : {}", config.isEnabled());

        if(!config.isEnabled()) return;

        for (Map<String, String> map : config.getLists()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String companyNo = entry.getKey();
                String[] values = entry.getValue().split(splitSymbol);
                CompanyEntity company = companyService.findByCompanyNo(companyNo);
                if (company == null) {
                    company = new CompanyEntity();
                }
                company.setCompanyNo(companyNo);
                company.setName(values[0]);
                company.setFullName(values[1]);
                company.setLogo(values[2]);
                companyService.merge(company);
            }
        }
    }

    /**
     * 初始化管理员信息
     */
    @Transactional
    private void initAdmin(Setting setting) {
        XMLConfig config = setting.getAdmin();
        logger.info("int system : {}", config.isEnabled());

        if(!config.isEnabled()) return;

        for (Map<String, String> map : config.getLists()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                AdminEntity admin = adminService.findByUsername(entry.getKey());
                String[] values = entry.getValue().split(splitSymbol);
                String password = values[0];
                String companyNo = values[1];
                CompanyEntity company = companyService.findByCompanyNo(companyNo);
                if (company == null) {
                    logger.debug("Initialization information failed");
                    throw new InitSystemException("Initialization information failed");
                }
                if (admin == null) {
                    admin = new AdminEntity();
                }
                admin.setEmail("----");
                admin.setPassword(CipherUtils.getTime64MD5(password).toCharArray());
                admin.setCompanyId(company.getId());
                admin.setUsername(entry.getKey().trim());
                admin.setIsLocked(false);

                adminService.merge(admin);
            }
        }
    }



    private void initRole(Setting setting){
        XMLConfig config = setting.getRole();
        XMLConfig adminConfig = setting.getAdmin();
        if(!config.isEnabled() && !adminConfig.isEnabled()) return;

        for(Map<String, String> map : config.getLists()){
            List<String> permissionSources =  new LinkedList<String>();
            for(Map.Entry<String, String> entry : map.entrySet()){
                String value = entry.getValue();
                permissionSources.add(value);
            }

            RoleEntity roleEntity = roleService.findByName(config.getValue());
            if(roleEntity == null) {
                roleEntity = new RoleEntity();
                Set<AdminEntity> adminEntitySet = new HashSet<AdminEntity>();
                for (Map<String, String> adminMap : adminConfig.getLists()) {
                    for (Map.Entry<String, String> adminEntry : adminMap.entrySet()) {
                        AdminEntity adminEntity = adminService.findByName(adminEntry.getKey());
                        adminEntitySet.add(adminEntity);
                    }
                }
                roleEntity.setAdmins(adminEntitySet);
                roleEntity.setName(config.getValue());
                roleEntity.setAuthorities(permissionSources);
                roleService.merge(roleEntity);
            }
        }
    }


    /**
     * 初始化功能权限
     * @param setting
     */
    private void initPermissionSource(Setting setting){
        XMLConfig config = setting.getPermissionSource();
        logger.info("INIT SYSTEM MENU:{}", config.isEnabled());

        if(!config.isEnabled()) return;

        for (Map<String, String> map : config.getLists()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String name = entry.getKey();
                String[] params = entry.getValue().split(":");
                PermissionSourceEntity permissionSourceEntity = permissionSourceService.findBySubjectAndSourceAndAction(params[0], params[1], params[2]);
                if(permissionSourceEntity == null){
                    permissionSourceEntity = new PermissionSourceEntity(name, params[0], params[1], params[2]);
                }else{
                    permissionSourceEntity.setName(name);
                }
                permissionSourceService.merge(permissionSourceEntity);
            }
        }
    }



    /**
     * 初始化菜单
     *
     * @param setting
     */
    @Transactional
    private void initMenu(Setting setting) {
        XMLConfig config = setting.getMenu();
        logger.info("INIT SYSTEM MENU:{}", config.isEnabled());

        if(!config.isEnabled()) return;

        for (Map<String, String> map : config.getLists()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                MenuEntity menu = menuService.findByName(entry.getKey());
                String value = entry.getValue();
                String[] sub = value.split(splitSymbol);
                if (menu == null) {
                    Set<SubMenuEntity> subMenus = new HashSet<SubMenuEntity>();
                    menu = new MenuEntity();
                    menu.setName(entry.getKey());
                    SubMenuEntity sm = new SubMenuEntity();
                    sm.setName(sub[0].trim());
                    sm.setMenu(menu);
                    sm.setPath(getPathSubfix(sub[1], setting));
                    subMenus.add(sm);
                    menu.setSubMenus(subMenus);
                    menuService.persist(menu);
                } else {
                    SubMenuEntity subMenu = subMenuService.findByName(sub[0]);
                    if (subMenu == null) {
                        SubMenuEntity sm = new SubMenuEntity();
                        sm.setName(sub[0]);
                        sm.setPath(getPathSubfix(sub[1], setting));
                        sm.setMenu(menu);
                        menu.getSubMenus().add(sm);
                        menuService.merge(menu);
                    }
                }
            }
        }
    }

    /**
     * 获取文件后缀
     *
     * @param path
     * @param setting
     * @return
     */
    private String getPathSubfix(String path, Setting setting) {
        XMLConfig config = setting.getSubfix();
        if (config.isEnabled()) {
            if (!path.endsWith(config.getValue())) {
                return StringUtils.trim(path) + config.getValue();
            }
        }
        return StringUtils.trim(path);
    }
}
