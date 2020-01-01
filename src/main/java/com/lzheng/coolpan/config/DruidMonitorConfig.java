package com.lzheng.coolpan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @ClassName DruidMonitorConfig
 * @Author 刘正
 * @Date 2020/1/1 1:18
 * @Version 1.0
 * @Description:
 */

@Configuration
@PropertySource(value = {"classpath:DruidDataSource.properties"})
public class DruidMonitorConfig {

    @Value("${druid.monitorUser}")
    String monitorUser;
    @Value("${druid.monitorPassword}")
    String monitorPassword;

    /**
     * 注册ServletRegistrationBean
     * @return
     */
        @Bean(name = "dataSource")
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource druid(){
            return new DruidDataSource();
        }



    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow", "");//多个ip逗号隔开
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示
//        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", monitorUser);
        servletRegistrationBean.addInitParameter("loginPassword", monitorPassword);
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }


    /**
     * 注册FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
