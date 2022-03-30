package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import app.beetlebug.R;
import app.beetlebug.utils.WebAppInterface;

public class WebViewXSSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_xssactivity);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
        loadWebView();
    }


    public void loadWebView() {
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        Map<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("Authorization", getUserToken());
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadUrl(getIntent().getStringExtra("support_url"), extraHeaders);
    }

    public static String getUserToken() {
        return UUID.randomUUID().toString();
    }

}