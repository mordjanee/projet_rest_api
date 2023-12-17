package com.projetrest.models;

public class Cinema {
    private String idCinema;
    private String ville;
    private String adresse;


    public Cinema() {}


    public Cinema(String idCinema, String ville, String adresse) {
        this.idCinema = idCinema;
        this.ville = ville;
        this.adresse = adresse;
    }


    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

