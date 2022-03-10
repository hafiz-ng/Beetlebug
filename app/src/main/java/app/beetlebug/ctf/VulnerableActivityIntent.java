
package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class VulnerableActivityIntent extends AppCompatActivity {

    TextView mCtfTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_intent);
        mCtfTitle = findViewById(R.id.ctfTitle);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }


    public void captureFlag(View view) {
        Intent captured_intent = new Intent(VulnerableActivityIntent.this, FlagCaptured.class);
        startActivity(captured_intent);
    }
}