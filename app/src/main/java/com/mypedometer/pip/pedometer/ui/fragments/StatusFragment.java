package com.mypedometer.pip.pedometer.ui.fragments;

import android.hardware.Sensor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;
import static com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This is the class that generates the UI for the current status (such as goals and achievements) of the user when called.
 */
public class StatusFragment extends Fragment {
    public StatusFragment() {
        // Required empty public constructor
    }
    static final int STEPS = 3000;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View StatusView = inflater.inflate(R.layout.status_layout, container, false);

        final Integer CALORIES = 231;
        String MSGCALORIES = CALORIES + "  Kcal";

        final Integer DISTANCE = 985;
        String MSGDISTANCE = DISTANCE + "  m";

        final int DAILYAVERAGESTEPS = 7000;
        String MSGDAILYAVERAGESTEPS = "Average activity: " + DAILYAVERAGESTEPS + " steps";

        final int DAILYAVERAGEDISTANCE = 8000;
        String MSGDAILYAVERAGEDISTANCE = "Average distance: " + DAILYAVERAGEDISTANCE + " m";

        final int DAILYAVERAGECALORIES = 350;
        String MSGDAILYAVERAGECALORIES = "Average calories: " + DAILYAVERAGECALORIES + " kcal";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        String Date = dateFormat.format(calendar.getTime());

        final int GOAL;
        if(ProfileFragment.user != null){
            GOAL = ProfileFragment.user.getGoalDailySteps();
        }
        else{
            //valoare default daca user-ul nu a fost initializat
            GOAL = 8000;
        }
        //fie 100(Goal atins), fie 0
        final int MONDAY = 0;
        final int TUESDAY = 100;
        final int WEDNESDAY = 100;
        final int THURSDAY = 0;
        final int FRIDAY = 100;
        final int SATURDAY = 100;
        final int SUNDAY = 0;



        //afisez data curenta dateFormat
        TextView textData = StatusView.findViewById(R.id.currentDate);
        textData.setText(Date);

        // afisez pasii in textView-ul pt steps
        TextView textPasi = StatusView.findViewById(R.id.steps);
        textPasi.setText(Integer.toString(STEPS));

        //detalii progress bar
        ProgressBar pBar = StatusView.findViewById(R.id.progressBar);
        pBar.setProgress(STEPS*100/GOAL);

        //afisare detalii progress bar
        TextView infoPBar = StatusView.findViewById(R.id.infoProgressBar);
        infoPBar.setText(STEPS + "/" + GOAL);

        // afisez in textView-ul pt calorii mesajul MSGCALORIES
        TextView textCalories = StatusView.findViewById(R.id.Calories);
        textCalories.setText(MSGCALORIES);

        // afisez in textView-ul pt distanta mesajul MSGDISTANCE
        TextView textDistancce = StatusView.findViewById(R.id.Distance);
        textDistancce.setText(MSGDISTANCE);

        // afisez in textView-ul pt daily average steps mesajul MSGDAILYAVERAGESTEPS
        TextView textDailyAverageSteps = StatusView.findViewById(R.id.dailyAverageSteps);
        textDailyAverageSteps.setText(MSGDAILYAVERAGESTEPS);

        // afisez in textView-ul pt daily average distance mesajul MSGDAILYAVERAGEDISTANCE
        TextView textDailyAverageDistance = StatusView.findViewById(R.id.dailyAverageDistance);
        textDailyAverageDistance.setText(MSGDAILYAVERAGEDISTANCE);

        // afisez in textView-ul pt daily average calories mesajul MSGDAILYAVERAGECALORIES
        TextView textDailyAverageCalories = StatusView.findViewById(R.id.dailyAverageCalories);
        textDailyAverageCalories.setText(MSGDAILYAVERAGECALORIES);

        //setez progresul pentru fiecare zi
        ProgressBar mon = StatusView.findViewById(R.id.monday);
        mon.setProgress(MONDAY);

        ProgressBar tue = StatusView.findViewById(R.id.tuesday);
        tue.setProgress(TUESDAY);

        ProgressBar wed = StatusView.findViewById(R.id.wednesday);
        wed.setProgress(WEDNESDAY);

        ProgressBar thu = StatusView.findViewById(R.id.thursday);
        thu.setProgress(THURSDAY);

        ProgressBar fri = StatusView.findViewById(R.id.friday);
        fri.setProgress(FRIDAY);

        ProgressBar sat = StatusView.findViewById(R.id.saturday);
        sat.setProgress(SATURDAY);

        ProgressBar sun = StatusView.findViewById(R.id.sunday);
        sun.setProgress(SUNDAY);

        return StatusView;
    }
}
