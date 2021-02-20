package com.bs.bs_system.common.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /*
            anon:无需认证可以访问
            authc:必须认证
            user:必须拥有记住我功能
            perms:拥有对某个资源的权限
            role:拥有某个角色权限
         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/index", "authc");
        filterMap.put("system/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login");
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象，自定义(1)
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }


}
