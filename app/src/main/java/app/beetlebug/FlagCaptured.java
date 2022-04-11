package app.beetlebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.utils.CustomProgressBar;

public class FlagCaptured extends AppCompatActivity {


    CustomProgressBar dailyProgressBar;
    TextView m_total_ctf_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_captured);
        m_total_ctf_points = (TextView) findViewById(R.id.totalCTFPoints);

        dailyProgressBar = (CustomProgressBar) findViewById(R.id.user_progress_bar);
        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.default_light_grey_bg));
        }

        beetlePoints();
        setupProgressBar();
    }

    public void setupProgressBar() {
        // retrieve ctf score from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        float sqlite_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        float secret_source_score = sharedPreferences.getFloat("ctf_score_secret_source", 0);
        float secret_string_score = sharedPreferences.getFloat("ctf_score_secret_string", 0);
        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        float firebase_score = sharedPreferences.getFloat("ctf_score_firebase", 0);
        float sqli_score = sharedPreferences.getFloat("ctf_score_sqli", 0);
        float intent_redirect_score = sharedPreferences.getFloat("ctf_score_intent_redirect", 0);
        float service_score = sharedPreferences.getFloat("ctf_score_service", 0);
        float log_score = sharedPreferences.getFloat("ctf_score_log", 0);
        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float content_score = sharedPreferences.getFloat("ctf_score_content_provider", 0);
        float patch_score = sharedPreferences.getFloat("ctf_score_patch", 0);
        float clip_score = sharedPreferences.getFloat("ctf_score_clip", 0);
        float auth_score = sharedPreferences.getFloat("ctf_score_auth", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);


        float total_score = sqlite_score + shared_pref_score + secret_source_score + secret_string_score + external_str_score + firebase_score
                + sqli_score + intent_redirect_score + service_score + log_score + xss_score + content_score + patch_score
                + clip_score + auth_score + webview_score;

        m_total_ctf_points.setText(total_score + "XP");

        dailyProgressBar.setProgressWithAnimation(total_score, 2000);
    }

    public void continueCTF(View view) {
        Intent i = new Intent(FlagCaptured.this, MainActivity.class);
        startActivity(i);
    }

    private void beetlePoints() {
        Intent i = getIntent();
        if (i.getStringExtra("intent_str").equals("6.25")) {
            TextView ctf_point = (TextView) findViewById(R.id.ctfPoint);
            ctf_point.setText("6.25");
        }

    }
}