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
            int user_score_webview = 5;
            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_webview", user_score_webview);
            editor.apply();

            Intent ctf_captured = new Intent(WebViewURLActivity.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_webview", user_score_webview);
            startActivity(ctf_captured);

        } else {
            m_flag.setError("Wrong answer");
        }
    }
    // TODO: MOVE

//    private void loadWebView() {
//        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.setWebChromeClient(new WebChromeClient() {
//            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
//                Log.d("Beetlebug-app", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
//                return true;
//            }
//        });
//        webView.setWebViewClient(new WebViewClient());
//        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        if (getIntent().getExtras().getBoolean("is_reg", false)) {
//            webView.loadUrl("file:///android_asset/pwn.html");
//        } else {
//            webView.loadUrl(getIntent().getStringExtra("reg_url"));
//        }
//    }
}