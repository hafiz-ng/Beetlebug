package app.beetlebug.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.beetlebug.R;
import app.beetlebug.ctf.BiometricActivityDeeplink;
import app.beetlebug.ctf.BiometricActivityFrida;


public class BiometricFragment extends Fragment {

    Button mButton, mButton2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biometric, container, false);

        mButton = view.findViewById(R.id.button);
        mButton2 = view.findViewById(R.id.button2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), BiometricActivityDeeplink.class);
                startActivity(ctf_intent);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), BiometricActivityFrida.class);
                startActivity(ctf_intent2);
            }
        });

        return view;
    }
}