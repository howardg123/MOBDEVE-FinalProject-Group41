package com.mobdeve.s11.group41.alienimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    MyDatabaseHelper myDB;

    int nGameProgress;
    int nMusicProgress;
    boolean bDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        myDB = new MyDatabaseHelper(GameOptionActivity.this);
        initComponent();
        setFullscreen();
        setComponent();
        bDefault = false;

        ibGameOptionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOptionActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ibGameOptionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bDefault == false) {
                    //Set volumes
                    myDB.updateGameVolume(nGameProgress);
                    myDB.updateMusicVolume(nMusicProgress);
                    //Set name
                    myDB.updatePlayerName(etOptionUserName.getText().toString().trim());
                    //Set picture
                    if (ivPlayerIcon1.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.player_human_a).getConstantState())) {
                        myDB.updatePlayerIcon(0);
                    }
                    else if (ivPlayerIcon2.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.player_human_b).getConstantState())) {
                        myDB.updatePlayerIcon(1);
                    }
                    else if (ivPlayerIcon3.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.player_alien).getConstantState())) {
                        myDB.updatePlayerIcon(2);
                    }
                }
                else {
                    //Set volumes
                    myDB.updateGameVolume(100);
                    myDB.updateMusicVolume(100);
                    //Set name
                    myDB.updatePlayerName("BOB");
                    //Set icon
                    myDB.updatePlayerIcon(0);
                }
            }
        });
        ibGameOptionDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //default
                bDefault = true;
                //default icon
                ivPlayerIcon1.setImageResource(R.drawable.player_human_a);
                ivPlayerIcon2.setImageResource(R.drawable.player_human_b_unselected);
                ivPlayerIcon3.setImageResource(R.drawable.player_alien_unselected);
                //default name
                etOptionUserName.setText("BOB");
                //default volume
                sbGameVolume.setProgress(100);
                sbMusicVolume.setProgress(100);
            }
        });

        ivPlayerIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlayerIcon1.setImageResource(R.drawable.player_human_a);
                ivPlayerIcon2.setImageResource(R.drawable.player_human_b_unselected);
                ivPlayerIcon3.setImageResource(R.drawable.player_alien_unselected);
            }
        });

        ivPlayerIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlayerIcon1.setImageResource(R.drawable.player_human_a_unselected);
                ivPlayerIcon2.setImageResource(R.drawable.player_human_b);
                ivPlayerIcon3.setImageResource(R.drawable.player_alien_unselected);
            }
        });

        ivPlayerIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlayerIcon1.setImageResource(R.drawable.player_human_a_unselected);
                ivPlayerIcon2.setImageResource(R.drawable.player_human_b_unselected);
                ivPlayerIcon3.setImageResource(R.drawable.player_alien);
            }
        });

        sbMusicVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nMusicProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbGameVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nGameProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

    private void setComponent() {
        //Highlight used icon
        if (myDB.getPlayerIcon() == 0) {
            ivPlayerIcon1.setImageResource(R.drawable.player_human_a);
        }
        else if (myDB.getPlayerIcon() == 1) {
            ivPlayerIcon2.setImageResource(R.drawable.player_human_b);
        }
        else if (myDB.getPlayerIcon() == 2) {
            ivPlayerIcon3.setImageResource(R.drawable.player_alien);
        }
        //Set text to curr name
        etOptionUserName.setText(myDB.getPlayerName());
        //set volume
        sbMusicVolume.setProgress(myDB.getMusicVolume());
        nMusicProgress = myDB.getMusicVolume();
        sbGameVolume.setProgress(myDB.getGameVolume());
        nGameProgress = myDB.getGameVolume();
    }

    private void setFullscreen() {
        View decorView = getWindow().getDecorView();
        // Hides the status and navigation bar until the user swipe from bottom to up or up to bottom
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}