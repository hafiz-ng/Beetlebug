package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class WebViewXSSActivity extends AppCompatActivity {
    public WebView myWebView;
    SharedPreferences sharedPreferences, preferences;
    TextView url2;
    LinearLayout lin;
    EditText m_flag;

    public static final String PACKAGE_STRING = "app.beetlebug.ctf.WebViewXSSActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_xssactivity);

        m_flag = findViewById(R.id.flag);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

    }

    public void submitResult(View view) {
        EditText editText = findViewById(R.id.editTextUrl);
        String post = editText.getText().toString();
        if(post.isEmpty()) {
            editText.setError("Field is empty");
        } else {
            Intent intent = new Intent(this, DisplayXSS.class);
            intent.putExtra(PACKAGE_STRING, post);
            startActivity(intent);
        }

    }


        public void captureFlag(View view) {
            EditText m_flag = findViewById(R.id.flag);

            String pref_result = preferences.getString("13_xss", "");
            byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
            String text = new String(data, StandardCharsets.UTF_8);

            if (m_flag.getText().toString().equals(text)) {
            float user_score_xss = 6.25F;

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("ctf_score_xss", user_score_xss);
            editor.apply();
            Intent ctf_captured = new Intent(WebViewXSSActivity.this, FlagCaptured.class);
                String intent_xss_str = Float.toString(user_score_xss);
                ctf_captured.putExtra("intent_str", intent_xss_str);
            startActivity(ctf_captured);
        } else {
            Toast.makeText(WebViewXSSActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

    }

}