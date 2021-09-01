package com.mobdeve.s11.group41.alienimpact;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

public class GameActivity extends Activity {

    SurfaceView surface;
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
    private Bitmap bmp;
    private Bitmap bmpScaled;
    ImageView ivMotionGesture;
    float x1 = 0;
    float y1 = 0;
    float t1 = 0;
    float x2 = 0;
    float y2 = 0;
    float t2 = 0;
    MyDatabaseHelper myDB;

    int nEnemy;
    int enemyMaxHP;
    String enemyName;

    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;
    AudioAttributes attributes;
    AudioAttributes.Builder attributesBuilder;
    int soundID_hold;
    int soundID_hold_shoot;
    int soundID_tap_shoot;
    int soundID_swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen();
        setContentView(R.layout.activity_game);
        myDB = new MyDatabaseHelper(GameActivity.this);
        initComponent();
        //get enemymaxhp sharedpreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        enemyMaxHP = sh.getInt("enemyMaxHP", (int)Math.ceil(myDB.getGamePrevHP() + 10 * Math.pow(1.15, 1 + myDB.getGameRound())));
        enemyName = sh.getString("enemyName","DRONE");
        nEnemy = sh.getInt("nEnemy", 0);
        initDatabase();
        setComponent();
        btnGameShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });


        attributesBuilder = new AudioAttributes.Builder();
        attributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
        attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_MOVIE);
        attributes = attributesBuilder.build();

        soundPoolBuilder = new SoundPool.Builder();
        soundPoolBuilder.setAudioAttributes(attributes);
        soundPool = soundPoolBuilder.build();

        soundID_hold = soundPool.load(this, R.raw.shoot, 1);
        soundID_hold_shoot = soundPool.load(this, R.raw.hold_shot, 2);
        soundID_tap_shoot = soundPool.load(this, R.raw.tap_shot, 2);
        soundID_swipe = soundPool.load(this, R.raw.swipe, 2);

        ivMotionGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int buffs;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if (mediaPlayer == null)
                            startShoot();
                        mediaPlayer.start();
                        x1 = event.getX();
                        y1 = event.getY();
                        t1 = SystemClock.uptimeMillis();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        t2 = SystemClock.uptimeMillis();
                        mediaPlayer.stop();
                        stopShoot();
                        if (x1 == x2 && y1 == y2 && (t2 - t1) < 1000) {
                            System.out.println("click");
                            buffs = 0;
                            damageEnemy(1 + (2 * buffs));
                            checkEnemy();
                            soundPool.play(soundID_tap_shoot,1, 1, 0, 0, 1);
                        }
                        else if ((t2 - t1) >= 1000 && (t2 - t1) < 2000) { //1 to 1.99 seconds
                            System.out.println("hold");
                            buffs = myDB.getHoldLevel();
                            damageEnemy(5 + (40 * buffs));
                            checkEnemy();
                            soundPool.play(soundID_hold_shoot,1, 1, 0, 0, 1);
                        }
                        else if ((t2 - t1) >= 2000 && (t2 - t1) < 3000) { //2 to 2.99 seconds
                            System.out.println("hold");
                            buffs = myDB.getHoldLevel();
                            damageEnemy((5 + (40 * buffs))*2);
                            checkEnemy();
                            soundPool.play(soundID_hold_shoot,1, 1, 0, 0, 1);
                        }
                        else if ((t2 - t1) >= 3000) { // 3 seconds above
                            System.out.println("hold");
                            buffs = myDB.getHoldLevel();
                            damageEnemy((5 + (40 * buffs))*3);
                            checkEnemy();
                            soundPool.play(soundID_hold_shoot,1, 1, 0, 0, 1);
                        }
                        else if ((x1 != x2) || (y1 != y2) ) {
                            System.out.println("swipe");
                            buffs = myDB.getHoldLevel();
                            damageEnemy(10 + (360 * buffs));
                            checkEnemy();
                            soundPool.play(soundID_swipe,1, 1, 0, 0, 1);
                        }
                        return true;
                }

                return false;
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
                finish();
            }
        });

        surface.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                setEnemy();
                drawEnemy();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                System.out.println("changed");
            }
        });

    }

    private void startShoot(){
        mediaPlayer = MediaPlayer.create(this, R.raw.shoot);
    }

    private void stopShoot(){
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void setEnemy() {
        switch(this.nEnemy){
            case 0:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_drone);
                this.enemyName = "DRONE";
                break;
            case 1:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_face_hugger);
                this.enemyName = "FACE HUGGER";
                break;
            case 2:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_xenomorph);
                this.enemyName = "XENOMORPH";
                break;
            case 3:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_graylien);
                this.enemyName = "GRAYLIEN";
                break;
            case 4:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_sus);
                this.enemyName = "SUSSY ALIEN";
                break;
            case 5:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_mothership);
                this.enemyName = "MOTHERSHIP";
                break;
        }
    }

    private void nextEnemy() {
        if (nEnemy < 5) {
            this.nEnemy++;
        }
        else {
            this.nEnemy = 0;
        }
        switch(nEnemy){
            case 0:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_drone);
                this.enemyName = "DRONE";
                break;
            case 1:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_face_hugger);
                this.enemyName = "FACE HUGGER";
                break;
            case 2:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_xenomorph);
                this.enemyName = "XENOMORPH";
                break;
            case 3:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_graylien);
                this.enemyName = "GRAYLIEN";
                break;
            case 4:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_sus);
                this.enemyName = "SUSSY ALIEN";
                break;
            case 5:
                this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.alien_mothership);
                this.enemyName = "MOTHERSHIP";
                break;
        }
    }

    private void drawEnemy() {
        Canvas canvas = surface.getHolder().lockCanvas();
        canvas.drawColor(Color.rgb(61, 79, 43));
        bmpScaled = Bitmap.createScaledBitmap(bmp, 550, 550, true);
        int left  = (canvas.getWidth() - bmpScaled.getWidth())/2;
        int top  = (canvas.getHeight() - bmpScaled.getHeight())/2;
        canvas.drawBitmap(bmpScaled, left, top, null);
        surface.getHolder().unlockCanvasAndPost(canvas);
    }

    private void initComponent() {
        surface = findViewById(R.id.surfaceGame);
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
        ivMotionGesture = findViewById(R.id.ivMotionGesture);
    }

    private void setComponent() {
        //set round and stage
        tvStage.setText("STAGE " + myDB.getGameStage());
        tvRound.setText("ROUND " + myDB.getGameRound());
        //set scrap
        tvGameScrap.setText(myDB.getScrap() + " SCRAP");
        //save sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("enemyName", enemyName);
        myEdit.putInt("enemyMaxHP", enemyMaxHP);
        myEdit.putInt("nEnemy",nEnemy);
        myEdit.commit();
        //set enemy
        tvEnemyHp.setText(myDB.getGameHP() + "/" + enemyMaxHP + " HP");
        tvEnemyName.setText(enemyName);
        pbHPBar.setProgress((int)Math.floor(((float)myDB.getGameHP()/enemyMaxHP) * 100));
        //set player name and icon
        setPlayerAndIcon();
        //set pet name and icon
        setPetAndIcon();
        //set damage per second
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void damageEnemy(int nDamage) {
        myDB.removeGameHP(nDamage);
        setComponent();
    }

    private void setPlayerAndIcon() {
        tvGameUserName.setText(myDB.getPlayerName());
        switch(myDB.getPlayerIcon()) {
            case 0:
                ivGamePlayerIcon.setImageResource(R.drawable.player_human_a);
                break;
            case 1:
                ivGamePlayerIcon.setImageResource(R.drawable.player_human_b);
                break;
            case 2:
                ivGamePlayerIcon.setImageResource(R.drawable.player_alien);
                break;
        }
    }

    private void setPetAndIcon() {
        switch(myDB.getPetUsed()) {
            case -1:
                tvGamePetName.setText("NO PET");
                ivGamePetIcon.setImageResource(R.drawable.button_pets);
                break;
            case 0:
                tvGamePetName.setText("DOG");
                ivGamePetIcon.setImageResource(R.drawable.pet_dog);
                break;
            case 1:
                tvGamePetName.setText("MONKEY");
                ivGamePetIcon.setImageResource(R.drawable.pet_monkey);
                break;
            case 2:
                tvGamePetName.setText("TURTLE");
                ivGamePetIcon.setImageResource(R.drawable.pet_turtle);
                break;
            case 3:
                tvGamePetName.setText("CAT");
                ivGamePetIcon.setImageResource(R.drawable.pet_cat);
                break;
        }
    }

    private void checkEnemy() {
        if (myDB.getGameHP() <= 0) {
            //update scrap
            myDB.updateScrap(10 * myDB.getGamePrevHP());
            //update enemy prev hp
            myDB.updateGamePrevHP(enemyMaxHP);
            //update current enemy hp
            enemyMaxHP = (int)Math.ceil(myDB.getGamePrevHP() + 10 * Math.pow(1.15, 1 + myDB.getGameRound()));
            myDB.updateGameHP(enemyMaxHP);
            //update round and stage
            nextRoundAndStage();
            //change all text
            setComponent();
        }
    }

    private void nextRoundAndStage() {
        if (myDB.getGameRound() == 10) {
            myDB.updateGameStage();
        }
        myDB.updateGameRound();
        //switch enemy
        nextEnemy();
        drawEnemy();
    }

    private void initDatabase() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(GameActivity.this);
        if (myDB.getScrap() == -1) {
            myDB.initPlayer();
            myDB.initGame();
            myDB.initVolume();
            myDB.initBuffLevel();
            myDB.initWeapons();
            myDB.initPet();
            myDB.updateGameHP(enemyMaxHP);
            myDB.updateGamePrevHP(enemyMaxHP);
        }
    }
}