package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ShopActivity extends AppCompatActivity {

    ImageButton ibShopBack;
    ImageButton ibShopBuff;
    ImageButton ibShopWeapon;
    ImageButton ibShopPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initComponent();
        setFullscreen();

    }

    private void initComponent() {
        ibShopBack = findViewById(R.id.ibShopBack);
        ibShopBuff = findViewById(R.id.ibShopBuff);
        ibShopWeapon = findViewById(R.id.ibShopWeapon);
        ibShopPets = findViewById(R.id.ibShopPets);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}