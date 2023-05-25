package com.mypedometer.pip.pedometer.ui.main;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;

public class LoadingScreenFragment extends Fragment {
    public static final String TAG = "LoadingScreenFragment";
    private View fragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.loading_screen, container, false);
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        scheduleFragmentRemoval();
    }

    private void scheduleFragmentRemoval() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeFragment();
            }
        }, 2500); // Delay in milliseconds (2.5 seconds in this case)
    }

    private void removeFragment() {
        if (isAdded() && getParentFragmentManager() != null) {
            Animator animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_out);
            animator.setTarget(fragmentView);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    getParentFragmentManager().beginTransaction()
                            .remove(LoadingScreenFragment.this)
                            .commitAllowingStateLoss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            animator.start();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentView = null; // Reset the fragment view reference
    }
}

