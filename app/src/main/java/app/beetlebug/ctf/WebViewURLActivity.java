package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
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

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class WebViewURLActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences, preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_urlactivity);
        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

//        loadWebView();

    }

    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        String pref_result = preferences.getString("12_url", "");
        byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);
        if (m_flag.getText().toString().equals(text)) {
            float user_score_webview = 6.25F;
            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("ctf_score_webview", user_score_webview);
            editor.apply();

            Intent ctf_captured = new Intent(WebViewURLActivity.this, FlagCaptured.class);
            String intent_str_url = Float.toString(user_score_webview);
            ctf_captured.putExtra("intent_str", intent_str_url);
            startActivity(ctf_captured);

        } else {
            m_flag.setError("Wrong answer");
        }
    }
}