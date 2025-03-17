package com.example.hw3qestion6;

public class Password
{
    private String place;
    private String password;

    public Password(String place, String password)
    {
        this.password = password;
        this.place = place;
    }

    //get place
    public String getPlace()
    {
        return place;
    }

    //get password
    public String getPassword()
    {
        return password;
    }
}
