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
    TextView m_total_ctf_points, ctf_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_captured);
        m_total_ctf_points = (TextView) findViewById(R.id.totalCTFPoints);
        ctf_point = (TextView) findViewById(R.id.ctfPoint);

        dailyProgressBar = (CustomProgressBar) findViewById(R.id.user_progress_bar);
        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.default_light_grey_bg));
        }
        // TODO: Check Beetle Points
//        int shared_pref_score = getIntent().getExtras().getInt("shared_pref_score_intent");
//        String flag_score = Integer.toString(shared_pref_score);
//        ctf_point.setText(flag_score);
        setupProgressBar();
    }

    public void setupProgressBar() {

        // retrieve ctf score from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        int sqlite_score = sharedPreferences.getInt("ctf_score_sqlite", 0);
        int shared_pref_score = sharedPreferences.getInt("ctf_score_shared_pref", 0);
        int secret_source_score = sharedPreferences.getInt("ctf_score_secret_source", 0);
        int secret_string_score = sharedPreferences.getInt("ctf_score_secret_string", 0);
        int external_str_score = sharedPreferences.getInt("ctf_score_external", 0);
        int firebase_score = sharedPreferences.getInt("ctf_score_firebase", 0);
        int sqli_score = sharedPreferences.getInt("ctf_score_sqli", 0);
        int intent_redirect_score = sharedPreferences.getInt("ctf_score_intent_redirect", 0);
        int service_score = sharedPreferences.getInt("ctf_score_service", 0);
        int log_score = sharedPreferences.getInt("ctf_score_log", 0);
        int total_score = sqlite_score + shared_pref_score + secret_source_score + secret_string_score + external_str_score + firebase_score
                + sqli_score + intent_redirect_score + service_score + log_score;

        String str_score = Integer.toString(total_score);

        m_total_ctf_points.setText(str_score + "XP");
//        Toast.makeText(FlagCaptured.this, "CTF Score: " + total_score, Toast.LENGTH_SHORT).show();

        dailyProgressBar.setProgressWithAnimation(total_score, 2000);
    }

    public void continueCTF(View view) {
        Intent i = new Intent(FlagCaptured.this, MainActivity.class);
        startActivity(i);
    }

}