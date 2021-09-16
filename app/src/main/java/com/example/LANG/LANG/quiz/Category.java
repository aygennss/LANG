package com.example.LANG.LANG.quiz;


public class Category {
    public static final int INGILIZCE = 1;
    public static final int ALMANCA = 2;
    public static final int FRANSZICA = 3;
    public static final int ISPANYOLCA = 4;




    private int id;

    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}