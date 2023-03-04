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
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.ctf.BinaryPatchActivity;
import app.beetlebug.ctf.VulnerableActivityIntent;
import app.beetlebug.fragments.AndroidComponentsFragment;
import app.beetlebug.fragments.BiometricFragment;
import app.beetlebug.fragments.DatabasesFragment;
import app.beetlebug.fragments.InsecureStorageFragment;
import app.beetlebug.fragments.SecretsFragment;
import app.beetlebug.fragments.SensitiveDataFragment;
import app.beetlebug.fragments.WebViewFragment;
import app.beetlebug.utils.CustomProgressBar;

public class FlagsOverview extends AppCompatActivity {
    ImageView mBackButton;
    ScrollView mScrollView;
    RelativeLayout mToolbar;
    Button mfinish;
    TextView flags_captured, ctf_player;


    CustomProgressBar hardcodedSecretsProgressBar;
    CustomProgressBar webViewsProgressBar;
    CustomProgressBar androidComponentsProgressBar;
    CustomProgressBar insecureStoreProgressBar;
    CustomProgressBar sensitiveInfoProgressBar;
    CustomProgressBar databasesProgressBar;
    CustomProgressBar patchDetectionProgressBar;
    CustomProgressBar bioProgressBar;
    SharedPreferences sharedPreferences, userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags_overview);
        mBackButton = findViewById(R.id.back);
        mScrollView = findViewById(R.id.scrollview_flags);
        mToolbar = findViewById(R.id.toolbar);

        hardcodedSecretsProgressBar = findViewById(R.id.hardcoded_secrets_bar);
        webViewsProgressBar = findViewById(R.id.progress_bar_webview);
        insecureStoreProgressBar = findViewById(R.id.progress_bar_local_storage);
        androidComponentsProgressBar = findViewById(R.id.progress_bar_components);
        sensitiveInfoProgressBar = findViewById(R.id.progress_bar_sensitive_info);
        patchDetectionProgressBar = findViewById(R.id.progress_bar_binary);
        databasesProgressBar = findViewById(R.id.progress_bar_database);
        webViewsProgressBar = findViewById(R.id.progress_bar_webview);
        bioProgressBar = findViewById(R.id.progress_bar_bio);

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

        setUpFlagsCaptured();

        setupProgressBarInsecureStorage();
        setupProgressBarHardCodedSecrets();
        setUpProgressBarAndroidComponents();
        setUpProgressBarInfoDiscl();
        setUpProgressBarDatabases();
        setUpProgressBarWebViews();
        setUpProgressBarPatch();
        setUpProgressBarAuth();
    }

    private void setUpProgressBarAuth() {
        float auth_score = sharedPreferences.getFloat("ctf_score_auth", 0);
        String total_string = Float.toString(auth_score);
        if (total_string.equals("6.25")) {
            bioProgressBar.setProgressWithAnimation(100, 2000);
        }
    }
    private void setUpProgressBarAndroidComponents() {
        float service_score = sharedPreferences.getFloat("ctf_score_service", 0);
        float content_score = sharedPreferences.getFloat("ctf_score_content_provider", 0);
        float intent_redirect_score = sharedPreferences.getFloat("ctf_score_intent_redirect", 0);

        float total_score = service_score + content_score + intent_redirect_score;
        String total_string = Float.toString(total_score);

        if(total_string.equals("6.25")) {
            androidComponentsProgressBar.setProgressWithAnimation(33, 2000);
        } else if (total_string.equals("12.5")){
            androidComponentsProgressBar.setProgressWithAnimation(66, 2000);
        } else if (total_string.equals("18.75"))
            androidComponentsProgressBar.setProgressWithAnimation(100, 2000);
    }


    private void setUpProgressBarWebViews() {
        float xss_score = sharedPreferences.getFloat("ctf_score_xss", 0);
        float webview_score = sharedPreferences.getFloat("ctf_score_webview", 0);
        float total_score = xss_score + webview_score;
        String total_string = Float.toString(total_score);

        if(total_string.equals("6.25")) {
            webViewsProgressBar.setProgressWithAnimation(50, 2000);
        } else if (total_string.equals("12.5")) {
            webViewsProgressBar.setProgressWithAnimation(100, 2000);
        }
    }


    private void setUpProgressBarInfoDiscl() {
        float log_score = sharedPreferences.getFloat("ctf_score_log", 0);
        float clip_score = sharedPreferences.getFloat("ctf_score_clip", 0);
        float total_score = log_score + clip_score;
        String total_string = Float.toString(total_score);
        if(total_string.equals("6.25")) {
            sensitiveInfoProgressBar.setProgressWithAnimation(50, 2000);
        } else if (total_string.equals("12.5")) {
            sensitiveInfoProgressBar.setProgressWithAnimation(100, 2000);
        }
    }

    private void setUpProgressBarDatabases() {
        float firebase_score = sharedPreferences.getFloat("ctf_score_firebase", 0);
        float sqli_score = sharedPreferences.getFloat("ctf_score_sqli", 0);

        float total_score = firebase_score + sqli_score;
        String total_string = Float.toString(total_score);

        if(total_string.equals("6.25")) {
            databasesProgressBar.setProgressWithAnimation(50, 2000);
        } else if (total_string.equals("12.5")) {
            databasesProgressBar.setProgressWithAnimation(100, 2000);
        }
    }

    private void setUpProgressBarPatch() {
        float root_score = sharedPreferences.getFloat("ctf_score_patch", 0);
        String score = Float.toString(root_score);
        if(score.equals("6.25"))
        patchDetectionProgressBar.setProgressWithAnimation(100, 2000);
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


    // Setup Progress Bar
    private void setupProgressBarHardCodedSecrets() {
        float secret_string_score = sharedPreferences.getFloat("ctf_score_secret_string", 0);
        float secret_source_score = sharedPreferences.getFloat("ctf_score_secret_source", 0);

        float total_score = secret_source_score + secret_string_score;
//        Toast.makeText(FlagsOverview.this, "Total Score: " + total_score, Toast.LENGTH_LONG).show();
        String total_string = Float.toString(total_score);
        if(total_string.equals("6.25")) {
            hardcodedSecretsProgressBar.setProgressWithAnimation(50, 2000);
        } else if (total_string.equals("12.5")){
            hardcodedSecretsProgressBar.setProgressWithAnimation(100, 2000);
        }
    }

    private void setupProgressBarInsecureStorage() {
        float external_str_score = sharedPreferences.getFloat("ctf_score_external", 0);
        float shared_pref_score = sharedPreferences.getFloat("ctf_score_shared_pref", 0);
        float sqlite_score = sharedPreferences.getFloat("ctf_score_sqlite", 0);

        float total_score = external_str_score + shared_pref_score + sqlite_score;
        String total_string = Float.toString(total_score);

        if(total_string.equals("6.25")) {
            insecureStoreProgressBar.setProgressWithAnimation(33, 2000);
        } else if (total_string.equals("12.5")){
            insecureStoreProgressBar.setProgressWithAnimation(66, 2000);
        } else if (total_string.equals("18.75"))
        insecureStoreProgressBar.setProgressWithAnimation(100, 2000);
    }




    public void inSecureLoggingFlag(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new SensitiveDataFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void patchBinary (View view) {
        Intent i = new Intent(FlagsOverview.this, BinaryPatchActivity.class);
        startActivity(i);
    }
    public void submitFlags(View view) {
        final Dialog finish_dialog = new Dialog(FlagsOverview.this);
        finish_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

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


        String num = Float.toString(total_score);

        switch (num) {
            case "6.25":
                finish_dialog.setContentView(R.layout.try_again_sheet);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;
            case "12.5":
                finish_dialog.setContentView(R.layout.try_again_sheet);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;
            case "25.0":
                finish_dialog.setContentView(R.layout.try_again_sheet);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;

            case "31.25":
                finish_dialog.setContentView(R.layout.bottom_sheet_continue);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;

            case "37.5":
                finish_dialog.setContentView(R.layout.bottom_sheet_continue);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;

            case "43.75":
                finish_dialog.setContentView(R.layout.bottom_sheet_continue);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;

            case "100.0":

                finish_dialog.setContentView(R.layout.bottom_sheet);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);
                break;

            default :
                finish_dialog.setContentView(R.layout.try_again_sheet);
                finish_dialog.show();
                finish_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                finish_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                finish_dialog.getWindow().setGravity(Gravity.BOTTOM);        }
    }

    public void shareResult(View view) {
        String url = "https://github.com/hafiz-ng/beetlebug";

        // define MIME type
        String mimeType = "text/plain";

        String ctf_twitter = "@BeetlebugCTF";

        // create a share widget, with options on how to share the text
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText("Yayy! I have captured all the flags on " + ctf_twitter  + " " + url)
                .startChooser();
    }

    public void embeddedSecrets(View view) {
        mScrollView.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new SecretsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
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


    public void continueFlag(View view) {
        Intent continue_ctf = new Intent(FlagsOverview.this, FlagsOverview.class);
        startActivity(continue_ctf);
    }

    public void continueUser(View view) {
        Intent continue_ctf = new Intent(FlagsOverview.this, FlagsOverview.class);
        startActivity(continue_ctf);
    }
}