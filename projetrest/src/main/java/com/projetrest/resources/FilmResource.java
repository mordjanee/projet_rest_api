package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.projetrest.data.DatabaseUtil;
import com.projetrest.models.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/films")
public class FilmResource {

    @GET
    @Path("/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilm(@PathParam("idFilm") int idFilm) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        Film film = null;

        try {
            String query = "SELECT * FROM film WHERE idFilm = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idFilm);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                film = new Film();
                film.setIdFilm(resultSet.getString("idFilm"));
                film.setTitre(resultSet.getString("titre"));
                film.setDuree(resultSet.getDouble("duree"));
                film.setLangue(resultSet.getString("langue"));
                film.setSousTitre(resultSet.getBoolean("sousTitre"));
                film.setRealisateur(resultSet.getString("realisateur"));
                film.setActeursPrincipaux(resultSet.getString("acteursPrincipaux"));
                film.setAgeMin(resultSet.getInt("ageMin"));
                film.setDateDebut(resultSet.getDate("dateDebut"));
                film.setDateFin(resultSet.getDate("dateFin"));
                film.setIdCreateur(resultSet.getString("idCreateur"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du film").build();
        }

        if (film != null) {
            return Response.ok(film).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        List<Film> films = new ArrayList<>();

        try {
            String query = "SELECT * FROM film";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Film film = new Film();
                film.setIdFilm(resultSet.getString("idFilm"));
                film.setTitre(resultSet.getString("titre"));
                film.setDuree(resultSet.getDouble("duree"));
                film.setLangue(resultSet.getString("langue"));
                film.setSousTitre(resultSet.getBoolean("sousTitre"));
                film.setRealisateur(resultSet.getString("realisateur"));
                film.setActeursPrincipaux(resultSet.getString("acteursPrincipaux"));
                film.setAgeMin(resultSet.getInt("ageMin"));
                film.setDateDebut(resultSet.getDate("dateDebut"));
                film.setDateFin(resultSet.getDate("dateFin"));
                film.setIdCreateur(resultSet.getString("idCreateur"));
                films.add(film);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des films").build();
        }

        return Response.ok(films).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFilm(Film film) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            String query = "INSERT INTO film (idFilm, titre, duree, langue, sousTitre, realisateur, acteursPrincipaux, ageMin, dateDebut, dateFin, idCreateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, film.getIdFilm());
            statement.setString(2, film.getTitre());
            statement.setDouble(3, film.getDuree());
            statement.setString(4, film.getLangue());
            statement.setBoolean(5, film.getSousTitre());
            statement.setString(6, film.getRealisateur());
            statement.setString(7, film.getActeursPrincipaux());
            statement.setInt(8, film.getAgeMin());
            statement.setDate(9, film.getDateDebut());
            statement.setDate(10, film.getDateFin());
            statement.setString(11, film.getIdCreateur());

            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.status(Response.Status.CREATED).entity("Film créé avec succès").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de la création du film").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création du film").build();
        }
    }

    @PUT
    @Path("/{filmId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("filmId") int filmId, Film film) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            String query = "UPDATE film SET titre = ?, duree = ? WHERE idFilm = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, film.getTitre());
            statement.setDouble(2, film.getDuree());
            statement.setInt(3, filmId);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Film mis à jour avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du film").build();
        }
    }

    @DELETE
    @Path("/{filmId}")
    public Response deleteFilm(@PathParam("filmId") int filmId) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            String query = "DELETE FROM film WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, filmId);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Film supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du film").build();
        }
    }
}
