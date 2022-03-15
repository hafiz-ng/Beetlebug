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
    static TextView m_total_ctf_points;

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
        setupProgressBar();
    }

    private void setupProgressBar() {

        // retrieve ctf score from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        int sqlite_score = sharedPreferences.getInt("ctf_score_sqlite", 0);
        int shared_pref_score = sharedPreferences.getInt("ctf_score_shared_pref", 0);
        int secret_source_score = sharedPreferences.getInt("ctf_score_secret_source", 0);
        int total_score = sqlite_score + shared_pref_score + secret_source_score;

        String str_score = Integer.toString(total_score);
        m_total_ctf_points.setText(str_score + "XP");
        Toast.makeText(FlagCaptured.this, "CTF Score: " + total_score, Toast.LENGTH_SHORT).show();

        dailyProgressBar.setProgressWithAnimation(total_score, 2000);
    }

    public void continueCTF(View view) {
        Intent i = new Intent(FlagCaptured.this, MainActivity.class);
        startActivity(i);
    }

}