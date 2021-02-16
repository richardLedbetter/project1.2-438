package com.example.myapplication;

public class City {
    private String name;
    private float temp;

    public City(String n, float t)
    {
        name = n;
        temp = t;
    }

    public String getName() {
        return name;
    }

    public float getTemp() {
        return temp;
    }
}
