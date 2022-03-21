package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.R;

public class InsecureDeepLinkActivity extends AppCompatActivity {

    EditText m_deeplink;
    Button m_goto_uri;
    TextView m_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_deep_link);

        m_deeplink = findViewById(R.id.editTextURI);
        m_goto_uri = findViewById(R.id.buttonGoToUri);

        m_goto_uri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = m_deeplink.getText().toString();

                if (m_deeplink.length() == 0) {
                    m_deeplink.setError("Enter Deeplink URI");
                    return;
                }

                Intent i = new Intent("android.intent.action.VIEW");
                i.setData(Uri.parse(data));
                try {
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }
}