package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputPreferenceActivity extends AppCompatActivity {

    Button btnPreferenceBack;
    Button btnPreferenceSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_preference);
        setFullscreen();
        initComponent();
        btnPreferenceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPreferenceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
            }
        });
    }

    private void initComponent() {
        btnPreferenceBack = findViewById(R.id.btnPreferenceBack);
        btnPreferenceSave = findViewById(R.id.btnPreferenceSave);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}