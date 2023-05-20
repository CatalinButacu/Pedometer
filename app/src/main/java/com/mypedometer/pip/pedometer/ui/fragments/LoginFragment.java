package com.mypedometer.pip.pedometer.ui.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.mypedometer.pip.pedometer.ConnectionDB;
import com.mypedometer.pip.pedometer.DataBaseHelper;
import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {
    LoginFragment lf = this;

    private Button loginButton;
    private Button signupButton;

    private EditText emailEditText;
    private EditText passwordEditText;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View LoginView = inflater.inflate(R.layout.login_layout, container, false);

        loginButton = (Button) LoginView.findViewById(R.id.login_button);
        signupButton = (Button) LoginView.findViewById(R.id.signup_button);

        emailEditText = (EditText) LoginView.findViewById(R.id.email_input);
        passwordEditText = (EditText) LoginView.findViewById(R.id.password_input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String email = "";
                String password = "";

                try {
                    email = emailEditText.getText().toString();
                    password = passwordEditText.getText().toString();

                    emailEditText.getText().clear();
                    passwordEditText.getText().clear();

                    if (email.isEmpty() || password.isEmpty()) {
                        valid = false;
                    }

                    if(!checkToValidEmail(email)){
                        Toast.makeText(getContext(), "Email-ul nu este valid!", Toast.LENGTH_SHORT).show();
                        valid = false;
                    }

                } catch (Exception e) {
                    valid = false;
                }

                if (valid) {
                    try {
                        DataBaseHelper db = new DataBaseHelper(getContext(), "PedometerDB.sqlite", null, 1);
                        SQLiteDatabase database = db.getReadableDatabase();

                        String query = "SELECT * FROM USERS_ESSENTIALS WHERE Email = ? AND Password = ?";
                        String[] selectionArgs = {email, password};

                        Cursor cursor = database.rawQuery(query, selectionArgs);

                        if (cursor.moveToNext()) {
                            valid = true;
                        } else {
                            valid = false;
                        }

                        cursor.close();
                    } catch (Exception e) {
                        valid = false;
                    }
                }

                //LOGIN -> PROFILE
                if (valid) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.changeProfileFragment(lf,new ProfileFragment());
                }
                else
                    Toast.makeText(getContext(), "Email-ul sau parola sunt gresite!", Toast.LENGTH_SHORT).show();


                //TRIMITERE NOTIFICARE SYSTEM LOGARE
                try {
                    SentNotificatrion(valid);
                } catch (Exception e) {
                    System.out.println("Ascultatorul pe butonul de login nu a putut fi stabilit!");
                }

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(lf,new CreateAccountFragment());
            }
        });
        return LoginView;
    }

    static boolean checkToValidEmail(String email){
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();
    }

    void SentNotificatrion(boolean success) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "Login Notification");
        mBuilder.setContentTitle(getResources().getString(R.string.app_name));
        mBuilder.setContentText(getResources().getString(success ? R.string.notification_login_success : R.string.notification_login_failure));
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(true);

        NotificationManagerCompat managerNotification = NotificationManagerCompat.from(getActivity());
        managerNotification.notify(1, mBuilder.build());
    }
}