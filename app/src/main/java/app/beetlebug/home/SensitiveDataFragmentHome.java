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

import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.ctf.InsecureLoggingActivity;
import app.beetlebug.ctf.VulnerableClipboardActivity;

public class SensitiveDataFragmentHome extends Fragment {

    ImageView m_back_btn;
    Button m_btn, m_btn2;

    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensitive_data, container, false);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);
        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        m_back_btn = view.findViewById(R.id.back);
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
                Intent i = new Intent(getActivity(), InsecureLoggingActivity.class);
                startActivity(i);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), VulnerableClipboardActivity.class);
                startActivity(i);
            }
        });

        int clip_score = sharedPreferences.getInt("ctf_score_clip", 0);
        int log_score = sharedPreferences.getInt("ctf_score_log", 0);

        String log_string = Integer.toString(log_score);
        if (log_string.equals("5")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        String clip_string = Integer.toString(clip_score);
        if (clip_string.equals("5")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }

        return view;
    }
}