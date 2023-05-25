package com.mypedometer.pip.pedometer.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.lang.Thread;

import com.mypedometer.pip.pedometer.data.storage.LocalManager;

public class StepDetector implements SensorEventListener {
    private static final float ACCELERATION_THRESHOLD = 8.0f;
    private static final float LINEAR_ACCELERATION_THRESHOLD = 5.0f;
    private static final int STEP_WINDOW_SIZE = 5;
    private static final float MAX_STEP_DURATION = 4000; // milliseconds

    private float[] previousValues = new float[3];
    private boolean isStepDetected = false;
    private long stepTimestamp = 0;
    private StepListener stepListener;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] currentValues = event.values.clone();

            // Calculate the magnitude of the acceleration vector
            float acceleration = calculateMagnitude(currentValues);

            // Check if the acceleration exceeds the threshold
            if (acceleration > ACCELERATION_THRESHOLD) {
                // Check if a step has already been detected
                if (!isStepDetected) {
                    // Check for step detection criteria
                    if (isStep(currentValues, previousValues, event.timestamp)) {
                        isStepDetected = true;
                        LocalManager.getInstance().getLocalUser().incrementSteps();
                        LocalManager.getInstance().getLocalUser().incrementCalories();
                        LocalManager.getInstance().getLocalUser().incrementDistance();
                        notifyStepListener();
                        try {
                            Thread.currentThread().sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        isStepDetected = false;
                    }
                }
            } else {
                isStepDetected = false;
            }

            // Update the previous values
            System.arraycopy(currentValues, 0, previousValues, 0, Math.min(currentValues.length, previousValues.length));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    public void setStepListener(StepListener listener) {
        stepListener = listener;
    }

    private float calculateMagnitude(float[] values) {
        return (float) Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }

    private boolean isStep(float[] currentValues, float[] previousValues, long currentTimestamp) {
        // Calculate the time difference between the current and previous step
        long stepDuration = currentTimestamp - stepTimestamp;

        // Example criteria:
        // 1. Check if there is a significant change in acceleration
        boolean hasSignificantChange = Math.abs(currentValues[0] - previousValues[0]) > ACCELERATION_THRESHOLD
                || Math.abs(currentValues[1] - previousValues[1]) > ACCELERATION_THRESHOLD
                || Math.abs(currentValues[2] - previousValues[2]) > ACCELERATION_THRESHOLD;

        // 2. Check if the step duration is within the desired range
        boolean isStepDurationValid = stepDuration > 0 && stepDuration < MAX_STEP_DURATION;

        // 3. Check if the linear acceleration is above a certain threshold
        float linearAcceleration = calculateLinearAcceleration(currentValues);
        boolean hasLinearMovement = linearAcceleration > LINEAR_ACCELERATION_THRESHOLD;

        // Update the step timestamp if a step is detected
        if (hasSignificantChange || isStepDurationValid || hasLinearMovement || stepDuration > MAX_STEP_DURATION) {
            stepTimestamp = currentTimestamp;
        }

        // Return true if any of the criteria are met
        return hasSignificantChange || isStepDurationValid || hasLinearMovement;
    }

    private float calculateLinearAcceleration(float[] values) {
        final float alpha = 0.05f; // Adjust this value based on your requirements

        // Apply a low-pass filter to the acceleration values
        float[] filteredValues = new float[3];
        filteredValues[0] = alpha * filteredValues[0] + (1 - alpha) * values[0];
        filteredValues[1] = alpha * filteredValues[1] + (1 - alpha) * values[1];
        filteredValues[2] = alpha * filteredValues[2] + (1 - alpha) * values[2];

        // Calculate the magnitude of the linear acceleration vector
        float linearAcceleration = (float) Math.sqrt(
                (filteredValues[0] * filteredValues[0]) +
                        (filteredValues[1] * filteredValues[1]) +
                        (filteredValues[2] * filteredValues[2])
        );

        // Subtract the acceleration due to gravity
        linearAcceleration -= SensorManager.GRAVITY_EARTH;

        return linearAcceleration;
    }


    private boolean isWindowStep(float[] currentValues, float[] previousValues) {
        // Keep a circular buffer of previous samples to check for a step within a window size
        float[] windowValues = new float[STEP_WINDOW_SIZE];
        windowValues[0] = currentValues[1];

        // Copy the previous values to the window buffer
        int windowIndex = 1;
        for (int i = previousValues.length - 1; i >= 0 && windowIndex < STEP_WINDOW_SIZE; i--) {
            windowValues[windowIndex] = previousValues[i];
            windowIndex++;
        }

        // Check if the window contains a step
        for (int i = 0; i < STEP_WINDOW_SIZE; i++) {
            if (windowValues[i] > 0) {
                return false; // If any previous value is positive, it's not a step
            }
        }

        return true;
    }

    private void notifyStepListener() {
        if (stepListener != null) {
            stepListener.onStep();
        }
        stepTimestamp = System.currentTimeMillis();
    }
}
