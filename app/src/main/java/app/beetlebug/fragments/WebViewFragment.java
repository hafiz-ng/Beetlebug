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
import app.beetlebug.ctf.WebViewURLActivity;
import app.beetlebug.ctf.WebViewXSSActivity;


public class WebViewFragment extends Fragment {


    ImageView btn;
    Button m_btn1, m_btn3;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        m_btn1 = view.findViewById(R.id.button);
        m_btn3 = view.findViewById(R.id.button3);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        btn = view.findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });

        m_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), WebViewURLActivity.class);
                startActivity(ctf_intent);
            }
        });

        m_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), WebViewXSSActivity.class);
                startActivity(ctf_intent2);
            }
        });


        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);

        String xss_string = Float.toString(xss_score);
        if (xss_string.equals("6.25")) {
            m_btn3.setEnabled(false);
            m_btn3.setText("Done");
        }

        String web_string = Float.toString(webview_score);
        if (web_string.equals("6.25")) {
            m_btn1.setEnabled(false);
            m_btn1.setText("Done");
        }

        return view;

    }
}