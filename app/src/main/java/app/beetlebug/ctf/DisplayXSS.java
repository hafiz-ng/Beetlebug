package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebChromeClient;

import app.beetlebug.R;

public class DisplayXSS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_xss);
        Intent i = getIntent();
        String result = i.getStringExtra(WebViewXSSActivity.PACKAGE_STRING);
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebChromeClient client = new WebChromeClient();
        webView.setWebChromeClient(client);
        webView.loadData(result, "text/html", "UTF-8");
    }

    public void goBack(View view) {
        startActivity(new Intent(DisplayXSS.this, WebViewXSSActivity.class));
    }

    public void copyResult(View view) {

        TextView tv = findViewById(R.id.textViewFlag);
        String result = (String) tv.getText();
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("TextView", result);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(DisplayXSS.this, "Copied to clipboard: " + result, Toast.LENGTH_SHORT).show();
    }
}