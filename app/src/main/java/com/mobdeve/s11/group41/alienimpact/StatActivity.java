package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StatActivity extends AppCompatActivity {

    ImageButton ibStatBack;
    TextView tvNAliensKilled;
    TextView tvNScrapsEarned;
    TextView tvNScrapsSpent;
    TextView tvNTaps;
    TextView tvNHolds;
    TextView tvNSwipes;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        myDB = new MyDatabaseHelper(StatActivity.this);
        initComponent();
        setComponent();
        setFullscreen();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ibStatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponent() {
        ibStatBack = findViewById(R.id.ibStatBack);
        tvNAliensKilled = findViewById(R.id.tvNAliensKilled);
        tvNScrapsEarned = findViewById(R.id.tvNTotalEarned);
        tvNScrapsSpent = findViewById(R.id.tvNTotalSpent);
        tvNTaps = findViewById(R.id.tvNTotalTaps);
        tvNHolds = findViewById(R.id.tvNTotalHolds);
        tvNSwipes = findViewById(R.id.tvNTotalSwipes);
    }

    private void setComponent() {
        //Total alien killed
        tvNAliensKilled.setText((myDB.getGameStage()-1) * 10 + (myDB.getGameRound()-1) + " ");
        //Total scrap spent
        tvNScrapsSpent.setText(myDB.getScrapSpent() + " ");
        //Total scrap earned
        tvNScrapsEarned.setText(myDB.getScrapEarned() + " ");
        //Total taps
        tvNTaps.setText(myDB.getTotalTaps() + " ");
        //Total holds
        tvNHolds.setText(myDB.getTotalHolds() + " ");
        //Total swipes
        tvNSwipes.setText(myDB.getTotalSwipes() + " ");
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}