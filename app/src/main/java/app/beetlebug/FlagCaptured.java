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

import app.beetlebug.utils.CustomProgressBar;

public class FlagCaptured extends AppCompatActivity {


    CustomProgressBar dailyProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_captured);

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
        int ctfScore = sharedPreferences.getInt("ctf_score", 0);
//        int dailyXp = 65;
//
//        dailyProgressBar.setProgressWithAnimation(dailyXp, 2000);

        dailyProgressBar.setProgressWithAnimation(ctfScore, 2000);
    }

    public void continueCTF(View view) {
        Intent i = new Intent(FlagCaptured.this, MainActivity.class);
        startActivity(i);
    }
}