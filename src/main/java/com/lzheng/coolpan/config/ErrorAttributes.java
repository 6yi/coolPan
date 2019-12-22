package com.lzheng.coolpan.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @ClassName ErrorAttributes
 * @Author 刘正
 * @Date 2019/12/20 11:02
 * @Version 1.0
 * @Description:
 */

@Configuration
public class ErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("Author","lzheng");
        return errorAttributes;
    }

}
