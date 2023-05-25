package com.mypedometer.pip.pedometer;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mypedometer.pip.pedometer.data.storage.LocalManager;
import com.mypedometer.pip.pedometer.sensors.StepService;
import com.mypedometer.pip.pedometer.ui.fragments.ChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.CreateAccountFragment;
import com.mypedometer.pip.pedometer.ui.fragments.CreateChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.EditProfileFragment;
import com.mypedometer.pip.pedometer.ui.main.LoadingScreenFragment;
import com.mypedometer.pip.pedometer.ui.fragments.LoginFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ViewChallengeFragment;
import com.mypedometer.pip.pedometer.ui.main.WelcomeDialogFragment;
import com.mypedometer.pip.pedometer.ui.main.SectionsPagerAdapter;
import com.mypedometer.pip.pedometer.databinding.ActivityMainBinding;

import java.util.Objects;

/**
 * The MainActivity class is the main activity of the application.
 * It is responsible for managing the main user interface and handling navigation between fragments.
 */
public class MainActivity extends AppCompatActivity {


    static Fragment fragmentChallenge = new Fragment();
    static Fragment fragmentProfile = new Fragment();
    static boolean fragmentChangedChallenge = false;
    static boolean fragmentChangedProfile = false;
    static FragmentManager fragmentManager;
    @SuppressLint("StaticFieldLeak")
    static public ActivityMainBinding binding;
    static public ViewPager viewPager;
    @SuppressLint("StaticFieldLeak")
    static public SectionsPagerAdapter sectionsPagerAdapter;
    private View loadingScreen;
    static public DataBaseHelper db;
    /**
     * Called when the activity is starting. Initializes the activity and sets up the user interface.
     *
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoadingScreen();
        new LocalManager();
        startService(new Intent(this, StepService.class));


        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        //CREARE CANAL PENTRU RULARE APLICATIE
        try {
            // Create the normal notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String normalChannelId = "NormalNotification";
                String normalChannelName = "Normal Notification";
                int normalChannelImportance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel normalChannel = new NotificationChannel(normalChannelId, normalChannelName, normalChannelImportance);
                NotificationManager manager1 = getSystemService(NotificationManager.class);

                if (manager1 != null)
                    manager1.createNotificationChannel(normalChannel);
            }

            // Create the persistent information notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String infoChannelId = "PersistentInfo";
                String infoChannelName = "Persistent Information";
                int infoChannelImportance = NotificationManager.IMPORTANCE_LOW;

                NotificationChannel infoChannel = new NotificationChannel(infoChannelId, infoChannelName, infoChannelImportance);
                NotificationManager manager = getSystemService(NotificationManager.class);

                if (manager != null)
                    manager.createNotificationChannel(infoChannel);


                // Example usage: Create a normal notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NormalNotification")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Normal Notification")
                        .setContentText("This is a normal notification.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(1, builder.build());

                // Example usage: Create a persistent information notification
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, "PersistentInfo")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Daily Steps")
                        .setContentText("You have taken 10,000 steps today.")
                        .setPriority(NotificationCompat.PRIORITY_LOW);

                notificationManager.notify(2, builder2.build());
            }

        } catch (Exception e) {
            System.out.println("CANALUL NU A PUTUT FI CREAT");
        }

        //TRANZITIE DINTRE TAB-URI
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = new Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                if(position == 0){
                    Fragment fragment1 = ((SectionsPagerAdapter) Objects.requireNonNull(viewPager.getAdapter())).getItem(0);
                    getSupportFragmentManager().beginTransaction().show(fragment1).commit();
                }
                if(position == 1 && fragmentChangedChallenge){
                    if(fragmentChallenge instanceof ChallengeFragment){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChallengeFragment()).commit();
                    }else{
                        getSupportFragmentManager().beginTransaction().add(fragmentChallenge,"").commit();
                    }
                }
                if (position == 2 && fragmentChangedProfile) {
                    if(fragmentProfile instanceof ProfileFragment){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                    }else{
                        getSupportFragmentManager().beginTransaction().add(fragmentProfile,"").commit();
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        initDatabase();
        //dropAllTables(db.getWritableDatabase());
        performLoadingTask();
    }

    /**
     * Changes the challenge fragment with a new fragment.
     *
     * @param fragment     The current fragment.
     * @param newFragment  The new fragment to replace the current fragment.
     */
    public static void changeChallengeFragment(Fragment fragment, Fragment newFragment) {
        fragmentChallenge = newFragment;
        fragmentChangedChallenge = true;
        FragmentTransaction ft = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.replace(R.id.fragment_container, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    /**
     * Changes the profile fragment with a new fragment.
     *
     * @param fragment     The current fragment.
     * @param newFragment  The new fragment to replace the current fragment.
     */
    public static void changeProfileFragment(Fragment fragment, Fragment newFragment) {
        fragmentProfile = newFragment;
        fragmentChangedProfile = true;
        FragmentTransaction ft = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.replace(R.id.fragment_container, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    /**
     * Handles the back button press. Checks the current fragment and performs the necessary action.
     */
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof EditProfileFragment){
            changeProfileFragment(currentFragment, new ProfileFragment());
        }
        if (currentFragment instanceof CreateAccountFragment){
            changeProfileFragment(currentFragment, new LoginFragment());
        }
        if (currentFragment instanceof ViewChallengeFragment){
            changeChallengeFragment(currentFragment, new ChallengeFragment());
        }
        if (currentFragment instanceof CreateChallengeFragment){
            changeChallengeFragment(currentFragment, new ChallengeFragment());
        }
    }
    /**
     * Initializes the database by creating a new instance of the DataBaseHelper class.
     */
    void initDatabase() {
        db = new DataBaseHelper(MainActivity.this, "PedometerDB.sqlite", null, 1);
        //db.createTable(Database);
    }
    private void showLoadingScreen() {
        // Inflate the loading screen layout
        LayoutInflater inflater = getLayoutInflater();
        loadingScreen = inflater.inflate(R.layout.loading_screen, null);

        // Add the loading screen to the root view of the activity
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(loadingScreen);

        // Show the loading screen fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new LoadingScreenFragment());
        ft.addToBackStack(null);
        ft.commit();
        System.out.println("Added loading screen");
    }

    private void hideLoadingScreen() {
        // Remove the loading screen fragment
        Fragment loadingScreenFragment = getSupportFragmentManager().findFragmentByTag(LoadingScreenFragment.TAG);
        if (loadingScreenFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(loadingScreenFragment);
            ft.commit();
        }

        // Remove the loading screen layout from the root view of the activity
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.removeView(loadingScreen);
        System.out.println("Hidden loading screen");

        WelcomeDialogFragment welcomeDialogFragment = new WelcomeDialogFragment();
        welcomeDialogFragment.show(getSupportFragmentManager(), WelcomeDialogFragment.TAG);

    }


    private void performLoadingTask() {
        // Show the loading screen
        showLoadingScreen();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate a time-consuming task
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Hide the loading screen and show the main content
                        hideLoadingScreen();
                        showMainContent();
                    }
                });

                // Remove the LoadingScreenFragment from the fragment manager
                Fragment loadingScreenFragment = getSupportFragmentManager().findFragmentByTag(LoadingScreenFragment.TAG);
                if (loadingScreenFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(loadingScreenFragment).commit();
                }
            }
        }).start();
    }


    private void showMainContent() {
        // Enable/disable views or perform any UI-related tasks

        // Example: Enable the TabLayout and ViewPager
        binding.tabs.setEnabled(true);
        viewPager.setEnabled(true);

    }

}


