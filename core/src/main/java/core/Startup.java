package core;

import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoader;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class Startup implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

        DefaultWebEnvironment webEnvironment = new DefaultWebEnvironment();
        webEnvironment.setSecurityManager(webSecurityManager);
        webEnvironment.setFilterChainResolver(new PathMatchingFilterChainResolver());
        webEnvironment.setServletContext(servletContext);

        servletContext.setAttribute(EnvironmentLoader.ENVIRONMENT_ATTRIBUTE_KEY, webEnvironment);

        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", ShiroFilter.class);
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
