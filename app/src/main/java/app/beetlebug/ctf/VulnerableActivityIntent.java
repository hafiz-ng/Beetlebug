
package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class VulnerableActivityIntent extends AppCompatActivity {

    EditText edt_flag;
    Button btn_flag, btn_ctf;
    SharedPreferences sharedPreferences;
    public static String ctf_score_intent_redirect = "ctf_score_intent_redirect";

    public static String flag_scores = "flag_scores";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_intent);
        btn_ctf = findViewById(R.id.button);
        btn_flag = findViewById(R.id.buttonFlag);

        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        btn_ctf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VulnerableActivityIntent.this, "Look for exported activity to re-direct intent", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        if (m_flag.getText().toString().equals("0x334f22")) {
            int user_score_intent_redirect = 5;
            String ctf_status = "intent_redirect_ctf_status";

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ctf_score_intent_redirect, user_score_intent_redirect);
            editor.commit();

            Intent ctf_captured = new Intent(VulnerableActivityIntent.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_intent_redirect", user_score_intent_redirect);
            startActivity(ctf_captured);
        } else {
            m_flag.setError("Wrong answer");
        }

    }
}