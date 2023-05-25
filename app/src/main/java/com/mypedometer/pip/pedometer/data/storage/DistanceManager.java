package com.mypedometer.pip.pedometer.data.storage;

import com.mypedometer.pip.pedometer.PedometerSettings;
import com.mypedometer.pip.pedometer.sensors.SpeakingTimer;
import com.mypedometer.pip.pedometer.sensors.StepListener;
import com.mypedometer.pip.pedometer.sensors.Utils;

/**
 * This class calculates and displays the distance walked.
 */
public class DistanceManager implements StepListener, SpeakingTimer.Listener {

    public interface Listener {
        void onValueChanged(float value);
    }

    private Listener mListener;
    private float mDistance;

    private PedometerSettings mSettings;
    private Utils mUtils;

    private boolean mIsMetric;
    private float mStepLength;

    public DistanceManager(Listener listener, PedometerSettings settings, Utils utils) {
        mListener = listener;
        mUtils = utils;
        mSettings = settings;
        reloadSettings();
    }

    public void setDistance(float distance) {
        mDistance = distance;
        notifyListener();
    }

    public void reloadSettings() {
        mIsMetric = mSettings.isMetric();
        mStepLength = mSettings.getStepLength();
        notifyListener();
    }

    public void onStep() {
        float distanceIncrement = mIsMetric ?
                mStepLength / 100000.0f : // centimeters/kilometer
                mStepLength / 63360.0f; // inches/mile

        mDistance += distanceIncrement;

        notifyListener();
    }

    private void notifyListener() {
        mListener.onValueChanged(mDistance);
    }

    public void passValue() {
        // No implementation needed
    }

    public void speak() {
        if (mSettings.shouldTellDistance() && mDistance >= 0.001f) {
            String distanceText = String.format("%.3f", mDistance + 0.000001f);
            String unit = mIsMetric ? " kilometers" : " miles";
            mUtils.say(distanceText + unit);
        }
    }
}
