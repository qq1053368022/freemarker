package com.shiro.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager()  {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(new MyRealm());
        defaultSecurityManager.setCacheManager(new MemoryConstrainedCacheManager());
        return defaultSecurityManager;
    }
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    @Bean
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean(SecurityManager securityManager) throws Exception {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addInitParameter("tragetFilterLifecycle", "true");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/onLogin");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/img/**", "anon");
        map.put("/index", "anon");
        map.put("/onLogin","anon");
        map.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        filterRegistrationBean.setFilter((Filter)shiroFilterFactoryBean.getObject());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }
}
