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
import app.beetlebug.ctf.InsecureLoggingActivity;
import app.beetlebug.ctf.SSLPinningByPassActivity;


public class NetworkFragmentHome extends Fragment {

    ImageView m_back_btn;
    Button m_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network_home, container, false);

        m_btn = view.findViewById(R.id.button);

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
                Intent i = new Intent(getActivity(), SSLPinningByPassActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}