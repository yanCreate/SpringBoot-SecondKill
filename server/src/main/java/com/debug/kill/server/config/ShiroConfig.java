package com.debug.kill.server.config;/**
 * Created by Administrator on 2019/7/2.
 */

import com.debug.kill.server.service.CustomRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro的通用化配置
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/2 17:54
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public CustomRealm customRealm(){
        CustomRealm realm=new CustomRealm();
        return realm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/to/login");
        bean.setUnauthorizedUrl("/unauth");

        Map<String, String> filterChainDefinitionMap=new HashMap<>();
        filterChainDefinitionMap.put("/to/login","anon");

        filterChainDefinitionMap.put("/kill/execute","authc");
        filterChainDefinitionMap.put("/item/detail","authc");

        filterChainDefinitionMap.put("/**","anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }


}




























