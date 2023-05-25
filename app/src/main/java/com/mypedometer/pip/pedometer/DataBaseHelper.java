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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the class that helps link the locally stored data to a DataBase.
 * The class provides methods to insert, update, and delete data for UserModel and ChallengeModel objects.
 * There are also methods to execute custom SQL statements, create and drop tables, and fetch data from the database.
 */
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
        //this.updateDataUsers(this.getReadableDatabase());
        //this.updateDataChallenges(this.getReadableDatabase());
    }
    //----------------------------------------------------------------------------------------------

    /**
     * This method is called when the database is created for the first time. It executes SQL statements to create the required tables based on the DBTypes enumeration.
     * @param sqLiteDatabase
     */
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

    /**
     * This method is called when the database needs to be upgraded to a new version. It can be implemented to handle the migration of data from the old schema to the new schema.
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO:
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
            cv.put("Nume", user.getLastName());
            cv.put("Prenume", user.getFirstName());
            cv.put("Gen", user.getGen());
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

    /**
     * This method returns the SQL statement for creating a specific table based on the DBTypes enumeration.
     * @param dbType
     * @return
     */
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
                        "  Gen TEXT,\n" +
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

    /**
     * This method checks if a table exists in the database. If it doesn't exist, it executes the SQL statement to create the table.
     * @param dbType
     * @return
     */
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

    /**
     * This method checks if a user already exists in the respective table.
     * @param user
     * @param dbType
     * @return
     */
    public boolean checkIsUserExists(@NonNull UserModel user, DBTypes dbType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ dbType.toString() + " WHERE UserID = ?", new String[]{user.getUserID()});
        return (cursor.getCount() != 0) ? true : false;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * This method checks if a challenge already exists in the respective table.
     * @param challenge
     * @param dbType
     * @return
     */
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
    public boolean updateDataUsers(SQLiteDatabase sqLiteDatabase){
        String updateStatement = "";




        sqLiteDatabase.execSQL(updateStatement);
        return true;
    }
   //----------------------------------------------------------------------------------------------
    public boolean updateDataChallenges(SQLiteDatabase sqLiteDatabase){
        String updateStatement = "";




        sqLiteDatabase.execSQL(updateStatement);
        return true;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * This method retrieves data from the corresponding table and return a list of UserModel object.
     * @param sqLiteDatabase
     * @return
     */
    public List<UserModel> getUsers(SQLiteDatabase sqLiteDatabase){
        boolean tableExists = ensureTableExist(DBTypes.USER_ESSENTIALS);
        if(!tableExists)
            return null;

        tableExists = ensureTableExist(DBTypes.USER_INFO);
        if(!tableExists)
            return null;

        tableExists = ensureTableExist(DBTypes.USER_STATS);
        if(!tableExists)
            return null;

        Boolean iterator = true;
        List<UserModel> users = new ArrayList<>();

        String selectStatementUserEssentials = "SELECT * FROM USER_ESSENTIALS";
        String selectStatementUserInfo       = "SELECT * FROM USER_INFO";
        String selectStatementUserStats      = "SELECT * FROM USER_STATS";

        Cursor cursor = sqLiteDatabase.rawQuery(selectStatementUserEssentials, null);
        Cursor cursorInfo = sqLiteDatabase.rawQuery(selectStatementUserInfo, null);
        Cursor cursorStats = sqLiteDatabase.rawQuery(selectStatementUserStats, null);

        if(cursor.moveToFirst()){
            do{
                UserModel user = new UserModel();

                user.setUserID(cursor.getString(0));
                user.setEmail(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                user.setFirstName(cursorInfo.getString(1));
                user.setLastName(cursorInfo.getString(2));
                user.setGender(cursorInfo.getString(3));
                user.setVarsta(Integer.parseInt(cursorInfo.getString(4)));
                user.setGreutate(Float.parseFloat(cursorInfo.getString(5)));
                user.setInaltime(Float.parseFloat(cursorInfo.getString(6)));
                user.setIsPrivateProfile(Boolean.parseBoolean(cursorInfo.getString(7)));

                user.setTotalSteps(Integer.parseInt(cursorStats.getString(1)));
                user.setTotalDistance(Float.parseFloat(cursorStats.getString(2)));
                user.setTotalCalories(Float.parseFloat(cursorStats.getString(3)));
                user.setDailySteps(cursorStats.getString(4));
                user.setDailyDistance(cursorStats.getString(5));
                user.setDailyCalories(cursorStats.getString(6));
                user.setGoalDailySteps(Integer.valueOf(cursorStats.getString(7)));
                user.setGoalDailyDistance(Float.valueOf(cursorStats.getString(8)));
                user.setGoalDailyCalories(Float.valueOf(cursorStats.getString(9)));
                user.setChallengeSteps(cursorStats.getString(10));
                user.setChallengeDistance(cursorStats.getString(11));
                user.setChallengeCalories(cursorStats.getString(12));

                //insert user into app
                users.add(user);

                //update cursor and iterator
                if(cursor.isLast())
                    iterator = false;
                else{
                    cursor.moveToNext();
                    cursorInfo.moveToNext();
                    cursorStats.moveToNext();
                }
            }while(iterator);
        }
        cursor.close();
        return users;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * This method retrieves data from the corresponding table and returns a list of ChallengeModel object.
     * @param sqLiteDatabase
     * @return
     */
    public List<ChallengeModel> getChallanges(SQLiteDatabase sqLiteDatabase){
        List<ChallengeModel> challenges = new ArrayList<>();
        String selectStatement = "SELECT * FROM CHALLENGE";
        Cursor cursor = sqLiteDatabase.rawQuery(selectStatement, null);
        if(cursor.moveToFirst()){
            do{
                ChallengeModel challenge = new ChallengeModel();
                challenge.setChallengeID(cursor.getString(0));
                challenge.setCreatorID(cursor.getString(1));
                challenge.setNameChallenge(cursor.getString(2));
                challenge.setStatusChallenge(cursor.getString(3));
                challenge.setDateStart(cursor.getString(4));
                challenge.setDateEnd(cursor.getString(5));
                challenge.setCandidatesID(cursor.getString(6));
                challenges.add(challenge);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return challenges;
    }
    //----------------------------------------------------------------------------------------------
}
