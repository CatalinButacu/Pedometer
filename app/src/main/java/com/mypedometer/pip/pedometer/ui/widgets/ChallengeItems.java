package com.mypedometer.pip.pedometer.ui.widgets;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;

public class ChallengeItems extends Fragment {

    String title;
    String description;
    Icon image;

    public ChallengeItems(String t,String d,Icon i){
        title = t;
        description = d;
        image = i;
    }

    public ChallengeItems(){
        //requirement
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ChallengeView = inflater.inflate(R.layout.item_challenge_layout, container, false);

        TextView titlu = (TextView) ChallengeView.findViewById(R.id.ch_title);
        TextView descriere = (TextView) ChallengeView.findViewById(R.id.ch_description);
        ImageView imagine = (ImageView) ChallengeView.findViewById(R.id.ch_image);

        titlu.setText(title);
        descriere.setText(description);
        imagine.setImageIcon(image);

        return ChallengeView;
    }
}
