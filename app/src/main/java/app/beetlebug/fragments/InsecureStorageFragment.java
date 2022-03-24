package app.beetlebug.fragments;

import android.content.Intent;
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

    TextView mCtfTitle, mCtfTitle2;
    Button mButton, mButton3, mButton4;
    ImageView mBackButton;


    public InsecureStorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insecure_storage, container, false);
        mCtfTitle = view.findViewById(R.id.ctfTitle);
        mCtfTitle2 = view.findViewById(R.id.ctfTitle2);

        mButton = view.findViewById(R.id.button);
        mButton3 = view.findViewById(R.id.button3);
        mButton4 = view.findViewById(R.id.button4);

        mBackButton = view.findViewById(R.id.back);


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), FlagsOverview.class);
                startActivity(ctf_intent);

//                getActivity().onBackPressed();
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