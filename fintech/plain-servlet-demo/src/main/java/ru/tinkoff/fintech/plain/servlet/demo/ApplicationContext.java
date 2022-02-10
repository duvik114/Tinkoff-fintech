package ru.tinkoff.fintech.plain.servlet.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import ru.tinkoff.fintech.plain.servlet.demo.controller.LecturerController;
import ru.tinkoff.fintech.plain.servlet.demo.controller.StudentController;

public class ApplicationContext {

    public static final ObjectMapper JACKSON = new ObjectMapper();


    public static void main(String[] args) throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // bootstraps Tomcat’s HTTP engine

        var context = tomcat.addContext("", null);
        var lecturerServlet = new LecturerController(JACKSON);
        var studentServlet = new StudentController(JACKSON);

        var lecturerWrapper = Tomcat.addServlet(context, "lecturer", lecturerServlet);
        var studentWrapper = Tomcat.addServlet(context, "student", studentServlet);

        studentWrapper.setLoadOnStartup(1); // start immediately
        studentWrapper.addMapping("/student");

        lecturerWrapper.setLoadOnStartup(1);
        lecturerWrapper.addMapping("/lecturer");

        tomcat.start();
    }
}
