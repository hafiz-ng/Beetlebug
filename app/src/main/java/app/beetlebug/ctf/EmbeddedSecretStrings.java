package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class EmbeddedSecretStrings extends AppCompatActivity {

    Button m_btn;
    EditText m_secret_pin;
    SharedPreferences sharedPreferences;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_sqlite = "ctf_score_sqlite";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_secret_strings);
        m_btn = findViewById(R.id.buttonUnlock);
        m_secret_pin = findViewById(R.id.editTextSecretPin);

        String s1 = m_secret_pin.getText().toString();

        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if((s1.equals("1234")) ) {
                   Intent secret_intent = new Intent(EmbeddedSecretStrings.this, FlagCaptured.class);
                   startActivity(secret_intent);
               } else if (s1.isEmpty()) {
                    Toast.makeText(EmbeddedSecretStrings.this, "Try again.", Toast.LENGTH_SHORT).show();
                    m_secret_pin.setError("Input your PIN");
               }
            }
        });
    }


    public void grantUserAccess(View view) {
        int secret = R.string.vendor_secret_key;
//        String result = m_vendor_key.getText().toString();
//        int ctf_score_secret_string = 9;
//        if (result.equals("vendor@khakiv")) {
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("ctf_score_secret_string", ctf_score_secret_string);
//            editor.apply();
//
//            Toast.makeText(EmbeddedSecretStrings.this, "Vendor match found", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(EmbeddedSecretStrings.this, FlagCaptured.class);
//            startActivity(i);
//        }
//

    }

    public void captureFlag(View view) {
    }
}