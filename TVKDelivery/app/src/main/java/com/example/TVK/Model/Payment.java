package com.example.TVK.Model;

public class Payment {
    private  int id;
    private String typePayment;
    private String paymentAmount;
    private String statusPayment;
    private int idWallet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
    }

    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    public Payment(int id, String typePayment, String paymentAmount, String statusPayment, int idWallet) {
        this.id = id;
        this.typePayment = typePayment;
        this.paymentAmount = paymentAmount;
        this.statusPayment = statusPayment;
        this.idWallet = idWallet;
    }

}
