package com.projetrest.resources;
import java.util.Date;

public class Film {
    private String idFilm;
    private String titre;
    private double duree;
    private String langue;
    private boolean sousTitre;
    private String realisateur;
    private String acteursPrincipaux;
    private int ageMin;
    private Date dateDebut;
    private Date dateFin;
    private String idCreateur;


    public Film() {}


    public Film(String idFilm, String titre, double duree, String langue, boolean sousTitre,
                String realisateur, String acteursPrincipaux, int ageMin,
                Date dateDebut, Date dateFin, String idCreateur) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.duree = duree;
        this.langue = langue;
        this.sousTitre = sousTitre;
        this.realisateur = realisateur;
        this.acteursPrincipaux = acteursPrincipaux;
        this.ageMin = ageMin;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idCreateur = idCreateur;
    }


    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public boolean isSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(boolean sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getActeursPrincipaux() {
        return acteursPrincipaux;
    }

    public void setActeursPrincipaux(String acteursPrincipaux) {
        this.acteursPrincipaux = acteursPrincipaux;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(String idCreateur) {
        this.idCreateur = idCreateur;
    }
}

