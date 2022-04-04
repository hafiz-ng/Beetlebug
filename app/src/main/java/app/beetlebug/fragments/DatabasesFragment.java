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
import app.beetlebug.ctf.FirebaseDatabaseActivity;
import app.beetlebug.ctf.SQLInjectionActivity;


public class DatabasesFragment extends Fragment {

    ImageView m_back;
    Button m_btn, m_btn2;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_databases, container, false);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);

        m_back = view.findViewById(R.id.back);
        m_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), SQLInjectionActivity.class);
                startActivity(ctf_intent);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FirebaseDatabaseActivity.class);
                startActivity(ctf_intent);
            }
        });


        int firebase_score = sharedPreferences.getInt("ctf_score_firebase", 0);
        int sqli_score = sharedPreferences.getInt("ctf_score_sqli", 0);

        String sqli_string = Integer.toString(sqli_score);
        if (sqli_string.equals("5")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        String web_string = Integer.toString(firebase_score);
        if (web_string.equals("5")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }

        return view;
    }
}