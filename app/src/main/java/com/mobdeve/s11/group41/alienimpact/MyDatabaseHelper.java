package com.mobdeve.s11.group41.alienimpact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AlienImpact.db";
    private static final int DATABASE_VERSION = 1;

    private static final String PLAYER_TABLE_NAME = "player_table";
    private static final String PLAYER_COLUMN_ID = "_id";
    private static final String PLAYER_COLUMN_SCRAP_QUANTITY = "player_scrap_quantity";
    private static final String PLAYER_COLUMN_ICON = "player_icon";
    private static final String PLAYER_COLUMN_USERNAME = "player_username";

    private static final String OPTION_TABLE_NAME = "option_table";
    private static final String OPTION_COLUMN_ID = "_id";
    private static final String OPTION_COLUMN_MUSIC_VOLUME = "option_music_volume";
    private static final String OPTION_COLUMN_GAME_VOLUME = "option_game_volume";

    private static final String BUFF_TABLE_NAME = "buff_table";
    private static final String BUFF_COLUMN_ID = "_id";
    private static final String BUFF_COLUMN_TAP_LEVEL = "buff_tap_level";
    private static final String BUFF_COLUMN_HOLD_LEVEL = "buff_hold_level";
    private static final String BUFF_COLUMN_SWIPE_LEVEL = "buff_swipe_level";

    private static final String GAME_TABLE_NAME = "game_table";
    private static final String GAME_COLUMN_ID = "_id";
    private static final String GAME_COLUMN_CURR_STAGE = "game_curr_stage";
    private static final String GAME_COLUMN_CURR_ROUND = "game_curr_round";
    private static final String GAME_COLUMN_PREV_HP = "game_prev_round_hp";
    private static final String GAME_COLUMN_CURR_HP = "game_curr_round_hp";

    private static final String WEAPON_TABLE_NAME = "weapon_table";
    private static final String WEAPON_COLUMN_ID = "_id";
    private static final String WEAPON_COLUMN_WEP0 = "wep0_level";
    private static final String WEAPON_COLUMN_WEP1 = "wep1_level";
    private static final String WEAPON_COLUMN_WEP2 = "wep2_level";
    private static final String WEAPON_COLUMN_WEP3 = "wep3_level";
    private static final String WEAPON_COLUMN_WEP4 = "wep4_level";

    private static final String PET_TABLE_NAME = "pet_table";
    private static final String PET_COLUMN_ID = "_id";
    private static final String PET_COLUMN_PET_USED = "pet_used";
    private static final String PET_COLUMN_PET0_BOUGHT = "pet0_bought";
    private static final String PET_COLUMN_PET1_BOUGHT = "pet1_bought";
    private static final String PET_COLUMN_PET2_BOUGHT = "pet2_bought";
    private static final String PET_COLUMN_PET3_BOUGHT = "pet3_bought";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 =
                "CREATE TABLE " + PLAYER_TABLE_NAME +
                        " (" + PLAYER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PLAYER_COLUMN_SCRAP_QUANTITY + " INTEGER, " +
                        PLAYER_COLUMN_ICON + " INTEGER, " +
                        PLAYER_COLUMN_USERNAME + " TEXT);";
        String query2 =
                "CREATE TABLE " + OPTION_TABLE_NAME +
                        " (" + OPTION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        OPTION_COLUMN_MUSIC_VOLUME + " INTEGER, " +
                        OPTION_COLUMN_GAME_VOLUME + " INTEGER);";
        String query3 =
                "CREATE TABLE " + BUFF_TABLE_NAME +
                        " (" + BUFF_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        BUFF_COLUMN_TAP_LEVEL + " INTEGER, " +
                        BUFF_COLUMN_HOLD_LEVEL + " INTEGER, " +
                        BUFF_COLUMN_SWIPE_LEVEL + " INTEGER);";
        String query4 =
                "CREATE TABLE " + GAME_TABLE_NAME +
                        " (" + GAME_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        GAME_COLUMN_CURR_STAGE + " INTEGER, " +
                        GAME_COLUMN_CURR_ROUND + " INTEGER, " +
                        GAME_COLUMN_PREV_HP + " INTEGER, " +
                        GAME_COLUMN_CURR_HP + " INTEGER);";
        String query5 =
                "CREATE TABLE " + WEAPON_TABLE_NAME +
                        " (" + WEAPON_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WEAPON_COLUMN_WEP0 + " INTEGER, " +
                        WEAPON_COLUMN_WEP1 + " INTEGER, " +
                        WEAPON_COLUMN_WEP2 + " INTEGER, " +
                        WEAPON_COLUMN_WEP3 + " INTEGER, " +
                        WEAPON_COLUMN_WEP4 + " INTEGER);";
        String query6 =
                "CREATE TABLE " + PET_TABLE_NAME +
                        " (" + PET_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PET_COLUMN_PET_USED + " INTEGER, " +
                        PET_COLUMN_PET0_BOUGHT + " INTEGER, " +
                        PET_COLUMN_PET1_BOUGHT + " INTEGER, " +
                        PET_COLUMN_PET2_BOUGHT + " INTEGER, " +
                        PET_COLUMN_PET3_BOUGHT + " INTEGER);";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OPTION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BUFF_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WEAPON_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PET_TABLE_NAME);
        onCreate(db);
    }

    //------------------------------------------------------------------------------------------------------
    //Player
    //Insert player
    public void initPlayer() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_SCRAP_QUANTITY, 0);
        cv.put(PLAYER_COLUMN_ICON, 0);
        cv.put(PLAYER_COLUMN_USERNAME, "BOB");
        db.insert(PLAYER_TABLE_NAME, null, cv);
    }

    //adds the number of scrap
    public void updateScrap(int nScrap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        nScrap += getScrap();
        cv.put(PLAYER_COLUMN_SCRAP_QUANTITY, nScrap);

        long result = db.update(PLAYER_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to add " + nScrap + " scrap", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully added " + nScrap  + " scrap", Toast.LENGTH_SHORT).show();
        }
    }

    //returns current scrap
    public int getScrap() {
        String query = "SELECT " + PLAYER_COLUMN_SCRAP_QUANTITY + " FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_SCRAP_QUANTITY));
        }
        return -1;
    }

    //Decreases the number of current scrap
    public void removeScrap(int nScrap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_SCRAP_QUANTITY, getScrap() - nScrap);

        long result = db.update(PLAYER_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to remove " + nScrap + " scrap", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully removed " + nScrap + " scrap", Toast.LENGTH_SHORT).show();
        }
    }

    //change icon
    public void updatePlayerIcon(int nIcon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_ICON, nIcon);

        long result = db.update(PLAYER_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change to icon " + nIcon, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed to icon " + nIcon, Toast.LENGTH_SHORT).show();
        }
    }

    //returns player icon
    public int getPlayerIcon() {
        String query = "SELECT " + PLAYER_COLUMN_ICON + " FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_ICON));
        }
        return -1;
    }

    //change player name
    public void updatePlayerName(String sName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_USERNAME, sName);

        long result = db.update(PLAYER_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the name to " + sName, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the name to " + sName, Toast.LENGTH_SHORT).show();
        }
    }

    //returns player name
    public String getPlayerName() {
        String query = "SELECT " + PLAYER_COLUMN_USERNAME + " FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(PLAYER_COLUMN_USERNAME));
        }
        return "Bob";
    }
    //------------------------------------------------------------------------------------------------------
    //Option
    //Insert Volume
    public void initVolume() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OPTION_COLUMN_MUSIC_VOLUME, 100);
        cv.put(OPTION_COLUMN_GAME_VOLUME, 100);
        db.insert(OPTION_TABLE_NAME, null, cv);
    }

    //change music volume
    public void updateMusicVolume(int nVol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OPTION_COLUMN_MUSIC_VOLUME, nVol);

        long result = db.update(OPTION_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the music volume to " + nVol, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the music volume to " + nVol, Toast.LENGTH_SHORT).show();
        }
    }

    //returns music volume
    public int getMusicVolume() {
        String query = "SELECT " + OPTION_COLUMN_MUSIC_VOLUME + " FROM " + OPTION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(OPTION_COLUMN_MUSIC_VOLUME));
        }
        return -1;
    }

    //change music volume
    public void updateGameVolume(int nVol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OPTION_COLUMN_GAME_VOLUME, nVol);

        long result = db.update(OPTION_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the game volume to " + nVol, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the game volume to " + nVol, Toast.LENGTH_SHORT).show();
        }
    }

    //returns music volume
    public int getGameVolume() {
        String query = "SELECT " + OPTION_COLUMN_GAME_VOLUME + " FROM " + OPTION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(OPTION_COLUMN_GAME_VOLUME));
        }
        return -1;
    }

    //------------------------------------------------------------------------------------------------------
    //Buff
    //Insert buff Levels
    public void initBuffLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_TAP_LEVEL, 0);
        cv.put(BUFF_COLUMN_HOLD_LEVEL, 0);
        cv.put(BUFF_COLUMN_SWIPE_LEVEL, 0);
        db.insert(BUFF_TABLE_NAME, null, cv);
    }

    //change tap level
    public void updateTapLevel(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_TAP_LEVEL, nLevel);

        long result = db.update(BUFF_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the tap level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the tap level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns tap level
    public int getTapLevel() {
        String query = "SELECT " + BUFF_COLUMN_TAP_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_TAP_LEVEL));
        }
        return -1;
    }

    //change tap level
    public void updateHoldLevel(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_HOLD_LEVEL, nLevel);

        long result = db.update(BUFF_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the hold level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the hold level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns tap level
    public int getHoldLevel() {
        String query = "SELECT " + BUFF_COLUMN_HOLD_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_HOLD_LEVEL));
        }
        return -1;
    }

    //change swipe level
    public void updateSwipeLevel(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_SWIPE_LEVEL, nLevel);

        long result = db.update(BUFF_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the swipe level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the swipe level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns tap level
    public int getSwipeLevel() {
        String query = "SELECT " + BUFF_COLUMN_SWIPE_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_SWIPE_LEVEL));
        }
        return -1;
    }

    //------------------------------------------------------------------------------------------------------
    //Game
    //Insert game
    public void initGame() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_STAGE, 1);
        cv.put(GAME_COLUMN_CURR_ROUND, 1);
        cv.put(GAME_COLUMN_CURR_HP, 0);
        cv.put(GAME_COLUMN_PREV_HP, 0);
        db.insert(GAME_TABLE_NAME, null, cv);
    }

    //add 1 to stage
    public void updateGameStage() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_STAGE, getGameStage() + 1);

        long result = db.update(GAME_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the stage to " + getGameStage(), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the stage to " + getGameStage(), Toast.LENGTH_SHORT).show();
        }
    }

    //returns stage
    public int getGameStage() {
        String query = "SELECT " + GAME_COLUMN_CURR_STAGE + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_STAGE));
        }
        return -1;
    }

    //game round add 1
    public void updateGameRound() {
        int nRound = getGameRound();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (nRound < 10) {
            nRound += 1;
        }
        else {
            nRound = 1;
        }
        cv.put(GAME_COLUMN_CURR_ROUND, nRound);

        long result = db.update(GAME_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the round to " + nRound, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the round to " + nRound, Toast.LENGTH_SHORT).show();
        }
    }

    //returns round
    public int getGameRound() {
        String query = "SELECT " + GAME_COLUMN_CURR_ROUND + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_ROUND));
        }
        return -1;
    }

    //change enemy max hp
    public void updateGameHP(int nHP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_HP, nHP);

        long result = db.update(GAME_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change the HP to " + nHP, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed the HP to " + nHP, Toast.LENGTH_SHORT).show();
        }
    }

    //returns enemy hp
    public int getGameHP() {
        String query = "SELECT " + GAME_COLUMN_CURR_HP + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_HP));
        }
        return -1;
    }

    //Decreases the hp of current enemy
    public void removeGameHP(int nHP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_HP, getGameHP() - nHP);

        long result = db.update(GAME_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to remove " + nHP + " HP", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully removed " + nHP + " HP", Toast.LENGTH_SHORT).show();
        }
    }

    //set prev max hp
    public void updateGamePrevHP(int nHP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_PREV_HP, nHP);

        long result = db.update(GAME_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change prev HP to " + nHP, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed prev HP to " + nHP, Toast.LENGTH_SHORT).show();
        }
    }

    //returns prev enemy hp
    public int getGamePrevHP() {
        String query = "SELECT " + GAME_COLUMN_PREV_HP + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_PREV_HP));
        }
        return -1;
    }

    //------------------------------------------------------------------------------------------------------
    //Weapon
    //Insert weapons
    public void initWeapons() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP0, 0);
        cv.put(WEAPON_COLUMN_WEP1, 0);
        cv.put(WEAPON_COLUMN_WEP2, 0);
        cv.put(WEAPON_COLUMN_WEP3, 0);
        cv.put(WEAPON_COLUMN_WEP4, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 0
    public void updateWeapon0(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP0, nLevel + getWeapon0());

        long result = db.update(WEAPON_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change wep0 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed wep0 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns weapon 0
    public int getWeapon0() {
        String query = "SELECT " + WEAPON_COLUMN_WEP0 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP0));
        }
        return -1;
    }

    //change weapon 1
    public void updateWeapon1(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP1, nLevel + getWeapon1());

        long result = db.update(WEAPON_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change wep1 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed wep1 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns weapon 1
    public int getWeapon1() {
        String query = "SELECT " + WEAPON_COLUMN_WEP1 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP1));
        }
        return -1;
    }

    //change weapon 2
    public void updateWeapon2(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP2, nLevel + getWeapon2());

        long result = db.update(WEAPON_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change wep2 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed wep2 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns weapon 2
    public int getWeapon2() {
        String query = "SELECT " + WEAPON_COLUMN_WEP2 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP2));
        }
        return -1;
    }

    //change weapon 3
    public void updateWeapon3(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP3, nLevel + getWeapon3());

        long result = db.update(WEAPON_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change wep3 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed wep3 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns weapon 3
    public int getWeapon3() {
        String query = "SELECT " + WEAPON_COLUMN_WEP3 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP3));
        }
        return -1;
    }

    //change weapon 4
    public void updateWeapon4(int nLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP4, nLevel + getWeapon4());

        long result = db.update(WEAPON_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change wep4 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed wep4 level to " + nLevel, Toast.LENGTH_SHORT).show();
        }
    }

    //returns weapon 4
    public int getWeapon4() {
        String query = "SELECT " + WEAPON_COLUMN_WEP4 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP4));
        }
        return -1;
    }

    //------------------------------------------------------------------------------------------------------
    //Pet
    //Insert pet
    public void initPet() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET_USED, -1);
        cv.put(PET_COLUMN_PET0_BOUGHT, 0);
        cv.put(PET_COLUMN_PET1_BOUGHT, 0);
        cv.put(PET_COLUMN_PET2_BOUGHT, 0);
        cv.put(PET_COLUMN_PET3_BOUGHT, 0);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet
    public void updatePetUsed(int nPet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //if pet is bought update pet
        int cHave = 0;
        if (nPet == 0) {
            cHave = getPet0Bought();
        }
        else if (nPet == 1) {
            cHave = getPet1Bought();
        }
        else if (nPet == 2) {
            cHave = getPet2Bought();
        }
        else if (nPet == 3) {
            cHave = getPet3Bought();
        }
        long result = -1;
        if (cHave == 1) {
            cv.put(PET_COLUMN_PET_USED, nPet);
            result = db.update(PET_TABLE_NAME, cv, null, null);
        }
        if (result == -1) {
            Toast.makeText(context, "Failed to change pet to " + nPet, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed pet to " + nPet, Toast.LENGTH_SHORT).show();
        }
    }

    //returns pet used
    public int getPetUsed() {
        String query = "SELECT " + PET_COLUMN_PET_USED + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET_USED));
        }
        return -1;
    }

    //change pet0 bought
    public void updatePet0Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //if pet is bought update pet
        cv.put(PET_COLUMN_PET0_BOUGHT, 1);

        long result = db.update(PET_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change pet0 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed pet0 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
    }

    //returns pet0 bought
    public int getPet0Bought() {
        String query = "SELECT " + PET_COLUMN_PET0_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET0_BOUGHT));
        }
        return -1;
    }


    //change pet1 bought
    public void updatePet1Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //if pet is bought update pet
        cv.put(PET_COLUMN_PET1_BOUGHT, 1);

        long result = db.update(PET_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change pet1 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed pet1 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
    }

    //returns pet1 bought
    public int getPet1Bought() {
        String query = "SELECT " + PET_COLUMN_PET1_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET1_BOUGHT));
        }
        return -1;
    }

    //change pet2 bought
    public void updatePet2Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //if pet is bought update pet
        cv.put(PET_COLUMN_PET2_BOUGHT, 1);

        long result = db.update(PET_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change pet2 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed pet2 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
    }

    //returns pet2 bought
    public int getPet2Bought() {
        String query = "SELECT " + PET_COLUMN_PET2_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET2_BOUGHT));
        }
        return -1;
    }

    //change pet
    public void updatePet3Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //if pet is bought update pet
        cv.put(PET_COLUMN_PET3_BOUGHT, 1);

        long result = db.update(PET_TABLE_NAME, cv, null, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to change pet3 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully changed pet3 bought to " + 1, Toast.LENGTH_SHORT).show();
        }
    }

    //returns pet3 bought
    public int getPet3Bought() {
        String query = "SELECT " + PET_COLUMN_PET3_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET3_BOUGHT));
        }
        return -1;
    }
}
