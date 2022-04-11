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
import app.beetlebug.ctf.InsecureLoggingActivity;
import app.beetlebug.ctf.VulnerableClipboardActivity;


public class SensitiveDataFragment extends Fragment {

    SharedPreferences sharedPreferences;

    ImageView mBackButton;
    Button mBtn, mBtn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensitive_data, container, false);

        mBackButton = view.findViewById(R.id.back);
        mBtn = view.findViewById(R.id.button);
        mBtn2 = view.findViewById(R.id.button2);
        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), InsecureLoggingActivity.class);
                startActivity(ctf_intent);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), VulnerableClipboardActivity.class);
                startActivity(ctf_intent2);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });



        float clip_score = sharedPreferences.getFloat("ctf_score_clip", 0);
        float log_score = sharedPreferences.getFloat("ctf_score_log", 0);

        String log_string = Float.toString(log_score);
        if (log_string.equals("6.25")) {
            mBtn.setEnabled(false);
            mBtn.setText("Done");
        }

        String clip_string = Float.toString(clip_score);
        if (clip_string.equals("6.25")) {
            mBtn2.setEnabled(false);
            mBtn2.setText("Done");
        }

        return view;
    }
}