package com.mypedometer.pip.pedometer;

import static com.mypedometer.pip.pedometer.DataBaseHelper.dropAllTables;

import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mypedometer.pip.pedometer.ui.fragments.ChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment;
import com.mypedometer.pip.pedometer.ui.fragments.StatusFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ViewChallengeFragment;
import com.mypedometer.pip.pedometer.ui.main.SectionsPagerAdapter;
import com.mypedometer.pip.pedometer.databinding.ActivityMainBinding;

import java.util.Objects;

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
                    getSupportFragmentManager().beginTransaction().add(fragmentChallenge,"").commit();
                }
                if (position == 2 && fragmentChangedProfile) {
                    getSupportFragmentManager().beginTransaction().add(fragmentProfile,"").commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        initDatabase();
        //dropAllTables(db.getWritableDatabase());
    }

    public static void changeChallengeFragment(Fragment fragment, Fragment newFragment) {
        fragmentChallenge = newFragment;
        fragmentChangedChallenge = true;
        FragmentTransaction ft = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.replace(R.id.fragment_container, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    public static void changeProfileFragment(Fragment fragment, Fragment newFragment) {
        fragmentProfile = newFragment;
        fragmentChangedProfile = true;
        FragmentTransaction ft = fragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.replace(R.id.fragment_container, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    void initDatabase() {
        db = new DataBaseHelper(MainActivity.this, "PedometerDB.sqlite", null, 1);
        //db.createTable(Database);
    }
}


