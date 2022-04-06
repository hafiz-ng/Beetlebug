package app.beetlebug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import app.beetlebug.adapter.SlideViewPagerAdapter;
import app.beetlebug.user.UserSignUp;

public class Walkthrough extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.yellow_700));
        }


        viewPager = findViewById(R.id.viewPager);
        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        if(isOpenAlready()) {
            Intent intent = new Intent(Walkthrough.this, UserSignUp.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = getSharedPreferences("walkthrough", MODE_PRIVATE).edit();
            editor.putBoolean("walkthrough", true);
            editor.commit();
        }
    }

    private boolean isOpenAlready() {
        SharedPreferences sharedPreferences=getSharedPreferences("walkthrough", MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("walkthrough",false);
        return result;
    }
}