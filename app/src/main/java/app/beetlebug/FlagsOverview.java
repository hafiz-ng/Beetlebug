package app.beetlebug;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import app.beetlebug.ctf.VulnerableActivityIntent;
import app.beetlebug.fragments.AndroidComponentsFragment;
import app.beetlebug.fragments.BiometricFragment;
import app.beetlebug.fragments.DatabasesFragment;
import app.beetlebug.fragments.InsecureStorageFragment;
import app.beetlebug.fragments.NetworkFragment;
import app.beetlebug.fragments.SecretsFragment;
import app.beetlebug.fragments.SensitiveDataFragment;
import app.beetlebug.fragments.WebViewFragment;
import app.beetlebug.utils.CustomProgressBar;

public class FlagsOverview extends AppCompatActivity {



    ImageView mBackButton;
    ScrollView mScrollView;
    RelativeLayout mToolbar;
    Button mfinish;

    CustomProgressBar insecureStorageProgressBar;
    CustomProgressBar dailyProgressBar2;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_overview);
        mBackButton = findViewById(R.id.arrowLeft);
        mScrollView = findViewById(R.id.scrollview_flags);
        mToolbar = findViewById(R.id.toolbar);
        insecureStorageProgressBar = findViewById(R.id.insecure_storage_bar);
        dailyProgressBar2 = findViewById(R.id.progress_bar_webview);
        mfinish = findViewById(R.id.finish_button);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

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

        mfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog finish_dialog = new Dialog(FlagsOverview.this);
                finish_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                finish_dialog.setContentView(R.layout.bottom_sheet);

                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        setupProgress2();
        setupProgressBarInsecureStorage();
    }

    public void inSecureStorage (View v) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new InsecureStorageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void biometricAuth (View v) {
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

    public void  inSecureActivity (View v) {
        Intent i = new Intent(FlagsOverview.this, VulnerableActivityIntent.class);
        startActivity(i);
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

    private void setupProgressBarInsecureStorage() {
        int secret_string_score = sharedPreferences.getInt("ctf_score_secret_string", 0);
        int secret_source_score = sharedPreferences.getInt("ctf_score_secret_source", 0);

        int total_score = secret_source_score + secret_string_score;
//        Toast.makeText(FlagsOverview.this, "Total Score: " + total_score, Toast.LENGTH_LONG).show();
        String total_string = Integer.toString(total_score);
        if(total_string.equals("9")) {
            insecureStorageProgressBar.setProgressWithAnimation(50, 2000);
        } else if (total_string.equals("18")){
            insecureStorageProgressBar.setProgressWithAnimation(100, 2000);
        }
    }

    private void setupProgress2() {
        int dailyXp = 65;
        dailyProgressBar2.setProgressWithAnimation(dailyXp, 2000);

    }

    public void inSecureLoggingFlag(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new SensitiveDataFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void networkCom(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new NetworkFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();

    }
    public void submitFlags(View view) {
    }

    public void cancelSheet(View view) {


    }

    public void shareResult(View view) {
        String url = "https://github.com/hafiz-ng/beetlebug";

        // define MIME type
        String mimeType = "text/plain";

        String author_twitter_url = "@hafiz__ng";

        // create a share widget, with options on how to share the text
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText("Get Started today " + url + " CTF Author " + author_twitter_url)
                .startChooser();
    }

    public void embeddeSecrets(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new SecretsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
}