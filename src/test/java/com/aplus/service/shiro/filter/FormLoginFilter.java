package com.aplus.service.shiro.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by lifang on 2015/4/1.
 */
public class FormLoginFilter extends PathMatchingFilter{

    private Logger logger = LoggerFactory.getLogger(FormLoginFilter.class);

    /**
     * 进行请求的预处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("==========================");
        return super.preHandle(request, response);
    }


    /**
     * 执行完拦截器链之后正常返回后执行；
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        super.postHandle(request, response);
    }


    /**
     * 不管最后有没有异常，afterCompletion都会执行，完成如清理资源功能。
     * @param request
     * @param response
     * @param exception
     * @throws Exception
     */
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        super.afterCompletion(request, response, exception);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return super.onPreHandle(request, response, mappedValue);
    }


}
