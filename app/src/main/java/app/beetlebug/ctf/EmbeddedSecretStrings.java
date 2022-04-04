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
    EditText pin;
    SharedPreferences sharedPreferences;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_sqlite = "ctf_score_sqlite";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_secret_strings);
        m_btn = findViewById(R.id.buttonUnlock);

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
                pin = findViewById(R.id.editTextSecretPin);

                String s1 = pin.getText().toString();
               if((s1.equals(getString(R.string.V98bFQrpGkDJ))) ) {
                   int ctf_score_secret_string = 5;
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putInt("ctf_score_secret_string", ctf_score_secret_string);
                   editor.apply();
                   Intent secret_intent = new Intent(EmbeddedSecretStrings.this, FlagCaptured.class);
                   secret_intent.putExtra("ctf_score_secret_string", ctf_score_secret_string);
                   startActivity(secret_intent);
               } else if (s1.isEmpty()) {
                    Toast.makeText(EmbeddedSecretStrings.this, "Try again.", Toast.LENGTH_SHORT).show();
                    pin.setError("Input your PIN");
               }
            }
        });
    }

}