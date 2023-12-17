package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.projetrest.data.DatabaseUtil;
import com.projetrest.models.Createur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/createurs")
public class CreateurRessource {


    @GET
    @Path("/{idCreateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreateur(@PathParam("idCreateur") int idCreateur) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        Createur createur = null;

        try {
            String query = "SELECT * FROM createur WHERE idCreateur = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idCreateur);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                createur = new Createur();
                createur.setIdCreateur(resultSet.getString("idCreateur"));
                createur.setLogin(resultSet.getString("login"));
                createur.setPassword(resultSet.getString("password"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du createur").build();
        }

        if (createur != null) {
            return Response.ok(createur).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Createur introuvable").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCreateurs() {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        List<Createur> createurs = new ArrayList<>();

        try {
            String query = "SELECT * FROM createur";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Createur createur = new Createur();
                createur.setIdCreateur(resultSet.getString("idCreateur"));
                createur.setLogin(resultSet.getString("login"));
                createur.setPassword(resultSet.getString("password"));
                createurs.add(createur);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des createurs").build();
        }

        return Response.ok(createurs).build();
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCreateur(Createur createur) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "INSERT INTO createur (idCreateur, password, login) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, createur.getIdCreateur());
            statement.setString(2, createur.getLogin());
            statement.setString(3, createur.getPassword());

            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.status(Response.Status.CREATED).entity("Créateur ajouté avec succès").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de l'ajout du créateur").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout du créateur").build();
        }
    }

    // Méthode pour mettre à jour un créateur de film existant
    @PUT
    @Path("/{idCreateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCreateur(@PathParam("idCreateur") String idCreateur, Createur createur) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
    	try {
            String query = "UPDATE createur SET login = ?, password = ? WHERE idCreateur = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, createur.getLogin());
            statement.setString(2, createur.getPassword());
            statement.setString(3, idCreateur);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Createur mis à jour avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Createur introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du Createur").build();
        }
    }

    // Méthode pour supprimer un créateur de film
    @DELETE
    @Path("/{idCreateur}")
    public Response deleteCreateur(@PathParam("idCreateur") String idCreateur) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "DELETE FROM createur WHERE idCreateur = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idCreateur);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Createur supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Createur introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du createur").build();
        }
    }
}
