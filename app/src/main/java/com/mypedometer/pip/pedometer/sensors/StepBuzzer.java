package com.mypedometer.pip.pedometer.sensors;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * Vibrates whenever a step is detected.
 * Normally, this is not attached, used for debugging purposes.
 */
public class StepBuzzer implements StepListener {

    private Context mContext;
    private Vibrator mVibrator;

    public StepBuzzer(Context context) {
        mContext = context;
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onStep() {
        buzz();
    }

    public void passValue() {

    }

    private void buzz() {
        if (mVibrator.hasVibrator()) {
            // Vibrate for 50 milliseconds
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                VibrationEffect vibrationEffect = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);
                mVibrator.vibrate(vibrationEffect);
            } else {
                // Deprecated in API 26
                mVibrator.vibrate(50);
            }
        }
    }
}
