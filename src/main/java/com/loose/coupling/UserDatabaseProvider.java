package com.loose.coupling;

public class UserDatabaseProvider implements UserDataProvider {
    public String getUserDetails(){

         return "User Details From Database";
    }
}
