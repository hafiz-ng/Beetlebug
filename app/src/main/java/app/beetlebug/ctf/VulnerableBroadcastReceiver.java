package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import app.beetlebug.R;

public class VulnerableBroadcastReceiver extends AppCompatActivity {

    private String input_broadcast = "2000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_broadcast_receiver);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }



    }

    public void sendBroadcastData(View view) {
        Intent intent = new Intent(getApplicationContext(), VulnerableBroadcastReceiver.class);
        intent.setAction("vulnerable.vulnerablereceiver.LOG");
        intent.putExtra("data", input_broadcast);

        sendBroadcast(intent);

    }
}