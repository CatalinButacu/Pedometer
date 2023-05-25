package com.mypedometer.pip.pedometer.sensors;

import com.mypedometer.pip.pedometer.PedometerSettings;
import com.mypedometer.pip.pedometer.data.storage.LocalManager;

import java.util.ArrayList;

public class StepDisplayer implements StepListener, SpeakingTimer.Listener {
    private int mCount = 0;
    private PedometerSettings mSettings;
    private Utils mUtils;

    public StepDisplayer(PedometerSettings settings, Utils utils) {
        mUtils = utils;
        mSettings = settings;
        notifyListeners();
    }

    public void setUtils(Utils utils) {
        mUtils = utils;
    }

    public void setSteps(int steps) {
        mCount = steps;
        notifyListeners();
    }

    public void onStep() {
        LocalManager.getInstance().getLocalUser().incrementSteps();
        LocalManager.getInstance().getLocalUser().incrementCalories();
        LocalManager.getInstance().getLocalUser().incrementDistance();
        mCount = LocalManager.getInstance().getLocalUser().getTodaySteps();
        notifyListeners();
    }

    public void reloadSettings() {
        notifyListeners();
    }

    public void passValue() {
    }

    // Listener

    public interface Listener {
        void stepsChanged(int value);

        void passValue();
    }

    private ArrayList<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener listener) {
        mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        mListeners.remove(listener);
    }

    public void notifyListeners() {
        for (Listener listener : mListeners) {
            listener.stepsChanged(mCount);
        }
    }

    // Speaking

    public void speak() {
        if (mSettings.shouldTellSteps() && mCount > 0) {
            mUtils.say(mCount + " steps");
        }
    }
}
