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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class BinaryPatchActivity extends AppCompatActivity {

    LinearLayout lin;
    SharedPreferences sharedPreferences, preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_patch);
        Button m_btn = (Button) findViewById(R.id.button);

        lin = findViewById(R.id.flagLinearLayout);

        lin.setVisibility(View.GONE);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ctf_score_patch = 5;
                EditText flag = findViewById(R.id.flag);
                String result = flag.getText().toString();
                String pref_result = preferences.getString("16_patch", "");
                byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
                String text = new String(data, StandardCharsets.UTF_8);

                if (result.equals(text)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ctf_score_patch", ctf_score_patch);
                    editor.apply();
                    Intent i = new Intent(BinaryPatchActivity.this, FlagCaptured.class);
                    i.putExtra("ctf_score_patch", ctf_score_patch);
                    startActivity(i);
                }
            }
        });
    }

    public void grantAccess(View view) {
        lin.setVisibility(View.VISIBLE);
        Toast.makeText(BinaryPatchActivity.this, "Flag Found!", Toast.LENGTH_LONG).show();
    }
}