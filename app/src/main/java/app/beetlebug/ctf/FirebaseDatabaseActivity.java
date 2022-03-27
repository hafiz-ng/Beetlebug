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

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    public Button mBtn;
    EditText m_flg;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        mBtn = findViewById(R.id.button);
        m_flg = findViewById(R.id.flag);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = m_flg.getText().toString();
                if(result.isEmpty()) {
                    m_flg.setError("Enter flag");
                } else if (result.equals("firebase374fc")){
                    int user_score_firebase = 9;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ctf_score_firebase", user_score_firebase);
                    editor.putBoolean("firebase_status", true);
                    editor.commit();
                    Intent ctf_captured = new Intent(FirebaseDatabaseActivity.this, FlagCaptured.class);
                    startActivity(ctf_captured);
                    Toast.makeText(FirebaseDatabaseActivity.this, "Flag found", Toast.LENGTH_LONG).show();
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