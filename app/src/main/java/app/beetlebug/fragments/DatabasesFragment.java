package app.beetlebug.fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import app.beetlebug.FlagsOverview;
import app.beetlebug.MainActivity;
import app.beetlebug.R;


public class DatabasesFragment extends Fragment {

    ImageView mBackButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_databases, container, false);

        mBackButton = view.findViewById(R.id.arrowLeft);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });

        return view;
    }
}