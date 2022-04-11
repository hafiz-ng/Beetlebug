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
import app.beetlebug.ctf.WebViewURLActivity;
import app.beetlebug.ctf.WebViewXSSActivity;


public class WebViewFragmentHome extends Fragment {

    ImageView m_btn_back;
    Button m_btn, m_btn2;

    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view_home, container, false);

        m_btn_back = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button3);


        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

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

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewXSSActivity.class);
                startActivity(i);
            }
        });

        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);

        String xss_string = Float.toString(xss_score);
        if (xss_string.equals("6.25")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        String web_string = Float.toString(webview_score);
        if (web_string.equals("6.25")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }
        return view;
    }
}