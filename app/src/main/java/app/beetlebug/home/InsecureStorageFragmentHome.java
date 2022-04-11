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
import app.beetlebug.ctf.InsecureStorageExternal;
import app.beetlebug.ctf.InsecureStorageSQLite;
import app.beetlebug.ctf.InsecureStorageSharedPref;

public class InsecureStorageFragmentHome extends Fragment {

    ImageView m_btn_back;
    Button m_btn, m_btn2, m_btn3;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insecure_storage_home, container, false);

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        m_btn_back = view.findViewById(R.id.back);
        m_btn = view.findViewById(R.id.button);
        m_btn2 = view.findViewById(R.id.button2);
        m_btn3 = view.findViewById(R.id.button3);

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InsecureStorageSharedPref.class);
                startActivity(i);
            }
        });

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InsecureStorageExternal.class);
                startActivity(i);
            }
        });

        m_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InsecureStorageSQLite.class);
                startActivity(i);
            }
        });

        m_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        String pref_string = Float.toString(shared_pref_score);
        if (pref_string.equals("6.25")) {
            m_btn.setEnabled(false);
            m_btn.setText("Done");
        }

        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        String exter_string = Float.toString(external_str_score);
        if (exter_string.equals("6.25")) {
            m_btn2.setEnabled(false);
            m_btn2.setText("Done");
        }

        float sqlite_str_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);
        String sqlite_string = Float.toString(sqlite_str_score);
        if (sqlite_string.equals("6.25")) {
            m_btn3.setEnabled(false);
            m_btn3.setText("Done");
        }
        return view;
    }
}