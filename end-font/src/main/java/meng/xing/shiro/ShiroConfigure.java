package meng.xing.shiro;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfigure {
    /**
     * 根据配置的SecurityManager生成filter的工厂
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //开始配置路径与拦截器的对应
        Map<String,String> filterChainDefinitionMap = new LinkedMap();
        filterChainDefinitionMap.put("/users/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 根据后面的bean详细配置SecurityManager
     * 主要是身份认证的管理，缓存管理，cookie管理，
     * 所以在实际开发中我们主要是和SecurityManager进行打交道的
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置1
        securityManager.setSubjectFactory(subjectFactory());
        //配置2
        securityManager.setSessionManager(sessionManager());
        return  securityManager;
    }
    /**
     * 配置1
     *自定义的无状态的subject工厂
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        RestSubjectFactory restSubjectFactory = new RestSubjectFactory();
        return restSubjectFactory;
    }

    /**
     * 配置2
     * session管理器：
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        //sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }
}
