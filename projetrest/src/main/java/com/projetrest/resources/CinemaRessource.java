package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.projetrest.data.DatabaseUtil;
import com.projetrest.models.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/cinemas")
public class CinemaRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCinemas() {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        List<Cinema> cinemas = new ArrayList<>();

        try {
            String query = "SELECT * FROM cinema";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Cinema cinema = new Cinema();
            	cinema.setIdCinema(resultSet.getString("idCinema"));
            	cinema.setVille(resultSet.getString("ville"));
            	cinema.setAdresse(resultSet.getString("adresse"));
            	cinemas.add(cinema);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des cinemas").build();
        }

        return Response.ok(cinemas).build();
    }

    @GET
    @Path("/{idCinema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCinemaById(@PathParam("idCinema") String idCinema) {
    	Connection conn = DatabaseUtil.getConnection();
        Cinema cinema = null;

        try {
            String query = "SELECT * FROM cinema WHERE idCinema = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idCinema);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	cinema = new Cinema();
            	cinema.setIdCinema(idCinema);
            	cinema.setVille(resultSet.getString("ville"));
            	cinema.setAdresse(resultSet.getString("adresse"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du cinema").build();
        }

        if (cinema != null) {
            return Response.ok(cinema).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cinema introuvable").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCinema(Cinema cinema) {
    	Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "INSERT INTO cinema (idCinema, ville, adresse) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cinema.getIdCinema());
            statement.setString(2, cinema.getVille());
            statement.setString(3, cinema.getAdresse());

            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.status(Response.Status.CREATED).entity("Cinema ajouté avec succès").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de l'ajout du cinema").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout du cinema").build();
        }
    }

    @PUT
    @Path("/{idCinema}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCinema(@PathParam("idCinema") String idCinema, Cinema cinema) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
    	try {
            String query = "UPDATE cinema SET ville = ?, adresse = ? WHERE idCinema = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cinema.getVille());
            statement.setString(2, cinema.getAdresse());
            statement.setString(3, idCinema);
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

    @DELETE
    @Path("/{idCinema}")
    public Response deleteCinema(@PathParam("idCinema") String idCinema) {
    	// Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        
        try {
            String query = "DELETE FROM createur WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idCinema);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Cinema supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Cinema introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du cinema").build();
        }
    }
}
