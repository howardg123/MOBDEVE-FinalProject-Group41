package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    ImageButton ibShopBack;
    ImageButton ibShopBuff;
    ImageButton ibShopWeapon;
    ImageButton ibShopPets;
    TextView tvShopTitle;
    TextView tvShopScrap;
    private ArrayList<WeaponModel> weapons;
    private ArrayList<BuffModel> buffs;
    private ArrayList<PetModel> pets;
    private RecyclerView rv;
    private String shopMode = "null";
    private static final String KEY_MODE = "KEY_MODE";
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        myDB = new MyDatabaseHelper(ShopActivity.this);
        Intent i = new Intent(ShopActivity.this, ShopActivity.class);
        Bundle bInput = new Bundle();
        Bundle bGet = getIntent().getExtras();
        if (bGet != null)
            this.shopMode = bGet.getString(KEY_MODE, "null");
        initComponent();

        switch (shopMode) {
            case "pet":
                initPets();
                break;
            case "buff":
                initBuffs();
                break;
            default:
                initWeapons();
                break;
        }


        setFullscreen();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibShopBuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bInput.putString(KEY_MODE, "buff");
                i.putExtras(bInput);
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
                ShopActivity.this.finish();
                overridePendingTransition(0, 0);
            }
        });

        ibShopWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bInput.putString(KEY_MODE, "weapon");
                i.putExtras(bInput);
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
                ShopActivity.this.finish();
                overridePendingTransition(0, 0);
            }
        });

        ibShopPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bInput.putString(KEY_MODE, "pet");
                i.putExtras(bInput);
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
                ShopActivity.this.finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    //initialize buffs page
    private void initBuffs () {
        View overlay = findViewById(R.id.rvShop);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.tvShopTitle.setText("Buff Shop");
        this.tvShopScrap.setText(myDB.getScrap() + " SCRAP");

        this.buffs = new ShopDataHelper().initializeBuffData();
        this.rv = findViewById(R.id.rvShop);
        this.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rv.setAdapter(new ShopAdapter(this.buffs, this.myDB, ShopActivity.this));

        this.rv.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                tvShopScrap.setText(myDB.getScrap() + " SCRAP");
            }
        });
    }

    //initialize weapons page
    private void initWeapons () {
        View overlay = findViewById(R.id.rvShop);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.tvShopTitle.setText("Weapon Shop");
        this.tvShopScrap.setText(myDB.getScrap() + " SCRAP");

        this.weapons = new ShopDataHelper().initializeWeaponData();
        this.rv = findViewById(R.id.rvShop);
        this.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rv.setAdapter(new ShopAdapter(this.weapons, this.myDB, ShopActivity.this, 0));

        this.rv.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                tvShopScrap.setText(myDB.getScrap() + " SCRAP");
            }
        });

    }

    //initialize pets page
    private void initPets() {
        View overlay = findViewById(R.id.rvShop);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.tvShopTitle.setText("Pet Shop");
        this.tvShopScrap.setText(myDB.getScrap() + " SCRAP");

        this.pets = new ShopDataHelper().initializePetData();
        this.rv = findViewById(R.id.rvShop);
        this.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rv.setAdapter(new PetShopAdapter(this.pets, this.myDB, ShopActivity.this));

        this.rv.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                tvShopScrap.setText(myDB.getScrap() + " SCRAP");
            }
        });
    }

    private void initComponent() {
        ibShopBack = findViewById(R.id.ibShopBack);
        ibShopBuff = findViewById(R.id.ibShopBuff);
        ibShopWeapon = findViewById(R.id.ibShopWeapon);
        ibShopPets = findViewById(R.id.ibShopPets);
        tvShopTitle = findViewById(R.id.tvShopTitle);
        tvShopScrap = findViewById(R.id.tvShopScrap);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}