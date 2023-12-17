package com.projetrest;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.glassfish.jersey.servlet.ServletContainer;

public class AppMain {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();

            // Port Tomcat
            tomcat.setPort(8080);

            // Répertoire de base
            String appBase = ".";
            tomcat.setBaseDir(appBase);

            // Créer un contexte
            Context context = tomcat.addContext("", appBase);

            // Servlet de Jersey pour traiter les requêtes JAX-RS
            Tomcat.addServlet(context, "jersey", new org.glassfish.jersey.servlet.ServletContainer());
            context.addServletMappingDecoded("/api/*", "jersey");

            // Servlet par défaut pour gérer les requêtes statiques
            Tomcat.addServlet(context, "default", new DefaultServlet());
            context.addServletMappingDecoded("/*", "default");

            tomcat.start();

            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
