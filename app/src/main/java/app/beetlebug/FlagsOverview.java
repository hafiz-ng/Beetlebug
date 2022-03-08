package app.beetlebug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import app.beetlebug.ctf.VulnerableActivity1;
import app.beetlebug.fragments.AndroidComponentsFragment;
import app.beetlebug.fragments.BiometricFragment;
import app.beetlebug.fragments.DatabasesFragment;
import app.beetlebug.fragments.InsecureStorageFragment;
import app.beetlebug.fragments.WebViewFragment;
import app.beetlebug.utils.CustomProgressBar;

public class FlagsOverview extends AppCompatActivity {


    ImageView mBackButton;
    ScrollView mScrollView;
    RelativeLayout mToolbar;

    CustomProgressBar dailyProgressBar1;
    CustomProgressBar dailyProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_overview);
        mBackButton = findViewById(R.id.arrowLeft);
        mScrollView = findViewById(R.id.scrollview_flags);
        mToolbar = findViewById(R.id.toolbar);
        dailyProgressBar1 = findViewById(R.id.insecure_storage_bar);
        dailyProgressBar2 = findViewById(R.id.progress_bar_webview);


        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_intent = new Intent(FlagsOverview.this, MainActivity.class);
                startActivity(home_intent);
            }
        });

        setupProgress2();
        setupProgressBar();

    }

    public void inSecureStorage (View v) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new InsecureStorageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void biometricAuthentication (View v) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new BiometricFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void  webViewFlag (View v) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new WebViewFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

//    public void  inSecureLoggingFlag (View v) {
//        Intent i = new Intent(FlagsOverview.this, InsecureLogging.class);
//        startActivity(i);
//    }

    public void  inSecureActivity (View v) {
        Intent i = new Intent(FlagsOverview.this, VulnerableActivity1.class);
        startActivity(i);
    }

    public void fingerActivity(View view) {
        Intent finger_intent = new Intent(FlagsOverview.this, FingerPrintActivity.class);
        startActivity(finger_intent);
    }

    public void AndroidComponents(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new AndroidComponentsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void databaseFlag(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new DatabasesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    private void setupProgressBar() {
        int dailyXp = 20;

        dailyProgressBar1.setProgressWithAnimation(dailyXp, 2000);

    }

    private void setupProgress2() {
        int dailyXp = 65;

        dailyProgressBar2.setProgressWithAnimation(dailyXp, 2000);

    }

}