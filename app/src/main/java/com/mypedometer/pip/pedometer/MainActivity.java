package com.mypedometer.pip.pedometer;

import static com.mypedometer.pip.pedometer.DataBaseHelper.dropAllTables;

import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mypedometer.pip.pedometer.ui.fragments.ChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.CreateAccountFragment;
import com.mypedometer.pip.pedometer.ui.fragments.CreateChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.EditProfileFragment;
import com.mypedometer.pip.pedometer.ui.fragments.LoginFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ViewChallengeFragment;
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

    static public DataBaseHelper db;
    /**
     * Called when the activity is starting. Initializes the activity and sets up the user interface.
     *
     * @param savedInstanceState The saved instance state Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        try {
            //creare canal notifcare
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("Login Notification", "Login Notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);

                if (manager != null)
                    manager.createNotificationChannel(channel);
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
        dropAllTables(db.getWritableDatabase());
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
}


