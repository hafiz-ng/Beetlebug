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


        // TODO: Check Beetle Points
        beetlePoints();
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
        int xss_score = sharedPreferences.getInt("ctf_score_xss", 0);
        int content_score = sharedPreferences.getInt("ctf_score_content_provider", 0);
        int patch_score = sharedPreferences.getInt("ctf_score_patch", 0);
        int clip_score = sharedPreferences.getInt("ctf_score_clip", 0);
        int auth_score = sharedPreferences.getInt("ctf_score_auth", 0);
        int webview_score = sharedPreferences.getInt("ctf_score_webview", 0);


        int total_score = sqlite_score + shared_pref_score + secret_source_score + secret_string_score + external_str_score + firebase_score
                + sqli_score + intent_redirect_score + service_score + log_score + xss_score + content_score + patch_score
                + clip_score + auth_score + webview_score;

        String str_score = Integer.toString(total_score);

        m_total_ctf_points.setText(str_score + "XP");

        dailyProgressBar.setProgressWithAnimation(total_score, 2000);
    }

    public void continueCTF(View view) {
        Intent i = new Intent(FlagCaptured.this, MainActivity.class);
        startActivity(i);
    }

    private void beetlePoints() {
        Intent i = getIntent();
        int result = i.getIntExtra("ctf_score_secret_string", 0);
        int result2 = i.getIntExtra("ctf_score_secret_source", 0);
        int result3 = i.getIntExtra("ctf_score_shared_pref", 0);
        int result4 = i.getIntExtra("ctf_score_external_str", 0);
        int result5 = i.getIntExtra("ctf_score_sqlite", 0);
        int result6 = i.getIntExtra("ctf_score_webview", 0);
        int result7 = i.getIntExtra("ctf_score_xss", 0);
        int result8 = i.getIntExtra("ctf_score_intent_redirect", 0);
        int result9 = i.getIntExtra("ctf_score", 0);
        int result10 = i.getIntExtra("ctf_score_content_provider", 0);
        int result11 = i.getIntExtra("ctf_score_log", 0);
        int result12 = i.getIntExtra("ctf_score_clip", 0);
        int result13 = i.getIntExtra("ctf_score_patch", 0);
        int result14 = i.getIntExtra("ctf_score_firebase", 0);
        int result15 = i.getIntExtra("ctf_score_auth", 0);
        int result16 = i.getIntExtra("ctf_score_sqli", 0);


        String str = Integer.toString(result);
        String str2 = Integer.toString(result2);
        String str3 = Integer.toString(result3);
        String str4 = Integer.toString(result4);
        String str5 = Integer.toString(result5);
        String str6 = Integer.toString(result6);
        String str7 = Integer.toString(result7);
        String str8 = Integer.toString(result8);
        String str9 = Integer.toString(result9);
        String str10 = Integer.toString(result10);
        String str11 = Integer.toString(result11);
        String str12 = Integer.toString(result12);
        String str13 = Integer.toString(result13);
        String str14 = Integer.toString(result14);
        String str15 = Integer.toString(result15);
        String str16 = Integer.toString(result16);


        TextView ctf_point = (TextView) findViewById(R.id.ctfPoint);
        ctf_point.setText(str);
        ctf_point.setText(str2);
        ctf_point.setText(str3);
        ctf_point.setText(str4);
        ctf_point.setText(str5);
        ctf_point.setText(str6);
        ctf_point.setText(str7);
        ctf_point.setText(str8);
        ctf_point.setText(str9);
        ctf_point.setText(str10);
        ctf_point.setText(str11);
        ctf_point.setText(str12);
        ctf_point.setText(str13);
        ctf_point.setText(str14);
        ctf_point.setText(str15);
        ctf_point.setText(str16);

    }
}