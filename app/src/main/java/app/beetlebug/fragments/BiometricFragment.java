package app.beetlebug.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class BiometricFragment extends Fragment {

    Button btn;
    ImageView m_btn;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biometric, container, false);
        m_btn = view.findViewById(R.id.back);
        btn = view.findViewById(R.id.button);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), BiometricActivityDeeplink.class);
                startActivity(ctf_intent);
            }
        });

        int auth_score = sharedPreferences.getInt("ctf_score_auth", 0);

        String auth_string = Integer.toString(auth_score);
        if (auth_string.equals("5")) {
            btn.setEnabled(false);
            btn.setText("Done");
        }


        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FlagsOverview.class);
                startActivity(i);
            }
        });

        return view;
    }
}