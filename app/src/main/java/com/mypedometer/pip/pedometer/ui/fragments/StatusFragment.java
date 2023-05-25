package com.mypedometer.pip.pedometer.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.storage.LocalManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StatusFragment extends Fragment {
    private Handler mHandler;
    private Runnable mRunnable;

    public StatusFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View StatusView = inflater.inflate(R.layout.status_layout, container, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        String Date = dateFormat.format(calendar.getTime());

        // Initialize the handler and the runnable
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                updateUI();
                mHandler.postDelayed(this, 5000); // Schedule the next update after 5 seconds
            }
        };

        // Update the UI elements initially
        updateUI();

        // Set the current date
        TextView textData = StatusView.findViewById(R.id.currentDate);
        textData.setText(Date);

        return StatusView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Start the UI update
        mHandler.postDelayed(mRunnable, 0); // Start immediately
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop the UI update
        mHandler.removeCallbacks(mRunnable);
    }

    private void updateUI() {
        View view = getView();
        if (view == null) {
            return;
        }

        // Update the UI elements
        TextView textPasi = view.findViewById(R.id.steps);
        textPasi.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getTodaySteps()));

        ProgressBar pBar = view.findViewById(R.id.progressBar);
        final int GOAL = LocalManager.getInstance().getLocalUser().getGoalDailySteps();
        pBar.setProgress(LocalManager.getInstance().getLocalUser().getTodaySteps() * 100 / GOAL);

        TextView infoPBar = view.findViewById(R.id.infoProgressBar);
        infoPBar.setText(LocalManager.getInstance().getLocalUser().getTodaySteps() + "/" + GOAL);

        TextView textCalories = view.findViewById(R.id.Calories);
        textCalories.setText(LocalManager.getInstance().getLocalUser().getTodayCalories() + "  Kcal");

        TextView textDistancce = view.findViewById(R.id.Distance);
        textDistancce.setText(String.format("%.02f", LocalManager.getInstance().getLocalUser().getTodayDistance()) + " m");

        TextView textDailyAverageSteps = view.findViewById(R.id.dailyAverageSteps);
        textDailyAverageSteps.setText("Average activity: " + LocalManager.getInstance().getLocalUser().calculateAverageDailySteps() + " steps");

        TextView textDailyAverageDistance = view.findViewById(R.id.dailyAverageDistance);
        textDailyAverageDistance.setText("Average distance: " + LocalManager.getInstance().getLocalUser().calculateAverageDailyDistance() + " m");

        TextView textDailyAverageCalories = view.findViewById(R.id.dailyAverageCalories);
        textDailyAverageCalories.setText("Average calories: " + LocalManager.getInstance().getLocalUser().calculateAverageDailyCalories() + " kcal");

        ProgressBar mon = view.findViewById(R.id.monday);
        mon.setProgress(0);

        ProgressBar tue = view.findViewById(R.id.tuesday);
        tue.setProgress(100);

        ProgressBar wed = view.findViewById(R.id.wednesday);
        wed.setProgress(100);

        ProgressBar thu = view.findViewById(R.id.thursday);
        thu.setProgress(0);

        ProgressBar fri = view.findViewById(R.id.friday);
        fri.setProgress(100);

        ProgressBar sat = view.findViewById(R.id.saturday);
        sat.setProgress(100);

        ProgressBar sun = view.findViewById(R.id.sunday);
        sun.setProgress(0);

        // Refresh the Fragment's view
        view.invalidate();
    }
}
