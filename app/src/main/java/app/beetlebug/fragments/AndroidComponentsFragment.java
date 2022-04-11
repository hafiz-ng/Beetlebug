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
import android.widget.TextView;

import app.beetlebug.FlagsOverview;
import app.beetlebug.R;
import app.beetlebug.ctf.InsecureContentProvider;
import app.beetlebug.ctf.VulnerableActivityIntent;
import app.beetlebug.ctf.VulnerableServiceActivity;


public class AndroidComponentsFragment extends Fragment {

    Button mButton, mButton3, mButton4;
    TextView mCtfTitle;
    SharedPreferences sharedPreferences;
    ImageView mBackButton;

    public AndroidComponentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_android_components, container, false);
        mButton = view.findViewById(R.id.button);
        mButton3 = view.findViewById(R.id.button3);
        mButton4 = view.findViewById(R.id.button4);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float intent_redirect_score = sharedPreferences.getFloat("ctf_score_intent_redirect", 0);

        String pref_string = Float.toString(intent_redirect_score);
        if (pref_string.equals("6.25")) {
            mButton.setEnabled(false);
            mButton.setText("Done");
        }

        float service_score = sharedPreferences.getFloat("ctf_score_service", 0);
        String service_string = Float.toString(service_score);
        if (service_string.equals("6.25")) {
            mButton3.setEnabled(false);
            mButton3.setText("Done");
        }

        float content_score = sharedPreferences.getFloat("ctf_score_content_provider", 0);
        String content_string = Float.toString(content_score);
        if (content_string.equals("6.25")) {
            mButton4.setEnabled(false);
            mButton4.setText("Done");
        }



        mCtfTitle = view.findViewById(R.id.textViewComponentsTitle);
        mBackButton = view.findViewById(R.id.back);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), VulnerableActivityIntent.class);
                startActivity(ctf_intent);
            }
        });


        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent3 = new Intent(getActivity(), VulnerableServiceActivity.class);
                startActivity(ctf_intent3);
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent4 = new Intent(getActivity(), InsecureContentProvider.class);
                startActivity(ctf_intent4);
            }
        });

        return view;
    }
}