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
import app.beetlebug.ctf.WebViewURLActivity;
import app.beetlebug.ctf.WebViewXSSActivity;


public class WebViewFragmentHome extends Fragment {

    ImageView m_btn_back;
    Button m_btn, m_btn3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view_home, container, false);

        m_btn_back = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);
        m_btn3 = view.findViewById(R.id.button3);


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
                Intent i = new Intent(getActivity(), WebViewURLActivity.class);
                startActivity(i);
            }
        });

        m_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewXSSActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}