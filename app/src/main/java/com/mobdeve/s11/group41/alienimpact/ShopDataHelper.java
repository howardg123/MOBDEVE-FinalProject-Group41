package com.mobdeve.s11.group41.alienimpact;

import java.util.ArrayList;

public class ShopDataHelper {

    //data for weapons
    public static ArrayList<WeaponModel> initializeWeaponData() {
        //weapon images
        int[] fistImages = {R.drawable.wep_1_1_fist, R.drawable.wep_1_2_gloves, R.drawable.wep_1_3_knuckles, R.drawable.wep_1_4_doomfist, R.drawable.wep_1_5_gauntlets};
        int[] stickImages = {R.drawable.wep_2_1_stick, R.drawable.wep_2_2_club, R.drawable.wep_2_3_katana, R.drawable.wep_2_4_greatsword, R.drawable.wep_2_5_energysaber};
        int[] projectileImages = {R.drawable.wep_3_1_blowgun, R.drawable.wep_3_2_crossbow, R.drawable.wep_3_3_pistol, R.drawable.wep_3_4_ar, R.drawable.wep_3_5_railgun};
        int[] artilleryImages = {R.drawable.wep_4_1_trebuchet, R.drawable.wep_4_2_cannon, R.drawable.wep_4_3_mortar, R.drawable.wep_4_4_missile, R.drawable.wep_4_5_antimatter};
        int[] supportImages = {R.drawable.wep_5_1_carriage, R.drawable.wep_5_2_steamboat, R.drawable.wep_5_3_train, R.drawable.wep_5_4_helicarrier, R.drawable.wep_5_5_teleporter};
        //weapon names
        String[] fistNames = {"Fists", "Gloves", "Knuckledusters", "Fists of Doom", "Orichalcum Gauntlets"};
        String[] stickNames = {"Stick", "Club", "Katana", "Greatsword", "Energy Saber"};
        String[] projectileNames = {"Slingshot", "Crossbow", "Pistol", "Assault Rifle", "Antimatter Launcher"};
        String[] artilleryNames = {"Trebuchet", "Cannon", "Mortal", "Missile", "Alien Railgun"};
        String[] supportNames = {"Carriage", "Steamboat", "Train", "Heli Carrier", "Teleporter"};

        ArrayList<WeaponModel> weaponData = new ArrayList<>();
        weaponData.add(new WeaponModel(fistImages, fistNames, 0, 0, 10));
        weaponData.add(new WeaponModel(stickImages, stickNames, 0, 0, 75));
        weaponData.add(new WeaponModel(projectileImages, projectileNames, 0, 0, 800));
        weaponData.add(new WeaponModel(artilleryImages, artilleryNames, 0, 0, 10000));
        weaponData.add(new WeaponModel(supportImages, supportNames, 0, 0, 110000));
        return weaponData;
    }

    //data for buffs
    public static ArrayList<BuffModel> initializeBuffData() {
        ArrayList<BuffModel> buffData = new ArrayList<>();
        buffData.add(new BuffModel(R.drawable.buff_click, "Click", 0, 20));
        buffData.add(new BuffModel(R.drawable.buff_hold, "Hold", 0, 200));
        buffData.add(new BuffModel(R.drawable.buff_swipe, "Swipe", 0, 2000));
        return buffData;
    }

    //data for pets
    public static ArrayList<PetModel> initializePetData() {
        ArrayList<PetModel> petData = new ArrayList<>();
        petData.add(new PetModel(R.drawable.pet_dog, "Dog", "deals 25% non-pet dmg; every min 10% chance to deal 1000% non-pet dmg for 1 min instead", 10000));
        petData.add(new PetModel(R.drawable.pet_monkey, "Monkey", "deals 50% non-pet dmg; 20% dmg boost to non-pets", 50000));
        petData.add(new PetModel(R.drawable.pet_turtle, "Turtle", "deals no dmg; 1000% dmg boost to player", 100000));
        petData.add(new PetModel(R.drawable.pet_cat, "Cat", "deals 100% non-pet dmg; offline dmg is 50% instead", 500000));
        return petData;
    }

}
