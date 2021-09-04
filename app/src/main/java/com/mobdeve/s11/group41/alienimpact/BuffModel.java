package com.mobdeve.s11.group41.alienimpact;

public class BuffModel {
    private int image;
    private String name;
    private int cost;

    public BuffModel (int image, String name, int cost) {
        this.image = image;
        this.name = name;
        this.cost = cost;
    }

    public int getBaseCost () {return cost;}
    public int getCurrentCost (int level) {
        return (int) Math.floor(cost * (Math.pow(1.1, level)));
    }
    public int getBuyTenCost (int level) {
        int total_cost = 0;
        for (int i=0; i < 10; i++) {
            total_cost += (int) Math.floor(cost * (Math.pow(1.1, level + i)));
        }
        return total_cost;
    }
    public int getImage () {return image;}
    public String getName () {return name;}
}
