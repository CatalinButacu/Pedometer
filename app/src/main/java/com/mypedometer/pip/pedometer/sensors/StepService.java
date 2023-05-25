package com.mypedometer.pip.pedometer.sensors;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

public class StepService extends Service implements SensorEventListener {
    private StepBinder binder = new StepBinder();
    private SensorManager sensorManager;
    private StepDetector stepDetector;
    private List<StepListener> stepListeners = new ArrayList<>();

    public class StepBinder extends Binder {
        public StepService getService() {
            return StepService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepDetector = new StepDetector();
        stepDetector.setStepListener(new StepListener() {
            @Override
            public void onStep() {
                notifyStepListeners();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stepDetector.onSensorChanged(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    public void addStepListener(StepListener listener) {
        stepListeners.add(listener);
    }

    public void removeStepListener(StepListener listener) {
        stepListeners.remove(listener);
    }

    private void notifyStepListeners() {
        for (StepListener listener : stepListeners) {
            listener.onStep();
        }
    }
}
