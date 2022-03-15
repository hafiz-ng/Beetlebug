package app.beetlebug.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.utils.CustomProgressBar;

public class UserProfileActivity extends AppCompatActivity {

    CustomProgressBar userProgress;
    ImageView m_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userProgress = (CustomProgressBar) findViewById(R.id.user_progress_bar);

        userProgress.setProgressWithAnimation(23, 2000);

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
                Intent i = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


}