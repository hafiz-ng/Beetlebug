package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import app.beetlebug.R;

public class VulnerableBroadcastReceiver extends AppCompatActivity {

    private String location;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_broadcast_receiver);
        webView = (WebView) findViewById(R.id.webView);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        location = this.getExternalFilesDir(null) + "/broadcast.html";
        webView.loadUrl("file://" + location);
    }

    public void buttonBack(View view) {

    }
}