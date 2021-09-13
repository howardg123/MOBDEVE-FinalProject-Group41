package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {

    Button btnTutorialBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen();
        setContentView(R.layout.activity_tutorial);
        initComponent();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnTutorialBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, GameOptionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initComponent() {
        btnTutorialBack = findViewById(R.id.btnTutorialBack);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

}