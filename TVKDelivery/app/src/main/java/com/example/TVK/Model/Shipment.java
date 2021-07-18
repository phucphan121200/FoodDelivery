package com.example.TVK.Model;

import java.util.Date;

public class Shipment {
    private int id;
    private int idDriver;
    private float sumOfMass;
    private int countOfOrder;
    private Date startTime;
    private Date endTime;
    private String Status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public float getSumOfMass() {
        return sumOfMass;
    }

    public void setSumOfMass(float sumOfMass) {
        this.sumOfMass = sumOfMass;
    }

    public int getCountOfOrder() {
        return countOfOrder;
    }

    public void setCountOfOrder(int countOfOrder) {
        this.countOfOrder = countOfOrder;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Shipment(int id, int idDriver, float sumOfMass, int countOfOrder, Date startTime, Date endTime, String status) {
        this.id = id;
        this.idDriver = idDriver;
        this.sumOfMass = sumOfMass;
        this.countOfOrder = countOfOrder;
        this.startTime = startTime;
        this.endTime = endTime;
        Status = status;
    }
}
