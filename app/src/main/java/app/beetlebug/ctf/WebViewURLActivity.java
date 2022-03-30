package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class WebViewURLActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_urlactivity);
        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);



        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }

    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        if (m_flag.getText().toString().equals("0x33f3341")) {
            int user_score_webview = 9;

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_webview", user_score_webview);
            editor.commit();

            Intent ctf_captured = new Intent(WebViewURLActivity.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_webview", user_score_webview);
            startActivity(ctf_captured);

        } else {
            m_flag.setError("Wrong answer");
        }
    }
}