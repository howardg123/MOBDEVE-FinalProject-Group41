package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class GameOptionActivity extends AppCompatActivity {

    ImageButton ibGameOptionBack;
    ImageButton ibGameOptionSave;
    ImageButton ibGameOptionDefault;
    ImageView ivPlayerIcon1;
    ImageView ivPlayerIcon2;
    ImageView ivPlayerIcon3;
    EditText etOptionUserName;
    SeekBar sbMusicVolume;
    SeekBar sbGameVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        initComponent();
        setFullscreen();
        ibGameOptionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibGameOptionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
            }
        });
        ibGameOptionDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //default
            }
        });
    }

    private void initComponent() {
        ibGameOptionBack = findViewById(R.id.ibGameOptionBack);
        ibGameOptionSave = findViewById(R.id.ibGameOptionSave);
        ibGameOptionDefault = findViewById(R.id.ibGameOptionDefault);
        ivPlayerIcon1 = findViewById(R.id.ivPlayerIcon1);
        ivPlayerIcon2 = findViewById(R.id.ivPlayerIcon2);
        ivPlayerIcon3 = findViewById(R.id.ivPlayerIcon3);
        etOptionUserName = findViewById(R.id.etOptionUserName);
        sbMusicVolume = findViewById(R.id.sbMusicVolume);
        sbGameVolume = findViewById(R.id.sbGameVolume);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}