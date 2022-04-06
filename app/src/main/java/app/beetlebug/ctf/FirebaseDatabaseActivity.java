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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    public Button mBtn;
    EditText m_flg;
    SharedPreferences sharedPreferences, preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        mBtn = findViewById(R.id.button);
        m_flg = findViewById(R.id.flag);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText flg = findViewById(R.id.flag);
                String result = flg.getText().toString();
                String pref_result = preferences.getString("11_firebase", "");
                byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
                String text = new String(data, StandardCharsets.UTF_8);

                if (result.equals(text)) {
                    int user_score_firebase = 5;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ctf_score_firebase", user_score_firebase);
                    editor.apply();

                    Intent ctf_captured = new Intent(FirebaseDatabaseActivity.this, FlagCaptured.class);
                    ctf_captured.putExtra("ctf_score_firebase", user_score_firebase);
                    startActivity(ctf_captured);
                } else {
                    m_flg.setError("Wrong answer");
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