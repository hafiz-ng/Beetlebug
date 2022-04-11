package app.beetlebug.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.utils.CustomProgressBar;

public class PlayerStats extends AppCompatActivity {

    CustomProgressBar userProgress;
    ImageView m_btn_back;
    TextView flags_captured;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userProgress = (CustomProgressBar) findViewById(R.id.user_progress_bar);
        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        m_btn_back = findViewById(R.id.back);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlayerStats.this, MainActivity.class);
                startActivity(i);
            }
        });
        setupProgressBar();
        setUpFlagsCaptured();
    }

    public void setupProgressBar() {

        // retrieve ctf score from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float secret_source_score = sharedPreferences.getFloat("ctf_score_secret_source", 0);
        float secret_string_score = sharedPreferences.getFloat("ctf_score_secret_string", 0);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        float sqlite_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);
        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);
        float intent_redirect_score = sharedPreferences.getFloat("ctf_score_intent_redirect", 0);
        float service_score = sharedPreferences.getFloat("ctf_score_service", 0);
        float content_score = sharedPreferences.getFloat("ctf_score_content_provider", 0);
        float auth_score = sharedPreferences.getFloat("ctf_score_auth", 0);
        float clip_score = sharedPreferences.getFloat("ctf_score_clip", 0);
        float log_score = sharedPreferences.getFloat("ctf_score_log", 0);
        float firebase_score = sharedPreferences.getFloat("ctf_score_firebase", 0);
        float sqli_score = sharedPreferences.getFloat("ctf_score_sqli", 0);
        float patch_score = sharedPreferences.getFloat("ctf_score_patch", 0);



        float total_score = sqlite_score + shared_pref_score + secret_source_score + secret_string_score + external_str_score + firebase_score
                + sqli_score + intent_redirect_score + service_score + log_score + xss_score + content_score + patch_score
                + clip_score + auth_score + webview_score;
        userProgress.setProgressWithAnimation(total_score, 2000);
    }

    public void setUpFlagsCaptured() {
        // retrieve ctf score from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float secret_source_score = sharedPreferences.getFloat("ctf_score_secret_source", 0);
        float secret_string_score = sharedPreferences.getFloat("ctf_score_secret_string", 0);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        float sqlite_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);
        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);
        float intent_redirect_score = sharedPreferences.getFloat("ctf_score_intent_redirect", 0);
        float service_score = sharedPreferences.getFloat("ctf_score_service", 0);
        float content_score = sharedPreferences.getFloat("ctf_score_content_provider", 0);
        float auth_score = sharedPreferences.getFloat("ctf_score_auth", 0);
        float clip_score = sharedPreferences.getFloat("ctf_score_clip", 0);
        float log_score = sharedPreferences.getFloat("ctf_score_log", 0);
        float firebase_score = sharedPreferences.getFloat("ctf_score_firebase", 0);
        float sqli_score = sharedPreferences.getFloat("ctf_score_sqli", 0);
        float patch_score = sharedPreferences.getFloat("ctf_score_patch", 0);



        float total_score = sqlite_score + shared_pref_score + secret_source_score + secret_string_score + external_str_score + firebase_score
                + sqli_score + intent_redirect_score + service_score + log_score + xss_score + content_score + patch_score
                + clip_score + auth_score + webview_score;


        String stringF = Float.toString(total_score);
        flags_captured = findViewById(R.id.flag_score);

        switch(stringF) {
            case "6.25" :
                flags_captured.setText("1");
                break;
            case "12.5" :
                flags_captured.setText("2");
                break;
            case "18.75" :
                flags_captured.setText("3");
                break;
            case "25.0" :
                flags_captured.setText("4");
                break;
            case "31.25" :
                flags_captured.setText("5");
                break;
            case "37.5" :
                flags_captured.setText("6");
                break;
            case "43.75" :
                flags_captured.setText("7");
                break;
            case "50.0" :
                flags_captured.setText("8");
                break;
            case "56.25" :
                flags_captured.setText("9");
                break;
            case "62.5" :
                flags_captured.setText("10");
                break;
            case "68.75" :
                flags_captured.setText("11");
                break;
            case "75.0" :
                flags_captured.setText("12");
                break;
            case "81.25" :
                flags_captured.setText("13");
                break;
            case "87.5" :
                flags_captured.setText("14");
                break;
            case "93.75" :
                flags_captured.setText("15");
                break;
            case "100.0" :
                flags_captured.setText("16");
                break;
            default :
                flags_captured.setText("0");// Optional
        }

    }


}