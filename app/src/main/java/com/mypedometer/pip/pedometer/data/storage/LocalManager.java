package com.mypedometer.pip.pedometer.data.storage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;
import com.mypedometer.pip.pedometer.sensors.StepListener;
import com.mypedometer.pip.pedometer.sensors.StepService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LocalManager extends AppCompatActivity implements StepListener {
    private static final String TAG = "PedometerApp.LocalManager";
    private StepService stepService;
    private boolean isServiceBound = false;
    private UserModel localUser;
    private List<UserModel> allUsers;
    private List<ChallengeModel> allChallenges;

    //----------------------------------------------------------------------------------------------
    // Singleton constructor
    public LocalManager() {
        localUser = new UserModel();
        allUsers = new ArrayList<>();
        allChallenges = new ArrayList<>();

        // Start the periodic synchronization methods
        // startService(new Intent(MainActivity.viewPager.getContext(), StepService.class));
        startSyncDB();
        startSyncStats();
    }

    private static final LocalManager instance = new LocalManager();

    public static LocalManager getInstance() {
        return instance;
    }
    //----------------------------------------------------------------------------------------------

    public UserModel getLocalUser() {
        return localUser;
    }

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

    public List<UserModel> getAllUsers() {
        return allUsers;
    }

    public List<ChallengeModel> getAllChallenges() {
        return allChallenges;
    }

    private void syncWithDatabase() {
        // Logic for synchronizing the local data with the database
        // Example: database.sync(allUsers, allChallenges);
        System.out.println("Synchronizing with the database...");
    }

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

    //----------------------------------------------------------------------------------------------
    // StepListener implementation
    @Override
    public void onStep() {
        // Step detected, update the daily steps count in the userModel
        localUser.incrementSteps();
    }

    //----------------------------------------------------------------------------------------------
    // StepService connection and lifecycle methods
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService.StepBinder binder = (StepService.StepBinder) service;
            stepService = binder.getService();
            stepService.addStepListener(LocalManager.this);
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            stepService = null;
            isServiceBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bindStepService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindStepService();
    }

    private void bindStepService() {
        Intent intent = new Intent(this, StepService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindStepService() {
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindStepService();
    }
}
