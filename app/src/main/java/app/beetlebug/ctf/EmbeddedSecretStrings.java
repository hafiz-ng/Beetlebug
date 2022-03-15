package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

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
    EditText m_vendor_key;
    SharedPreferences sharedPreferences;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_sqlite = "ctf_score_sqlite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embedded_secret_strings);
        m_btn = findViewById(R.id.button);
        m_vendor_key = findViewById(R.id.editTextVendorKey);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }

    public void grantUserAccess(View view) {
        int secret = R.string.vendor_secret_key;

        if (m_vendor_key.equals("vendor@khakiv")) {
            Toast.makeText(EmbeddedSecretStrings.this, "Vendor match found", Toast.LENGTH_LONG).show();
            Intent i = new Intent(EmbeddedSecretStrings.this, FlagCaptured.class);
            startActivity(i);
        }
    }
}