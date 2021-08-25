package com.mobdeve.s11.group41.alienimpact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "UserDatabase.db";
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
        db.execSQL(query1 + query2 + query3 + query4 + query5 + query6);
        initScrap();
        initPlayerIcon();
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
    //Insert column scrap
    void initScrap() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_SCRAP_QUANTITY, 0);
        db.insert(PLAYER_TABLE_NAME, null, cv);
    }

    //adds the number of scrap
    void updateScrap(int nScrap) {
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
    int getScrap() {
        String query = "SELECT " + PLAYER_COLUMN_SCRAP_QUANTITY + " FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_SCRAP_QUANTITY));
        }
        return 0;
    }

    //Decreases the number of current scrap
    void removeScrap(int nScrap) {
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

    //add player icon
    void initPlayerIcon() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_ICON, 0);
        db.insert(PLAYER_TABLE_NAME, null, cv);
    }

    //change icon
    void updatePlayerIcon(int nIcon) {
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
    int getPlayerIcon() {
        String query = "SELECT " + PLAYER_COLUMN_ICON + " FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_ICON));
        }
        return 0;
    }

    //add player name
    void initPlayerName() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_USERNAME, "Bob");
        db.insert(PLAYER_TABLE_NAME, null, cv);
    }

    //change player name
    void updatePlayerName(String sName) {
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
    String getPlayerName() {
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
    //add music volume
    void initMusicVolume() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OPTION_COLUMN_MUSIC_VOLUME, 100);
        db.insert(OPTION_TABLE_NAME, null, cv);
    }

    //change music volume
    void updateMusicVolume(int nVol) {
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
    int getMusicVolume() {
        String query = "SELECT " + OPTION_COLUMN_MUSIC_VOLUME + " FROM " + OPTION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(OPTION_COLUMN_MUSIC_VOLUME));
        }
        return 100;
    }

    //add game volume
    void initGameVolume() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OPTION_COLUMN_GAME_VOLUME, 100);
        db.insert(OPTION_TABLE_NAME, null, cv);
    }

    //change music volume
    void updateGameVolume(int nVol) {
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
    int getGameVolume() {
        String query = "SELECT " + OPTION_COLUMN_GAME_VOLUME + " FROM " + OPTION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(OPTION_COLUMN_GAME_VOLUME));
        }
        return 100;
    }

    //------------------------------------------------------------------------------------------------------
    //Buff
    //add tap level
    void initTapLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_TAP_LEVEL, 100);
        db.insert(BUFF_TABLE_NAME, null, cv);
    }

    //change tap level
    void updateTapLevel(int nLevel) {
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
    int getTapLevel() {
        String query = "SELECT " + BUFF_COLUMN_TAP_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_TAP_LEVEL));
        }
        return 0;
    }

    //add hold level
    void initHoldLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_HOLD_LEVEL, 100);
        db.insert(BUFF_TABLE_NAME, null, cv);
    }

    //change tap level
    void updateHoldLevel(int nLevel) {
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
    int getHoldLevel() {
        String query = "SELECT " + BUFF_COLUMN_HOLD_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_HOLD_LEVEL));
        }
        return 0;
    }

    //add swipe level
    void initSwipeLevel() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUFF_COLUMN_SWIPE_LEVEL, 100);
        db.insert(BUFF_TABLE_NAME, null, cv);
    }

    //change swipe level
    void updateSwipeLevel(int nLevel) {
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
    int getSwipeLevel() {
        String query = "SELECT " + BUFF_COLUMN_SWIPE_LEVEL + " FROM " + BUFF_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(BUFF_COLUMN_SWIPE_LEVEL));
        }
        return 0;
    }

    //------------------------------------------------------------------------------------------------------
    //Game
    //add stage
    void initGameStage() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_STAGE, 1);
        db.insert(GAME_TABLE_NAME, null, cv);
    }

    //add 1 to stage
    void updateGameStage() {
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
    int getGameStage() {
        String query = "SELECT " + GAME_COLUMN_CURR_STAGE + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_STAGE));
        }
        return 1;
    }

    //add round
    void initGameRound() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_ROUND, 1);
        db.insert(GAME_TABLE_NAME, null, cv);
    }

    //game round add 1
    void updateGameRound() {
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
    int getGameRound() {
        String query = "SELECT " + GAME_COLUMN_CURR_ROUND + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_ROUND));
        }
        return 1;
    }

    //add enemy hp
    void initGameHP() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GAME_COLUMN_CURR_HP, 0);
        db.insert(GAME_TABLE_NAME, null, cv);
    }

    //change enemy max hp
    void updateGameHP(int nHP) {
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
    int getGameHP() {
        String query = "SELECT " + GAME_COLUMN_CURR_HP + " FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(GAME_COLUMN_CURR_HP));
        }
        return 0;
    }

    //Decreases the number of current scrap
    void removeGameHP(int nHP) {
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

    //------------------------------------------------------------------------------------------------------
    //Weapon
    //add weapon 0
    void initWeapon0() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP0, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 0
    void updateWeapon0(int nLevel) {
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
    int getWeapon0() {
        String query = "SELECT " + WEAPON_COLUMN_WEP0 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP0));
        }
        return 0;
    }

    //add weapon 1
    void initWeapon1() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP1, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 1
    void updateWeapon1(int nLevel) {
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
    int getWeapon1() {
        String query = "SELECT " + WEAPON_COLUMN_WEP1 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP1));
        }
        return 0;
    }

    //add weapon 2
    void initWeapon2() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP2, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 2
    void updateWeapon2(int nLevel) {
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
    int getWeapon2() {
        String query = "SELECT " + WEAPON_COLUMN_WEP2 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP2));
        }
        return 0;
    }

    //add weapon 3
    void initWeapon3() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP3, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 3
    void updateWeapon3(int nLevel) {
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
    int getWeapon3() {
        String query = "SELECT " + WEAPON_COLUMN_WEP3 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP3));
        }
        return 0;
    }

    //add weapon 4
    void initWeapon4() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WEAPON_COLUMN_WEP4, 0);
        db.insert(WEAPON_TABLE_NAME, null, cv);
    }

    //change weapon 4
    void updateWeapon4(int nLevel) {
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
    int getWeapon4() {
        String query = "SELECT " + WEAPON_COLUMN_WEP4 + " FROM " + WEAPON_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(WEAPON_COLUMN_WEP4));
        }
        return 0;
    }

    //------------------------------------------------------------------------------------------------------
    //Pet
    //add pet used
    void initPetUsed() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET_USED, -1);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet
    void updatePetUsed(int nPet) {
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
    int getPetUsed() {
        String query = "SELECT " + PET_COLUMN_PET_USED + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET_USED));
        }
        return -1;
    }

    //add pet0
    void initPet0Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET0_BOUGHT, 0);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet0 bought
    void updatePet0Bought() {
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
    int getPet0Bought() {
        String query = "SELECT " + PET_COLUMN_PET0_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET0_BOUGHT));
        }
        return 0;
    }

    //add pet1
    void initPet1Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET1_BOUGHT, 0);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet1 bought
    void updatePet1Bought() {
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
    int getPet1Bought() {
        String query = "SELECT " + PET_COLUMN_PET1_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET1_BOUGHT));
        }
        return 0;
    }

    //add pet2
    void initPet2Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET2_BOUGHT, 0);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet2 bought
    void updatePet2Bought() {
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
    int getPet2Bought() {
        String query = "SELECT " + PET_COLUMN_PET2_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET2_BOUGHT));
        }
        return 0;
    }

    //add pet3
    void initPet3Bought() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PET_COLUMN_PET3_BOUGHT, 0);
        db.insert(PET_TABLE_NAME, null, cv);
    }

    //change pet
    void updatePet3Bought() {
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
    int getPet3Bought() {
        String query = "SELECT " + PET_COLUMN_PET3_BOUGHT + " FROM " + PET_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(PET_COLUMN_PET3_BOUGHT));
        }
        return 0;
    }
}
