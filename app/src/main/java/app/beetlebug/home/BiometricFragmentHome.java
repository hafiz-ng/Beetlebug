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
import app.beetlebug.ctf.BiometricActivityDeeplink;


public class BiometricFragmentHome extends Fragment {

    Button m_btn;
    ImageView m_btn_back;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biometric_home, container, false);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        m_btn_back = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);

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

        float auth_score = sharedPreferences.getFloat("ctf_score_auth", 0);

        String auth_string = Float.toString(auth_score);
        if (auth_string.equals("6.25")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }


//        m_btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent ctf_intent2 = new Intent(getActivity(), BiometricActivityFrida.class);
//                startActivity(ctf_intent2);
//            }
//        });

        return view;
    }
}