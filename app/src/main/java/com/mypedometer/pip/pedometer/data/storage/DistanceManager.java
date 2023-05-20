package com.mypedometer.pip.pedometer.data.storage;

import com.mypedometer.pip.pedometer.PedometerSettings;
import com.mypedometer.pip.pedometer.sensors.SpeakingTimer;
import com.mypedometer.pip.pedometer.sensors.StepListener;
import com.mypedometer.pip.pedometer.sensors.Utils;

/**
 * Calculates and displays the distance walked.
 */

public class DistanceManager implements StepListener, SpeakingTimer.Listener {

    public interface Listener {
        public void valueChanged(float value);
        public void passValue();
    }
    private Listener mListener;
    
    float mDistance = 0;
    
    PedometerSettings mSettings;
    Utils mUtils;
    
    boolean mIsMetric;
    float mStepLength;

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
        
        if (mIsMetric) {
            mDistance += (float)(// kilometers
                mStepLength // centimeters
                / 100000.0); // centimeters/kilometer
        }
        else {
            mDistance += (float)(// miles
                mStepLength // inches
                / 63360.0); // inches/mile
        }
        
        notifyListener();
    }
    
    private void notifyListener() {
        mListener.valueChanged(mDistance);
    }
    
    public void passValue() {
        // Callback of StepListener - Not implemented
    }

    public void speak() {
        if (mSettings.shouldTellDistance()) {
            if (mDistance >= .001f) {
                mUtils.say(("" + (mDistance + 0.000001f)).substring(0, 4) + (mIsMetric ? " kilometers" : " miles"));
                // TODO: format numbers (no "." at the end)
            }
        }
    }
    

}

