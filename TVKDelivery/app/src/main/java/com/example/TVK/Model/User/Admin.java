package com.example.TVK.Model.User;

public class Admin extends User {
    private static Admin instance;

    public Admin()
    {

    }
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public Admin(int idUser, String fullName, String gender, String phone, String address, String email, String userName, String passWord, String activationCode, String status, String resetPassword, String typeOfUser, int isReceiveNotification) {
        super(idUser, fullName, gender, phone, address, email, userName, passWord, activationCode, status, resetPassword, typeOfUser, isReceiveNotification);
    }
}
