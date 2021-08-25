package com.mobdeve.s11.group41.alienimpact;

public class WeaponModel {
    private int[] imageList;
    private String[] nameList;
    private int currentIndex;
    private int amount;
    private int cost;

    public WeaponModel (int imageList[], String nameList[], int currentIndex, int amount, int cost) {
        this.imageList = imageList;
        this.nameList = nameList;
        this.currentIndex = currentIndex;
        this.amount = amount;
        this.cost = cost;
    }

    public int getCurrentIndex () {return currentIndex;}
    public int getAmount () {return amount;}
    public int getCost () {return cost;}
    public int getCurrentImage () {return imageList[currentIndex];}
    public String getCurrentName () {return nameList[currentIndex];}
    public void setCurrentIndex (int index) {this.currentIndex = index;}
    public void setAmount (int amount) {this.amount = amount;}
}
