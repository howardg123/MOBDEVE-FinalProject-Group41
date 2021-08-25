package com.mobdeve.s11.group41.alienimpact;

public class BuffModel {
    private int image;
    private String name;
    private int amount;
    private int cost;

    public BuffModel (int image, String name, int amount, int cost) {
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.cost = cost;
    }

    public int getAmount () {return amount;}
    public int getCost () {return cost;}
    public int getImage () {return image;}
    public String getName () {return name;}
    public void setAmount(int amount) {this.amount = amount;}
}
