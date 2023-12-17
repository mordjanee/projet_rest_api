package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.projetrest.data.DatabaseUtil;
import com.projetrest.models.Creneau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/creneaux")
public class CreneauSeanceRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCreneaux() {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        List<Creneau> creneaux = new ArrayList<>();

        try {
            String query = "SELECT * FROM creneau_seance";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Creneau creneau = new Creneau();
            	creneau.setIdSeance(resultSet.getString("idSeance"));
            	creneau.setJour(resultSet.getString("jour"));
            	creneau.setHeureDebut(resultSet.getString("heureDebut"));
            	creneau.setHeureFin(resultSet.getString("heureFin"));
            	creneau.setSalle(resultSet.getInt("salle"));
            	creneau.setIdCinema(resultSet.getString("idCinema"));
            	
            	creneaux.add(creneau);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des creneaux").build();
        }

        return Response.ok(creneaux).build();
    }

    @GET
    @Path("/{idSeance}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreneauById(@PathParam("idSeance") String idSeance) {

    	Connection conn = DatabaseUtil.getConnection();
        Creneau creneau = null;

        try {
            String query = "SELECT * FROM creneau_seance WHERE idSeance = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idSeance);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	creneau = new Creneau();
            	creneau.setIdSeance(idSeance);
            	creneau.setJour(resultSet.getString("jour"));
            	creneau.setHeureDebut(resultSet.getString("heureDebut"));
            	creneau.setHeureFin(resultSet.getString("heureFin"));
            	creneau.setSalle(resultSet.getInt("salle"));
            	creneau.setIdCinema(resultSet.getString("idCinema"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du creneau").build();
        }

        if (creneau != null) {
            return Response.ok(creneau).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Creneau introuvable").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCreneau(Creneau creneau) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "INSERT INTO creneau (idSeance, jour, heureDebut, heureFin, salle, idCinema) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, creneau.getIdSeance());
            statement.setString(2, creneau.getJour());
            statement.setString(3, creneau.getHeureDebut());
            statement.setString(4, creneau.getHeureFin());
            statement.setInt(5, creneau.getSalle());
            statement.setString(6, creneau.getIdCinema());

            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.status(Response.Status.CREATED).entity("Creneau ajouté avec succès").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de l'ajout du creneau").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout du creneau").build();
        }
    }

    @PUT
    @Path("/{idSeance}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCreneau(@PathParam("idSeance") String idSeance, Creneau creneau) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
    	try {
            String query = "UPDATE createur SET jour = ?, heureDebut = ?, heureFin = ? WHERE idSeance = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, creneau.getJour());
            statement.setString(2, creneau.getHeureDebut());
            statement.setString(3, creneau.getHeureFin());
            statement.setString(4, idSeance);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Creneau mis à jour avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Creneau introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du creneau").build();
        }
    }

    @DELETE
    @Path("/{idSeance}")
    public Response deleteCreneau(@PathParam("idSeance") String idSeance) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "DELETE FROM creneau WHERE idSeance = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idSeance);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Creneau supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Creneau introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du creneau").build();
        }
    }
}
