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
import app.beetlebug.ctf.InsecureStorageExternal;
import app.beetlebug.ctf.InsecureStorageSQLite;
import app.beetlebug.ctf.InsecureStorageSharedPref;
import app.beetlebug.R;


public class InsecureStorageFragment extends Fragment {

    TextView mCtfTitle;
    Button mButton, mButton3, mButton4;
    ImageView mBackButton;


    SharedPreferences sharedPreferences;


    public InsecureStorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insecure_storage, container, false);
        mCtfTitle = view.findViewById(R.id.ctfTitle);

        mButton = view.findViewById(R.id.button);
        mButton3 = view.findViewById(R.id.button3);
        mButton4 = view.findViewById(R.id.button4);

        mBackButton = view.findViewById(R.id.back);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        String pref_string = Float.toString(shared_pref_score);
        if (pref_string.equals("6.25")) {
            mButton.setEnabled(false);
            mButton.setText("Done");
        }

        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        String exter_string = Float.toString(external_str_score);
        if (exter_string.equals("6.25")) {
            mButton4.setEnabled(false);
            mButton4.setText("Done");
        }

        float sqlite_str_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);
        String sqlite_string = Float.toString(sqlite_str_score);
        if (sqlite_string.equals("6.25")) {
            mButton3.setEnabled(false);
            mButton3.setText("Done");
        }


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
                Intent ctf_intent = new Intent(getActivity(), InsecureStorageSharedPref.class);
                startActivity(ctf_intent);
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent2 = new Intent(getActivity(), InsecureStorageSQLite.class);
                startActivity(ctf_intent2);
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent3 = new Intent(getActivity(), InsecureStorageExternal.class);
                startActivity(ctf_intent3);
            }
        });

        return view;
    }

}