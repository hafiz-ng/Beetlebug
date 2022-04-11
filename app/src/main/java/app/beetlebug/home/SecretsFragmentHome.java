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
import app.beetlebug.ctf.BinaryPatchActivity;
import app.beetlebug.ctf.EmbeddedSecretSourceCode;
import app.beetlebug.ctf.EmbeddedSecretStrings;


public class SecretsFragmentHome extends Fragment {

    ImageView m_btn_back;
    Button m_btn, m_btn2;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secrets_fragments_home, container, false);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        m_btn_back = view.findViewById(R.id.back);

        m_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EmbeddedSecretStrings.class);
                startActivity(i);
            }
        });


        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EmbeddedSecretSourceCode.class);
                startActivity(i);
            }
        });


        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        float secret_source_score = sharedPreferences.getFloat("ctf_score_secret_source", 0);
        float secret_string_score = sharedPreferences.getFloat("ctf_score_secret_string", 0);
        String score_string = Float.toString(secret_string_score);
        String score_source = Float.toString(secret_source_score);
        if (score_string.equals("6.25")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        if (score_source.equals("6.25")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }

        return view;
    }
}