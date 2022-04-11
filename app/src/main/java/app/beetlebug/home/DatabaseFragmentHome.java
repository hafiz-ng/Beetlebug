package app.beetlebug.home;

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
import android.widget.Toast;

import app.beetlebug.FlagsOverview;
import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.ctf.FirebaseDatabaseActivity;
import app.beetlebug.ctf.SQLInjectionActivity;

public class DatabaseFragmentHome extends Fragment {
    ImageView m_back_btn;
    Button m_btn, m_btn2;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_database_home, container, false);

        m_back_btn = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);


        sharedPreferences = getContext().getSharedPreferences("flag_scores", 0);

        m_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), MainActivity.class);
                startActivity(ctf_intent);
            }
        });

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SQLInjectionActivity.class);
                startActivity(i);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirebaseDatabaseActivity.class);
                startActivity(i);
            }
        });

        float firebase_score = sharedPreferences.getFloat("ctf_score_firebase", 0);
        float sqli_score = sharedPreferences.getFloat("ctf_score_sqli", 0);

        String sqli_string = Float.toString(sqli_score);
        if (sqli_string.equals("6.25")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        String web_string = Float.toString(firebase_score);
        if (web_string.equals("6.25")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }
        return view;
    }
}