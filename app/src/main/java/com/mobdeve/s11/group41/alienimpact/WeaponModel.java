package com.mobdeve.s11.group41.alienimpact;

public class WeaponModel {
    private int[] imageList;
    private String[] nameList;
    private int cost;

    public WeaponModel (int imageList[], String nameList[], int cost) {
        this.imageList = imageList;
        this.nameList = nameList;
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
    public int getCurrentImage (int level) {
        int index = (int) Math.floor(level/100);
        return imageList[index];
    }
    public String getCurrentName (int level) {
        int index = (int) Math.floor(level/100);
        return nameList[index];
    }
    public String getBaseName () {
        return nameList[0];
    }
}
