package com.mypedometer.pip.pedometer.sensors;

/**
 * Interface implemented by classes that can handle notifications about steps.
 * These classes can be passed to StepDetector.
 */
public interface StepListener {
    void onStep();
    void passValue();
}
