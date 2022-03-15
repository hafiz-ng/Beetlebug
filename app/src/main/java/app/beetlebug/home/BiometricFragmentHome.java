package app.beetlebug.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.ctf.BiometricActivityDeeplink;
import app.beetlebug.ctf.BiometricActivityFrida;


public class BiometricFragmentHome extends Fragment {

    Button m_btn, m_btn2;
    ImageView m_btn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biometric_home, container, false);

        m_btn_back = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);

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
                Intent ctf_intent = new Intent(getActivity(), BiometricActivityDeeplink.class);
                startActivity(ctf_intent);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), BiometricActivityFrida.class);
                startActivity(ctf_intent2);
            }
        });

        return view;
    }
}