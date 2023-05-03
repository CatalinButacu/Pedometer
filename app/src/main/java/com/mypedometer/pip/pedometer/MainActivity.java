package com.mypedometer.pip.pedometer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mypedometer.pip.pedometer.ui.main.SectionsPagerAdapter;
import com.mypedometer.pip.pedometer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static public ActivityMainBinding binding;
    static public ViewPager viewPager;
    static public SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    }
}


