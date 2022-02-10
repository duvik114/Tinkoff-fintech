package ru.tinkoff.fintech.spring.demo;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.tinkoff.fintech.spring.demo.configuration.ApplicationConfiguration;

import javax.servlet.ServletContext;

public class Application {

    public static void main(String[] args) throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        var tomcatCtx = tomcat.addContext("", null);
        var springCtx = springCtx(tomcatCtx.getServletContext());

        var dispatcherServlet = Tomcat.addServlet(tomcatCtx, "springDispatcher", new DispatcherServlet(springCtx));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/*");
        tomcat.start();
    }

    private static WebApplicationContext springCtx(ServletContext servletContext) {
        var springCtx = new AnnotationConfigWebApplicationContext();
        springCtx.register(ApplicationConfiguration.class);
        springCtx.setServletContext(servletContext);
        springCtx.registerShutdownHook();
        springCtx.refresh();
        return springCtx;
    }
}
