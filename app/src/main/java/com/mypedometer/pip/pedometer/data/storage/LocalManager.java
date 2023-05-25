package com.mypedometer.pip.pedometer.data.storage;

import com.mypedometer.pip.pedometer.DataBaseHelper;
import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LocalManager {
    private static final LocalManager instance = new LocalManager();
    private DataBaseHelper database;
    private UserModel localUser;
    private List<UserModel> allUsers;
    private List<ChallengeModel> allChallenges;
    //----------------------------------------------------------------------------------------------
    // Singleton constructor
    public LocalManager() {
        //database = new DataBaseHelper(MainActivity.viewPager.getContext(), "PedometerDB.sqlite", null, 1);
        localUser = new UserModel();
        allUsers = new ArrayList<>();
        allChallenges = new ArrayList<>();

        // Start the periodic synchronization methods
        startSyncDB();
        startSyncStats();
    }
    //----------------------------------------------------------------------------------------------
    public static LocalManager getInstance() {
        return instance;
    }
    //----------------------------------------------------------------------------------------------
    public UserModel getLocalUser() {
        return localUser;
    }
    //----------------------------------------------------------------------------------------------
    public void updateLocalUser(UserModel user) {
        localUser.setUserID(user.getUserID());
        localUser.setFirstName(user.getFirstName());
        localUser.setLastName(user.getLastName());
        localUser.setGender(user.getGen());
        localUser.setVarsta(user.getVarsta());
        localUser.setGreutate(user.getGreutate());
        localUser.setInaltime(user.getInaltime());
        localUser.setFriends(user.getFriends());
        localUser.setEmail(user.getEmail());
        localUser.setPhone(user.getPhone());
        localUser.setPassword(user.getPassword());
        localUser.setIsPrivateProfile(user.getIsPrivateProfile());
        localUser.setUserStats(user.getUserStats());
    }
    //----------------------------------------------------------------------------------------------
    public List<UserModel> getAllUsers() {
        return allUsers;
    }
    //----------------------------------------------------------------------------------------------
    public List<ChallengeModel> getAllChallenges() {
        return allChallenges;
    }
    //----------------------------------------------------------------------------------------------
    private void syncWithDatabase() {
        // Logic for synchronizing the local data with the database
        // Example: database.sync(allUsers, allChallenges);
        System.out.println("Synchronizing with the database...");
    }
    //----------------------------------------------------------------------------------------------
    private void startSyncDB() {
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                syncWithDatabase();
            }
        };

        // Schedule the synchronization task to run every 1 hour
        long delay = 0;
        long period = 30 * 1000; // 30s in milliseconds
        timer.schedule(syncTask, delay, period);
    }
    //----------------------------------------------------------------------------------------------
    private void startSyncStats() {
        Timer timer = new Timer();
        TimerTask syncTask = new TimerTask() {
            @Override
            public void run() {
                Calendar current = Calendar.getInstance();
                int hour = current.get(Calendar.HOUR_OF_DAY);
                int minute = current.get(Calendar.MINUTE);
                int amPm = current.get(Calendar.AM_PM);

                if (hour == 0 && minute == 0 && amPm == Calendar.AM) {
                    localUser.updateStatsNextDay();
                }
            }
        };

        // Calculate the delay until the next 00:00 AM
        Calendar nextMidnight = Calendar.getInstance();
        nextMidnight.set(Calendar.HOUR_OF_DAY, 0);
        nextMidnight.set(Calendar.MINUTE, 0);
        nextMidnight.set(Calendar.SECOND, 0);
        nextMidnight.set(Calendar.MILLISECOND, 0);

        Date now = new Date();
        if (now.after(nextMidnight.getTime())) {
            nextMidnight.add(Calendar.DAY_OF_YEAR, 1);
        }

        long delay = nextMidnight.getTimeInMillis() - now.getTime();

        // Schedule the synchronization task to run at the next 00:00 AM
        timer.schedule(syncTask, delay);
    }
    //---------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
}

