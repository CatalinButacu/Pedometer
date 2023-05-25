package com.mypedometer.pip.pedometer.data.storage;

import com.mypedometer.pip.pedometer.PedometerSettings;
import com.mypedometer.pip.pedometer.sensors.SpeakingTimer;
import com.mypedometer.pip.pedometer.sensors.StepListener;
import com.mypedometer.pip.pedometer.sensors.Utils;

/**
 * This class calculates and displays the approximate calories burned.
 */
public class CaloriesManager implements StepListener, SpeakingTimer.Listener {

    public interface Listener {
        void onValueChanged(float value);
    }

    private static final double METRIC_RUNNING_FACTOR = 1.02784823;
    private static final double IMPERIAL_RUNNING_FACTOR = 0.75031498;
    private static final double METRIC_WALKING_FACTOR = 0.708;
    private static final double IMPERIAL_WALKING_FACTOR = 0.517;

    private Listener mListener;
    private double mCalories;

    private PedometerSettings mSettings;
    private Utils mUtils;

    private boolean mIsMetric;
    private boolean mIsRunning;
    private float mStepLength;
    private float mBodyWeight;

    public CaloriesManager(Listener listener, PedometerSettings settings, Utils utils) {
        mListener = listener;
        mUtils = utils;
        mSettings = settings;
        reloadSettings();
    }

    public void setCalories(float calories) {
        mCalories = calories;
        notifyListener();
    }

    public void reloadSettings() {
        mIsMetric = mSettings.isMetric();
        mIsRunning = mSettings.isRunning();
        mStepLength = mSettings.getStepLength();
        mBodyWeight = mSettings.getBodyWeight();
        notifyListener();
    }

    public void resetValues() {
        mCalories = 0;
    }

    public void setMetric(boolean isMetric) {
        mIsMetric = isMetric;
    }

    public void setStepLength(float stepLength) {
        mStepLength = stepLength;
    }

    public void onStep() {
        double caloriesFactor = mIsMetric ?
                (mIsRunning ? METRIC_RUNNING_FACTOR : METRIC_WALKING_FACTOR) :
                (mIsRunning ? IMPERIAL_RUNNING_FACTOR : IMPERIAL_WALKING_FACTOR);

        mCalories += (mBodyWeight * caloriesFactor * mStepLength) /
                (mIsMetric ? 100000.0 : 63360.0);

        notifyListener();
    }

    private void notifyListener() {
        mListener.onValueChanged((float) mCalories);
    }

    public void passValue() {
        // No implementation needed
    }

    public void speak() {
        if (mSettings.shouldTellCalories() && mCalories > 0) {
            mUtils.say((int) mCalories + " calories burned");
        }
    }
}
