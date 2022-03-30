package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class InsecureStorageSharedPref extends AppCompatActivity {
    public static String flag_scores = "flag_scores";
    public static String m_username = "username";
    public static String m_password = "password";
    public static String ctf_score_shared_pref = "ctf_score_shared_pref";
    public static String shared_pref_flag = "shared_pref_flag";

    EditText et_username, et_password;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences_pref;
    LinearLayout ctf_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_storage_sharedpref);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
        sharedPreferences_pref = getSharedPreferences(shared_pref_flag, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        ctf_layout = (LinearLayout) findViewById(R.id.layoutCtf);
        ctf_layout.setVisibility(View.GONE);
    }


    public void saveUser(View view) {
        et_username = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword);

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        ctf_layout.setVisibility(View.VISIBLE);


        if (username.isEmpty()) {
            Toast.makeText(InsecureStorageSharedPref.this, "Input your username", Toast.LENGTH_SHORT).show();
            et_username.setError("Username cannot be blank");
        } if (password.isEmpty())
            et_password.setError("Password field is empty");
        else {
            String pref_flg = "flag 3";
            SharedPreferences.Editor editor = sharedPreferences_pref.edit();
            editor.putString(m_username, username);
            editor.putString(m_password, password);
            editor.putString(pref_flg, "0xe982c04");
            editor.clear();
            editor.commit();
            Toast.makeText(InsecureStorageSharedPref.this, "Login successful", Toast.LENGTH_SHORT).show();

        }

    }

    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        if (m_flag.getText().toString().equals("0xe982c04")) {
            int user_score_shared_pref = 9;
            String ctf_status = "shared_pref_ctf_status";

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ctf_score_shared_pref, user_score_shared_pref);
            editor.putBoolean(ctf_status, true);
            editor.commit();

            Intent ctf_captured = new Intent(InsecureStorageSharedPref.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_shared_pref", user_score_shared_pref);
            startActivity(ctf_captured);
        } else {
            Toast.makeText(InsecureStorageSharedPref.this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

    }
}