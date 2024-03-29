package com.example.hustle4work.model;

public class NavigationModel {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String name;
    private int image;

    public NavigationModel(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
