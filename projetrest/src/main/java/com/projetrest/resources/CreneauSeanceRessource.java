package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/creneaux")
public class CreneauSeanceRessource {

    // Liste de Creneau pour simuler une base de données en mémoire
    private static List<Creneau> creneaux = new ArrayList<>();

    // Méthode pour récupérer tous les créneaux
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Creneau> getAllCreneaux() {
        return creneaux;
    }

    // Méthode pour récupérer un créneau par son ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Creneau getCreneauById(@PathParam("id") String id) {
        // Implémentez la logique pour récupérer un créneau par son ID
        for (Creneau creneau : creneaux) {
            if (creneau.getIdSeance().equals(id)) {
                return creneau;
            }
        }
        return null;
    }

    // Méthode pour créer un nouveau créneau
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCreneau(Creneau creneau) {
        // Implémentez la logique pour créer un nouveau créneau
        creneaux.add(creneau);
    }

    // Méthode pour mettre à jour un créneau existant
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCreneau(@PathParam("id") String id, Creneau creneau) {
        // Implémentez la logique pour mettre à jour un créneau
        for (int i = 0; i < creneaux.size(); i++) {
            if (creneaux.get(i).getIdSeance().equals(id)) {
                creneaux.set(i, creneau);
                return;
            }
        }
    }

    // Méthode pour supprimer un créneau
    @DELETE
    @Path("/{id}")
    public void deleteCreneau(@PathParam("id") String id) {
        // Implémentez la logique pour supprimer un créneau
        creneaux.removeIf(creneau -> creneau.getIdSeance().equals(id));
    }
}
