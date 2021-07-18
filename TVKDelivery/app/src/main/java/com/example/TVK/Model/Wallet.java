package com.example.TVK.Model;

public class Wallet {
    private int id;
    private int idUser;
    private String type;
    private String isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Wallet(int id, int idUser, String type, String isActive) {
        this.id = id;
        this.idUser = idUser;
        this.type = type;
        this.isActive = isActive;
    }
}
