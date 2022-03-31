package app.beetlebug.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.ctf.InsecureContentProvider;
import app.beetlebug.ctf.VulnerableActivityIntent;
import app.beetlebug.ctf.VulnerableServiceActivity;


public class AndroidComponentsHome extends Fragment {

    Button m_btn, m_btn3, m_btn4;
    TextView mCtfTitle;

    ImageView mBackButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_android_components, container, false);
        m_btn = view.findViewById(R.id.button);
        m_btn3 = view.findViewById(R.id.button3);
        m_btn4 = view.findViewById(R.id.button4);

        mCtfTitle = view.findViewById(R.id.textViewComponentsTitle);
        mBackButton = view.findViewById(R.id.back);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), MainActivity.class);
                startActivity(ctf_intent);
            }
        });

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), VulnerableActivityIntent.class);
                startActivity(ctf_intent);
            }
        });


        m_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent3 = new Intent(getActivity(), VulnerableServiceActivity.class);
                startActivity(ctf_intent3);
            }
        });

        m_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent4 = new Intent(getActivity(), InsecureContentProvider.class);
                startActivity(ctf_intent4);
            }
        });
        return view;
    }
}