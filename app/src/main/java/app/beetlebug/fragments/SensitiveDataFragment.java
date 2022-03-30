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
import app.beetlebug.ctf.InsecureLoggingActivity;
import app.beetlebug.ctf.VulnerableClipboardActivity;


public class SensitiveDataFragment extends Fragment {

    ImageView mBackButton;
    Button mBtn, mBtn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensitive_data, container, false);

        mBackButton = view.findViewById(R.id.back);
        mBtn = view.findViewById(R.id.button);
        mBtn2 = view.findViewById(R.id.button2);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), InsecureLoggingActivity.class);
                startActivity(ctf_intent);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), VulnerableClipboardActivity.class);
                startActivity(ctf_intent2);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });
        return view;
    }
}