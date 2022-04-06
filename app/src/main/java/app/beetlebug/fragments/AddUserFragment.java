package app.beetlebug.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import app.beetlebug.R;
import app.beetlebug.ctf.b33tleAdministrator;
import app.beetlebug.db.DatabaseHelper;

public class AddUserFragment extends Fragment {


    Button add_user;
    ImageView back_btn;
    EditText username, pass;

    private DatabaseHelper db;

    public AddUserFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        add_user = view.findViewById(R.id.addUser);
        back_btn = view.findViewById(R.id.back);
        username = view.findViewById(R.id.editTextUsername);
        pass = view.findViewById(R.id.editTextPassword);
        db = new DatabaseHelper(getActivity());
        db.open();

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt = view.findViewById(R.id.editTextUsername);
                EditText pass = view.findViewById(R.id.editTextPassword);

                String edt_username = edt.getText().toString();
                String edt_pass = pass.getText().toString();

                if (edt_username.isEmpty()) {
                    edt.setError("Username is empty");
                } else if (edt_pass.isEmpty()) {
                    pass.setError("Password is required");
                } else {
                    db.add(edt_username, edt_pass);
                    Toast.makeText(getActivity(), "User Added Successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctf_intent = new Intent(getActivity(), b33tleAdministrator.class);
                startActivity(ctf_intent);
            }
        });
        return view;
    }
}