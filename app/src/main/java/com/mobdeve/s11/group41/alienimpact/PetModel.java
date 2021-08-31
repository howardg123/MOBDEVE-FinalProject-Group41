package com.mobdeve.s11.group41.alienimpact;

public class PetModel {
    private int image;
    private String name;
    private String effect;
    private int cost;

    public PetModel (int image, String name, String effect, int cost) {
        this.image = image;
        this.name = name;
        this.effect = effect;
        this.cost = cost;
    }

    public int getCost () {return cost;}
    public int getImage () {return image;}
    public String getName () {return name;}
    public String getEffect () {return effect;}
}
