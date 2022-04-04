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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class WebViewXSSActivity extends AppCompatActivity {
    public WebView myWebView;
    SharedPreferences sharedPreferences;
    TextView url2;
    LinearLayout lin;
    EditText m_flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_xssactivity);
        lin = (LinearLayout) findViewById(R.id.linear_layout4);

        m_flag = findViewById(R.id.flag);
        lin.setVisibility(View.GONE);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        url2 = findViewById(R.id.editTextUrl);
        url2.setText("https://xss.rocks/scriptlet.html");
        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.VISIBLE);
                m_flag.setText("0x66r921");
                Log.i("beetlebug", "Clicked XSS button");
                myWebView.loadUrl("https://xss.rocks/scriptlet.html");
            }
        });
        loadWebView();
    }


    public void loadWebView() {
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        myWebView.setWebChromeClient(new WebChromeClient());

        // setting up configuration for WebView
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }




        public void captureFlag(View view) {

        if (m_flag.getText().toString().equals("0x66r921")) {
            int user_score_xss = 5;

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_xss", user_score_xss);
            editor.commit();

            Intent ctf_captured = new Intent(WebViewXSSActivity.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_xss", user_score_xss);
            startActivity(ctf_captured);
        } else {
            Toast.makeText(WebViewXSSActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

    }

}