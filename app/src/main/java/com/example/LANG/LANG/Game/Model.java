package com.example.LANG.LANG.Game;



public class Model {

    private String kelime;
    private String anlami;
    private int id;

    public Model(String kelime, String anlami, int id) {
        this.kelime = kelime;
        this.anlami = anlami;
        this.id = id;
    }

    public String getKelime() {
        return kelime;
    }

    public String getAnlami() {
        return anlami;
    }

    public int getId() {
        return id;
    }
}
