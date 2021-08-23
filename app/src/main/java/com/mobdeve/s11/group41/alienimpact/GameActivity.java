package com.mobdeve.s11.group41.alienimpact;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends Activity {

    SurfaceView surface;
    Button btnGameBack;
    Button btnGameShop;
    ImageButton ibGameStat;
    ImageButton ibGameOption;
    TextView tvStage;
    TextView tvRound;
    TextView tvEnemyName;
    TextView tvEnemyHp;
    TextView tvDPS;
    TextView tvGameUserName;
    TextView tvGamePetName;
    TextView tvGameScrap;
    ImageView ivGamePlayerIcon;
    ImageView ivGamePetIcon;
    ProgressBar pbHPBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen();
        setContentView(R.layout.activity_game);
        initComponent();
        btnGameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGameShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        ibGameStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, StatActivity.class);
                startActivity(intent);
            }
        });

        ibGameOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, GameOptionActivity.class);
                startActivity(intent);
            }
        });

        surface.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.argb(255,0, 0,255));
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
    }

    private void initComponent() {
        surface = findViewById(R.id.surfaceGame);
        btnGameBack = findViewById(R.id.btnGameBack);
        btnGameShop = findViewById(R.id.btnGameShop);
        ibGameStat = findViewById(R.id.ibGameStat);
        ibGameOption = findViewById(R.id.ibGameOption);
        tvStage = findViewById(R.id.tvStage);
        tvRound = findViewById(R.id.tvRound);
        tvEnemyName = findViewById(R.id.tvEnemyName);
        tvEnemyHp = findViewById(R.id.tvEnemyHp);
        tvDPS = findViewById(R.id.tvDPS);
        tvGameUserName = findViewById(R.id.tvGameUserName);
        tvGamePetName = findViewById(R.id.tvGamePetName);
        tvGameScrap = findViewById(R.id.tvGameScrap);
        ivGamePlayerIcon = findViewById(R.id.ivGamePlayerIcon);
        ivGamePetIcon = findViewById(R.id.ivGamePetIcon);
        pbHPBar = findViewById(R.id.pbHpBar);
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}