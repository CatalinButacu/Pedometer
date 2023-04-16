package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        // TODO: initialize views and data
        /*
         * modification from main for first fragment
         *
        private final int STEPS = 5000;

        private final double CALORIES = 10.3;
        private String MSGCALORIES = CALORIES + "  Kcal";

        private final double DISTANCE = 39;
        private String MSGDISTANCE = DISTANCE + "  m";

        private final int TIME = 3;
        private String MSGTIME = TIME + "  min";

        private final int SPEED = 1;
        private String MSGSPEED = SPEED + "  Km/h";

        private final int DAILYAVERAGE = 3000;
        private String MSGDAILYAVERAGE = "Daily average: " + DAILYAVERAGE;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        String Date = dateFormat.format(calendar.getTime());
        private final int GOAL = 10000;
        private final int MONDAY = 10;
        private final int TUESDAY = 60;
        private final int WEDNESDAY = 80;
        private final int THURSDAY = 30;
        private final int FRIDAY = 50;
        private final int SATURDAY = 50;
        private final int SUNDAY = 100;



        //afisez data curenta dateFormat
        TextView textData = findViewById(R.id.currentDate);
        textData.setText(Date);

        // afisez pasii in textView-ul pt steps
        TextView textPasi = findViewById(R.id.steps);
        textPasi.setText(Integer.toString(STEPS));

        //detalii progress bar
        ProgressBar pBar = findViewById(R.id.progressBar);
        pBar.setProgress(STEPS*100/GOAL);

        //afisare detalii progress bar
        TextView infoPBar = findViewById(R.id.infoProgressBar);
        infoPBar.setText(STEPS + "/" + GOAL);


        // afisez in textView-ul pt calorii mesajul MSGCALORIES
        TextView textCalories = findViewById(R.id.Calories);
        textCalories.setText(MSGCALORIES);

        // afisez in textView-ul pt distanta mesajul MSGDISTANCE
        TextView textDistancce = findViewById(R.id.Distance);
        textDistancce.setText(MSGDISTANCE);

        // afisez in textView-ul pt timp mesajul MSGTIME
        TextView textTime = findViewById(R.id.Time);
        textTime.setText(MSGTIME);

        // afisez in textView-ul pt speed mesajul MSGSPEED
        TextView textSpeed = findViewById(R.id.Speed);
        textSpeed.setText(MSGSPEED);

        // afisez in textView-ul pt daile average mesajul MSGDAILYAVERAGE
        TextView textDailyAverage = findViewById(R.id.dailyAverage);
        textDailyAverage.setText(MSGDAILYAVERAGE);

        //setez progresul pentru fiecare zi
        ProgressBar mon = findViewById(R.id.monday);
        mon.setProgress(MONDAY);

        ProgressBar tue = findViewById(R.id.tuesday);
        tue.setProgress(TUESDAY);

        ProgressBar wed = findViewById(R.id.wednesday);
        wed.setProgress(WEDNESDAY);

        ProgressBar thu = findViewById(R.id.thursday);
        thu.setProgress(THURSDAY);

        ProgressBar fri = findViewById(R.id.friday);
        fri.setProgress(FRIDAY);

        ProgressBar sat = findViewById(R.id.saturday);
        sat.setProgress(SATURDAY);

        ProgressBar sun = findViewById(R.id.sunday);
        sun.setProgress(SUNDAY);
        */

        return view;
    }
}
