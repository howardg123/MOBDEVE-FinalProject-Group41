package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOptionActivity extends AppCompatActivity {

    Button btnGameOptionBack;
    Button btnGameOptionSave;
    Button btnGameOptionDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        initComponent();
        setFullscreen();
        btnGameOptionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnGameOptionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
                finish();
            }
        });
        btnGameOptionDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //default
            }
        });
    }

    private void initComponent() {
        btnGameOptionBack = findViewById(R.id.btnGameOptionBack);
        btnGameOptionSave = findViewById(R.id.btnGameOptionSave);
        btnGameOptionDefault = findViewById(R.id.btnGameOptionDefault);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}