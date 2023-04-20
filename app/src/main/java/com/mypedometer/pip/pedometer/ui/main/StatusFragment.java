package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.mypedometer.pip.pedometer.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StatusFragment extends Fragment {

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.status_layout, container, false);

        final int STEPS = 5000;

        final double CALORIES = 10.3;
        String MSGCALORIES = CALORIES + "  Kcal";

        final double DISTANCE = 39;
        String MSGDISTANCE = DISTANCE + "  m";

        final int TIME = 3;
        String MSGTIME = TIME + "  min";

        final int SPEED = 1;
        String MSGSPEED = SPEED + "  Km/h";

        final int DAILYAVERAGE = 3000;
        String MSGDAILYAVERAGE = "Daily average: " + DAILYAVERAGE;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        String Date = dateFormat.format(calendar.getTime());

        final int GOAL = 10000;
        final int MONDAY = 10;
        final int TUESDAY = 60;
        final int WEDNESDAY = 80;
        final int THURSDAY = 30;
        final int FRIDAY = 50;
        final int SATURDAY = 50;
        final int SUNDAY = 100;



        //afisez data curenta dateFormat
        TextView textData = view.findViewById(R.id.currentDate);
        textData.setText(Date);

        // afisez pasii in textView-ul pt steps
        TextView textPasi = view.findViewById(R.id.steps);
        textPasi.setText(Integer.toString(STEPS));

        //detalii progress bar
        ProgressBar pBar = view.findViewById(R.id.progressBar);
        pBar.setProgress(STEPS*100/GOAL);

        //afisare detalii progress bar
        TextView infoPBar = view.findViewById(R.id.infoProgressBar);
        infoPBar.setText(STEPS + "/" + GOAL);


        // afisez in textView-ul pt calorii mesajul MSGCALORIES
        TextView textCalories = view.findViewById(R.id.Calories);
        textCalories.setText(MSGCALORIES);

        // afisez in textView-ul pt distanta mesajul MSGDISTANCE
        TextView textDistancce = view.findViewById(R.id.Distance);
        textDistancce.setText(MSGDISTANCE);

        // afisez in textView-ul pt timp mesajul MSGTIME
        TextView textTime = view.findViewById(R.id.Time);
        textTime.setText(MSGTIME);

        // afisez in textView-ul pt speed mesajul MSGSPEED
        TextView textSpeed = view.findViewById(R.id.Speed);
        textSpeed.setText(MSGSPEED);

        // afisez in textView-ul pt daile average mesajul MSGDAILYAVERAGE
        TextView textDailyAverage = view.findViewById(R.id.dailyAverage);
        textDailyAverage.setText(MSGDAILYAVERAGE);

        //setez progresul pentru fiecare zi
        ProgressBar mon = view.findViewById(R.id.monday);
        mon.setProgress(MONDAY);

        ProgressBar tue = view.findViewById(R.id.tuesday);
        tue.setProgress(TUESDAY);

        ProgressBar wed = view.findViewById(R.id.wednesday);
        wed.setProgress(WEDNESDAY);

        ProgressBar thu = view.findViewById(R.id.thursday);
        thu.setProgress(THURSDAY);

        ProgressBar fri = view.findViewById(R.id.friday);
        fri.setProgress(FRIDAY);

        ProgressBar sat = view.findViewById(R.id.saturday);
        sat.setProgress(SATURDAY);

        ProgressBar sun = view.findViewById(R.id.sunday);
        sun.setProgress(SUNDAY);

        return view;
    }
}
