package com.aplus.method;

import com.aplus.utils.MessageUtils;
import freemarker.template.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by lifang on 2015/1/19.
 */
public class MessageMethod implements TemplateMethodModel{

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
            String message = null;
            String code = arguments.get(0).toString();
            if (arguments.size() > 1) {
                Object[] args = arguments.subList(1, arguments.size()).toArray();
                message = MessageUtils.getMessage(code, args);
            }	 else {
                message = MessageUtils.getMessage(code);
            }
            return new SimpleScalar(message);
        }
        return "";
    }

}
