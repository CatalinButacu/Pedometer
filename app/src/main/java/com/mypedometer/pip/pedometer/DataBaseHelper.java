package com.mypedometer.pip.pedometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;

import java.util.Arrays;

public class DataBaseHelper extends SQLiteOpenHelper {

    enum DBTypes{
        USER_ESSENTIALS,
        USER_INFO,
        USER_STATS,
        CHALLENGE
    }
    //----------------------------------------------------------------------------------------------
    // Constructors
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableStatement = "";

        for (DBTypes types : DBTypes.values()) {
            createTableStatement += getDBCreateStatement(types);
            createTableStatement += "\n";
        }

        sqLiteDatabase.execSQL(createTableStatement);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //----------------------------------------------------------------------------------------------
    public boolean insertDataUser(UserModel user){

        if (user == null || user.getUserID() == null)
            return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean ok = true;
        long insert = -1;

        if( ok && ensureTableExist(DBTypes.USER_ESSENTIALS) && !checkIsUserExists(user, DBTypes.USER_ESSENTIALS))
        {
            // Insert user
            cv.put("UserID", user.getUserID());
            cv.put("Email", user.getEmail());
            cv.put("Phone", user.getPhone());
            cv.put("Password", user.getPassword());

            insert = db.insert(DBTypes.USER_ESSENTIALS.toString(), null, cv);
            ok  = ok && (insert == -1 ? false : true);

            cv.clear();
        }

        if(ok && ensureTableExist(DBTypes.USER_INFO) && !checkIsUserExists(user, DBTypes.USER_INFO))
        {
            // Insert user info
            cv.put("UserID", user.getUserID());
            cv.put("Nume", user.getNume());
            cv.put("Prenume", user.getPrenume());
            cv.put("Varsta", user.getVarsta());
            cv.put("Greutate", user.getGreutate());
            cv.put("Inaltime", user.getInaltime());
            cv.put("TipProfil", user.getIsPrivateProfile());

            insert = db.insert(DBTypes.USER_INFO.toString(), null, cv);

            ok = ok && (insert == -1 ? false : true);
            cv.clear();
        }

        // Insert user stats
        if (ok && ensureTableExist(DBTypes.USER_STATS) &&
                !checkIsUserExists(user, DBTypes.USER_STATS) && user.getUserStats() != null)
        {
            cv.put("UserID", user.getUserID());
            cv.put("TotalSteps", user.getTotalSteps());
            cv.put("TotalDistance", user.getTotalDistance());
            cv.put("TotalCalories", user.getTotalCalories());
            cv.put("DailySteps", Arrays.toString(user.getDailySteps()));
            cv.put("DailyDistance", Arrays.toString(user.getDailyDistance()));
            cv.put("DailyCalories", Arrays.toString(user.getDailyCalories()));
            cv.put("GoalSteps", user.getGoalDailySteps());
            cv.put("GoalDistance", user.getGoalDailyDistance());
            cv.put("GoalCalories", user.getGoalDailyCalories());
            cv.put("ChallengeSteps", Arrays.toString(user.getChallengeSteps()));
            cv.put("ChallengeDistance", Arrays.toString(user.getChallengeDistance()));
            cv.put("ChallengeCalories", Arrays.toString(user.getChallengeCalories()));

            insert = db.insert(DBTypes.USER_STATS.toString(), null, cv);

            ok = ok && (insert == -1 ? false : true);
            cv.clear();
        }

        return ok;
    }
    //----------------------------------------------------------------------------------------------
    public boolean insertDataChallenge(ChallengeModel challenge){

        if (challenge == null || challenge.getChallengeID() == null)
            return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean ok = true;
        long insert = -1;

        if(ok && ensureTableExist(DBTypes.CHALLENGE) && !checkIsChallengeExists(challenge, DBTypes.CHALLENGE))
        {
            // Insert challenge
            cv.put("ChallengeID", challenge.getChallengeID());
            cv.put("CreatorID", challenge.getCreatorID());
            cv.put("ChallengeName", challenge.getNameChallenge());
            cv.put("ChallengeStatus", challenge.getStatusChallenge().toString());
            cv.put("DateStart", challenge.getDateStart());
            cv.put("DateEnd", challenge.getDateEnd());
            cv.put("CandidatesID", challenge.getCandidatesID());

            insert = db.insert("CHALLENGE", null, cv);

            ok = ok && (insert == -1 ? false : true);
            cv.clear();
        }
        return ok;
    }
    //----------------------------------------------------------------------------------------------
    public void updateDataUser(UserModel user){
        String updateStatement = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void updateDataChallenge(ChallengeModel challenge){
        String updateStatement = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void deleteDataUser(String deleteStatement){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void deleteDataChallenge(String deleteStatement){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void selectDataUser(String selectStatement){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void selectDataChallenge(String selectStatement){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void createTable(DBTypes dbType){
        String createTableStatement = getDBCreateStatement(dbType);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(createTableStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void dropTable(DBTypes dbType){
        String dropTableStatement = "DROP TABLE IF EXISTS " + dbType.toString();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(dropTableStatement);
    }
    //----------------------------------------------------------------------------------------------
    public void specialStatementTable(DBTypes dbType,String specialTableStatement){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(specialTableStatement);
    }
    //----------------------------------------------------------------------------------------------
    public String getDBCreateStatement(DBTypes dbType)
    {
        String createTableStatement = "";
        switch (dbType){
            case USER_ESSENTIALS:
                createTableStatement = "CREATE TABLE IF NOT EXISTS USER_ESSENTIALS (\n" +
                        "  UserID INTEGER PRIMARY KEY,\n" +
                        "  Email TEXT,\n" +
                        "  Phone TEXT,\n" +
                        "  Password TEXT\n" +
                        ");\n";
                System.out.println("\n\ncreateTableStatement = " + createTableStatement);
                break;

            case USER_INFO:
                createTableStatement = "CREATE TABLE IF NOT EXISTS USER_INFO (\n" +
                        "  UserID TEXT PRIMARY KEY,\n" +
                        "  Nume TEXT,\n" +
                        "  Prenume TEXT,\n" +
                        "  Varsta INTEGER,\n" +
                        "  Greutate REAL,\n" +
                        "  Inaltime REAL,\n" +
                        "  TipProfil INTEGER,\n" +
                        "  FOREIGN KEY (UserID) REFERENCES USER(UserID)\n" +
                        ");\n";
                System.out.println("\n\ncreateTableStatement = " + createTableStatement);
                break;

            case USER_STATS:
                createTableStatement = "CREATE TABLE IF NOT EXISTS USER_STATS (\n" +
                        "  UserID INTEGER PRIMARY KEY,\n" +
                        "  TotalSteps INTEGER,\n" +
                        "  TotalDistance INTEGER,\n" +
                        "  TotalCalories INTEGER,\n" +
                        "  DailySteps TEXT,\n" +
                        "  DailyDistance TEXT,\n" +
                        "  DailyCalories TEXT,\n" +
                        "  GoalSteps INTEGER,\n" +
                        "  GoalDistance INTEGER,\n" +
                        "  GoalCalories INTEGER,\n" +
                        "  ChallengeSteps TEXT,\n" +
                        "  ChallengeDistance TEXT,\n" +
                        "  ChallengeCalories TEXT\n" +
                        ");\n";
                System.out.println("\n\ncreateTableStatement = " + createTableStatement);
                break;

            case CHALLENGE:
                createTableStatement = "CREATE TABLE IF NOT EXISTS CHALLENGE (\n" +
                        "  ChallengeID TEXT PRIMARY KEY,\n" +
                        "  CreatorID TEXT,\n" +
                        "  ChallengeName TEXT,\n" +
                        "  ChallengeStatus TEXT,\n" +
                        "  DateStart TEXT,\n" +
                        "  DateEnd TEXT,\n" +
                        "  CandidatesID TEXT\n" +
                        ");\n";
                System.out.println("\n\ncreateTableStatement = " + createTableStatement);
                break;

            default:
                createTableStatement = "SELECT name FROM sqlite_master WHERE type='table';\n";
                System.out.println("\n\ncreateTableStatement = " + createTableStatement);
                break;
    }
        return createTableStatement;
    }
    //----------------------------------------------------------------------------------------------
    public boolean ensureTableExist(DBTypes dbType) {
        boolean tableExists = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
        Cursor cursor = db.rawQuery(query, new String[]{dbType.toString()});

        if (cursor != null) {
            tableExists = cursor.getCount() > 0;
            cursor.close();
        }

        if (!tableExists) {
            String createStatement = getDBCreateStatement(dbType);
            db.execSQL(createStatement);
            tableExists = true;
        }

        return tableExists;
    }
    //----------------------------------------------------------------------------------------------
    public boolean checkIsUserExists(@NonNull UserModel user, DBTypes dbType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ dbType.toString() + " WHERE UserID = ?", new String[]{user.getUserID()});
        return (cursor.getCount() != 0) ? true : false;
    }
    //----------------------------------------------------------------------------------------------
    public boolean checkIsChallengeExists(@NonNull ChallengeModel challenge, DBTypes dbType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ dbType.toString() + " WHERE ChallengeID = ?", new String[]{challenge.getChallengeID()});
        return (cursor.getCount() != 0) ? true : false;
    }
    //----------------------------------------------------------------------------------------------
   public static void dropAllTables(SQLiteDatabase sqLiteDatabase) {

       String dropTableStatement = "";

       for (DBTypes types : DBTypes.values()) {
           dropTableStatement += "DROP TABLE IF EXISTS "+ types.toString();
           dropTableStatement += ";\n";
       }

       sqLiteDatabase.execSQL(dropTableStatement);
   }
   //----------------------------------------------------------------------------------------------
}
