package com.projetrest.models;

public class Createur {
    private String idCreateur;
    private String password;
    private String login;


    public Createur() {}


    public Createur(String idCreateur, String password, String login) {
        this.idCreateur = idCreateur;
        this.password = password;
        this.login = login;
    }


    public String getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(String idCreateur) {
        this.idCreateur = idCreateur;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

