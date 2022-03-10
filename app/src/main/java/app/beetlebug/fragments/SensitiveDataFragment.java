package app.beetlebug.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import app.beetlebug.FlagsOverview;
import app.beetlebug.R;
import app.beetlebug.ctf.BiometricActivityDeeplink;
import app.beetlebug.ctf.InsecureLogging;


public class SensitiveDataFragment extends Fragment {

    ImageView mBackButton;
    Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensitive_data, container, false);

        mBackButton = view.findViewById(R.id.arrowLeft);
        mButton = view.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), InsecureLogging.class);
                startActivity(ctf_intent);
            }
        });
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