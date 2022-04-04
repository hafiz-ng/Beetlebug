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
import app.beetlebug.ctf.EmbeddedSecretSourceCode;
import app.beetlebug.ctf.EmbeddedSecretStrings;


public class SecretsFragment extends Fragment {
    ImageView m_back_btn, m_back_btn2;
    Button m_btn, m_btn2;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secrets, container, false);


        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);

        m_btn.setText("Done");
        m_btn.setEnabled(false);

        m_back_btn = view.findViewById(R.id.back);
        m_back_btn2 = view.findViewById(R.id.back2);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        int secret_source_score = sharedPreferences.getInt("ctf_score_secret_source", 0);
        int secret_string_score = sharedPreferences.getInt("ctf_score_secret_string", 0);
        String score_string = Integer.toString(secret_string_score);
        String score_source = Integer.toString(secret_source_score);
        if (score_string.equals("5")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        if (score_source.equals("5")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }



        m_back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secret_intent = new Intent(getActivity(), EmbeddedSecretStrings.class);
                startActivity(secret_intent);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secret_intent = new Intent(getActivity(), EmbeddedSecretSourceCode.class);
                startActivity(secret_intent);
            }
        });


        return view;

    }
}