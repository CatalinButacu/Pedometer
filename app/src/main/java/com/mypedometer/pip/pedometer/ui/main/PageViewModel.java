package com.mypedometer.pip.pedometer.ui.main;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.ui.fragments.ChallengeFragment;
import com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment;
import com.mypedometer.pip.pedometer.ui.fragments.StatusFragment;

/**
 * This is the class that is responsible for preparing and managing the data for every Fragment.
 */
public class PageViewModel extends ViewModel {
    private final MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private final LiveData<Fragment> mFragment = Transformations.switchMap(mIndex, input -> {
        switch (input) {
            case 1:   return new MutableLiveData<>(new StatusFragment());
            case 2:   return new MutableLiveData<>(new ChallengeFragment());
            default:  return new MutableLiveData<>(new ProfileFragment());
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<Fragment> getFragment() {
        return mFragment;
    }
}
