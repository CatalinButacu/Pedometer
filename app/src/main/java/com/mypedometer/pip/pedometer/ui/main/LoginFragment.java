package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mypedometer.pip.pedometer.ConnectionDB;
import com.mypedometer.pip.pedometer.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View LoginView = inflater.inflate(R.layout.login_layout, container, false);

        Button loginButton = (Button) LoginView.findViewById(R.id.login_button);
        Button signupButton = (Button) LoginView.findViewById(R.id.signup_button);

        EditText emailEditText = (EditText) LoginView.findViewById(R.id.email_input);
        EditText passwordEditText = (EditText) LoginView.findViewById(R.id.password_input);

        //TODO:: de implementat actionListener la login si sing up care sa duca la celelalte pagini

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String email = "";
                String password = "";


                //TODO:: DE IMPLEMENTAT VALIDAREA CU BAZA DE DATE
                try {
                    email = emailEditText.getText().toString();
                    password = passwordEditText.getText().toString();

                    if (email.isEmpty() || password.isEmpty()) {
                        valid = false;
                    }
                } catch (Exception e) {
                    valid = false;
                }


                if (valid) {
                    try {
                        ConnectionDB db = new ConnectionDB();
                        String query = "SELECT Email, Password FROM Users WHERE Email = ? AND Password = ?";
                        PreparedStatement statement = db.getConnection().prepareStatement(query);
                        statement.setString(1, email); // Set the value of the first placeholder to the username
                        statement.setString(2, password); // Set the value of the second placeholder to the password
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            // Credentials are valid, proceed with login
                        } else {
                            // Credentials are invalid, show an error message
                        }

                        statement.close();
                        resultSet.close();
                        db.close();
                    } catch (Exception e) {
                        valid = false;
                    }
                }


                //TODO:: DE INITIALIZAT NOUA FEREASTRA CU PROFILUL

                //TRIMITERE NOTIFICARE SYSTEM LOGARE
                try {
                    SentNotificatrion(valid);
                } catch (Exception e) {
                    System.out.println("Ascultatorul pe butonul de login nu a putut fi stabilit!");
                }
            }
        });
        return LoginView;
    }

    void SentNotificatrion(boolean success) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "Login Notification");
        mBuilder.setContentTitle(getResources().getString(R.string.app_name));
        mBuilder.setContentText(getResources().getString(success ? R.string.notification_login_success : R.string.notification_login_failure));
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(true);

        NotificationManagerCompat managerNotification = NotificationManagerCompat.from(getActivity());
        managerNotification.notify(0, mBuilder.build());
    }

}