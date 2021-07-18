package com.example.TVK.Ultis;

import com.example.TVK.Model.User.Admin;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.Model.User.Driver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GlobalUser {
    private static Customer globalCustomer;
    private static Admin globalAdmin;
    private static Driver globalDriver;

    private static String roleUser;

    public static  Driver getInstanceDriver(String roleUserne) {
        if (globalDriver  == null) {
            globalDriver = new Driver();
        }
        roleUser = roleUserne;
        return globalDriver;
    }

    public static Customer getInstanceCustomer(String roleUserne) {
        if (globalCustomer  == null) {
            globalCustomer = new Customer();
        }
        roleUser = roleUserne;
        return globalCustomer;
    }

    public static Admin getInstanceAdmin(String roleUserne) {
        if (globalAdmin  == null) {
            globalAdmin = new Admin();
        }
        roleUser = roleUserne;
        return globalAdmin;
    }

    public static void setCustomerModel(Customer customer)
    {
        globalCustomer = customer;
    }

    public static void setAdminModel(Admin admin)
    {
        globalAdmin = admin;
    }

    public static void setDriverModel(Driver driver)
    {
        globalDriver = driver;
    }

    public static void setGlobalUser(JSONObject jsonObject) throws JSONException {
        switch (roleUser)
        {
            case "ADMIN":
                globalAdmin = new Admin(Integer.parseInt(jsonObject.getString("ID")),
                        jsonObject.getString("Name"),
                        jsonObject.getString("Gender"),
                        jsonObject.getString("Phone"),
                        jsonObject.getString("Address"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Username"),
                        jsonObject.getString("Password"),
                        jsonObject.getString("ActivationCode"),
                        jsonObject.getString("State"),
                        "",
                        jsonObject.getString("TypeUser"),
                        0);
                break;
            case "DRIVER":

                globalDriver = new Driver(Integer.parseInt(jsonObject.getString("ID")),
                        jsonObject.getString("Name"),
                        jsonObject.getString("Gender"),
                        jsonObject.getString("Phone"),
                        jsonObject.getString("Address"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Username"),
                        jsonObject.getString("Password"),
                        jsonObject.getString("ActivationCode"),
                        jsonObject.getString("State"),
                        "",
                        jsonObject.getString("TypeUser"),
                        0,
                        jsonObject.getString("DriverLicenseNumber"),
                        jsonObject.getString("IdNumber"));
                break;
            default:
                globalCustomer = new Customer(Integer.parseInt(jsonObject.getString("ID")),
                        jsonObject.getString("Name"),
                        jsonObject.getString("Gender"),
                        jsonObject.getString("Phone"),
                        jsonObject.getString("Address"),
                        jsonObject.getString("Email"),
                        jsonObject.getString("Username"),
                        jsonObject.getString("Password"),
                        jsonObject.getString("ActivationCode"),
                        jsonObject.getString("State"),
                        "",
                        jsonObject.getString("TypeUser"),
                        0);
                break;
        }
    }

}
