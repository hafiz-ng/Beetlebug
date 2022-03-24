package app.beetlebug.fragments;

import android.content.Intent;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_secrets, container, false);

        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);

        m_btn.setText("Done");
        m_btn.setEnabled(false);

        m_back_btn = view.findViewById(R.id.back);
        m_back_btn2 = view.findViewById(R.id.back2);


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