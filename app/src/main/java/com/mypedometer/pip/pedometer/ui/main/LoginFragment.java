package com.mypedometer.pip.pedometer.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View LoginView = inflater.inflate(R.layout.login_layout, container, false);

        Button loginButton = (Button)LoginView.findViewById(R.id.login_button);
        //de implementat actionListener la butoanele de login si sing up


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;

                //TODO:: DE IMPLEMENTAT VALIDAREA CU BAZA DE DATE

                if (valid) {
                    //TODO:: DE INITIALIZAT NOUA FEREASTRA CU PROFILUL

                    //TRIMITERE NOTIFICARE SYSTEM LOGARE
                    try {
                        SentNotificatrion();
                    } catch (Exception e) {
                        System.out.println("Ascultatorul pe butonul de login nu a putut fi stabilit!");
                    }
                }
            }
        });
        return LoginView;
    }

    void SentNotificatrion() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "Login Notification");
        mBuilder.setContentTitle(getResources().getString(R.string.app_name));
        mBuilder.setContentText(getResources().getString(R.string.notification_login));
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(true);

        NotificationManagerCompat managerNotification = NotificationManagerCompat.from(getActivity());
        managerNotification.notify(0, mBuilder.build());
    }

}