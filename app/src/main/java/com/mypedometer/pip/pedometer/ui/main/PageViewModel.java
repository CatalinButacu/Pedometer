package com.mypedometer.pip.pedometer.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            switch (input)
            {
                case 1: return "Bine ai venit pe pagina principala!!";
                case 2: return "Bine ai venit pe pagina de challenge!!";
                case 3: return "Bine ai venit pe pagina profilului tau!!";
                default: return "404 - Nu avem nimic sa-ti aratam :<<";
            }
            //return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}