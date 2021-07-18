package com.example.TVK.Model.User;

public class UserFactory {

    public UserFactory()
    {

    }

    public static final IUser getUser(String usertype)
    {
        switch (usertype)
        {
            case "ADMIN":
                return Admin.getInstance();
            case "CUSTOMER":
                return Customer.getInstance();
            case "DRIVER":
                return Driver.getInstance();
            case "USER":
                return User.getInstance();
            default:
                throw new IllegalArgumentException("This type of user is unavailable");
        }
    }
}
