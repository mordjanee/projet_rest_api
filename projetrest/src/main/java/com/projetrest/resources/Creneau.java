package com.projetrest.resources;


public class Creneau {
    private String idSeance;
    private String jour;
    private String heureDebut;
    private String heureFin;
    private int salle;
    private String idCinema;


    public Creneau() {}

    public Creneau(String idSeance, String jour, String heureDebut, String heureFin, int salle, String idCinema) {
        this.idSeance = idSeance;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.idCinema = idCinema;
    }


    public String getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(String idSeance) {
        this.idSeance = idSeance;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }
}
