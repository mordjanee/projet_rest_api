package com.projetrest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")  // Spécifie le préfixe de l'URL pour les ressources REST
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        //classes de ressource
        
        classes.add(com.projetrest.resources.CinemaRessource.class);
        classes.add(com.projetrest.resources.CreateurRessource.class);
        classes.add(com.projetrest.resources.CreneauSeanceRessource.class);
        classes.add(com.projetrest.resources.FilmResource.class);

        return classes;
    }
}
