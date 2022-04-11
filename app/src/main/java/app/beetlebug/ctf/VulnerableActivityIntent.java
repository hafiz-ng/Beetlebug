
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
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class VulnerableActivityIntent extends AppCompatActivity {

    EditText edt_flag;
    Button btn_flag, btn_ctf;
    SharedPreferences sharedPreferences, preferences;
    public static String ctf_score_intent_redirect = "ctf_score_intent_redirect";

    public static String flag_scores = "flag_scores";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_intent);
        btn_ctf = findViewById(R.id.button);
        btn_flag = findViewById(R.id.buttonFlag);

        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        btn_ctf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VulnerableActivityIntent.this, "Look for exported activity to re-direct intent", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        String pref_result = preferences.getString("6_activity", "");
        byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);

        if (m_flag.getText().toString().equals(text)) {
            float user_score_intent_redirect = 6.25F;

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat(ctf_score_intent_redirect, user_score_intent_redirect);
            editor.apply();

            Intent ctf_captured = new Intent(VulnerableActivityIntent.this, FlagCaptured.class);
            String intent_str = Float.toString(user_score_intent_redirect);
            ctf_captured.putExtra("intent_str", intent_str);
            startActivity(ctf_captured);
        } else {
            m_flag.setError("Wrong answer");
        }

    }
}