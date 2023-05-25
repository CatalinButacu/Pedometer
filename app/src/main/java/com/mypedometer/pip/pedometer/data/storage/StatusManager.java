package com.mypedometer.pip.pedometer.data.storage;

import com.mypedometer.pip.pedometer.PedometerSettings;
import com.mypedometer.pip.pedometer.sensors.SpeakingTimer;
import com.mypedometer.pip.pedometer.sensors.StepListener;
import com.mypedometer.pip.pedometer.sensors.Utils;

import java.util.ArrayList;

/**
 * This class calculates and displays the pace (steps/minute), handles input of the desired pace,
 * and notifies the user if they need to go faster or slower.
 */
public class StatusManager implements StepListener, SpeakingTimer.Listener {

    public interface Listener {
        void onPaceChanged(int value);
    }

    private ArrayList<Listener> mListeners = new ArrayList<>();
    private int mCounter = 0;
    private long mLastStepTime = 0;
    private long[] mLastStepDeltas = {-1, -1, -1, -1};
    private int mLastStepDeltasIndex = 0;
    private long mPace = 0;

    private PedometerSettings mSettings;
    private Utils mUtils;

    private int mDesiredPace;
    private boolean mShouldTellFasterSlower;
    private long mSpokenAt = 0;

    public StatusManager(PedometerSettings settings, Utils utils) {
        mUtils = utils;
        mSettings = settings;
        mDesiredPace = mSettings.getDesiredPace();
        reloadSettings();
    }

    public void setPace(int pace) {
        mPace = pace;
        int avg = (int) (60 * 1000.0 / mPace);
        for (int i = 0; i < mLastStepDeltas.length; i++) {
            mLastStepDeltas[i] = avg;
        }
        notifyListener();
    }

    public void reloadSettings() {
        mShouldTellFasterSlower =
                mSettings.shouldTellFasterSlower() && mSettings.getMaintainOption() == PedometerSettings.M_PACE;
        notifyListener();
    }

    public void addListener(Listener listener) {
        mListeners.add(listener);
    }

    public void setDesiredPace(int desiredPace) {
        mDesiredPace = desiredPace;
    }

    public void onStep() {
        long thisStepTime = System.currentTimeMillis();
        mCounter++;

        if (mLastStepTime > 0) {
            long delta = thisStepTime - mLastStepTime;

            mLastStepDeltas[mLastStepDeltasIndex] = delta;
            mLastStepDeltasIndex = (mLastStepDeltasIndex + 1) % mLastStepDeltas.length;

            long sum = 0;
            boolean isMeaningful = true;
            for (long lastStepDelta : mLastStepDeltas) {
                if (lastStepDelta < 0) {
                    isMeaningful = false;
                    break;
                }
                sum += lastStepDelta;
            }

            if (isMeaningful && sum > 0) {
                long avg = sum / mLastStepDeltas.length;
                mPace = 60 * 1000 / avg;

                if (mShouldTellFasterSlower && !mUtils.isSpeakingEnabled()) {
                    if (thisStepTime - mSpokenAt > 3000 && !mUtils.isSpeakingNow()) {
                        float little = 0.10f;
                        float normal = 0.30f;
                        float much = 0.50f;

                        boolean spoken = true;
                        if (mPace < mDesiredPace * (1 - much)) {
                            mUtils.say("much faster!");
                        } else if (mPace > mDesiredPace * (1 + much)) {
                            mUtils.say("much slower!");
                        } else if (mPace < mDesiredPace * (1 - normal)) {
                            mUtils.say("faster!");
                        } else if (mPace > mDesiredPace * (1 + normal)) {
                            mUtils.say("slower!");
                        } else if (mPace < mDesiredPace * (1 - little)) {
                            mUtils.say("a little faster!");
                        } else if (mPace > mDesiredPace * (1 + little)) {
                            mUtils.say("a little slower!");
                        } else {
                            spoken = false;
                        }

                        if (spoken) {
                            mSpokenAt = thisStepTime;
                        }
                    }
                }
            } else {
                mPace = -1;
            }
        }

        mLastStepTime = thisStepTime;
        notifyListener();
    }

    private void notifyListener() {
        for (Listener listener : mListeners) {
            listener.onPaceChanged((int) mPace);
        }
    }

    public void passValue() {
        // Not used
    }

    //-----------------------------------------------------
    // Speaking

    public void speak() {
        if (mSettings.shouldTellPace() && mPace > 0) {
            mUtils.say(mPace + " steps per minute");
        }
    }
}
