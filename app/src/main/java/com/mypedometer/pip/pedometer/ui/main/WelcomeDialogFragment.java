package com.mypedometer.pip.pedometer.ui.main;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mypedometer.pip.pedometer.R;

public class WelcomeDialogFragment extends DialogFragment {
    public static final String TAG = "WelcomeDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        View dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_welcome, null);
        dialog.setContentView(dialogView);

        // Set initial translation on Y-axis
        dialogView.setTranslationY(5000f);

        // Translate animation
        dialogView.animate()
                .translationY(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        dialogView.setVisibility(View.VISIBLE);
                    }
                });

        Button okButton = dialog.findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Dismiss the fragment
            }
        });

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
