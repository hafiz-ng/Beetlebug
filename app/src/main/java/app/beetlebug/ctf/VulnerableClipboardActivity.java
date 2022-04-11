package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class VulnerableClipboardActivity extends AppCompatActivity {

    public Button m_pay;
    public EditText u_card, u_exp, u_cvv;

    SharedPreferences sharedPreferences, preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_clipboard);
        u_card = (EditText) findViewById(R.id.editTextCardNumber);
        u_exp = (EditText) findViewById(R.id.editTextExpires);
        u_cvv = (EditText) findViewById(R.id.editTextCvv);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        m_pay = findViewById(R.id.pay);

        m_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String card = u_card.getText().toString();
                String exp = u_exp.getText().toString();
                String cvv = u_cvv.getText().toString();

                if(card.isEmpty() && exp.isEmpty() && cvv.isEmpty()) {
                    u_card.setError("Enter card all details");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("card: " + card + "\n  ");
                    sb.append("expires: " + exp + "\n  ");
                    sb.append("cvv: " + cvv + "\n  ");
                    sb.append("flag: " + getString(R.string._1x33e91A));
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("TextView", sb);
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(VulnerableClipboardActivity.this, "Copied to clipboard" + sb, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void flg(View view) {
        EditText flg = findViewById(R.id.flag);
        String result = flg.getText().toString();
        String pref_result = preferences.getString("14_clip", "");
        byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);


        if (result.equals(text)) {
            float user_score_clip = 6.25F;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("ctf_score_clip", user_score_clip);
            editor.apply();

            Intent ctf_captured = new Intent(VulnerableClipboardActivity.this, FlagCaptured.class);
            String intent_clip_str = Float.toString(user_score_clip);
            ctf_captured.putExtra("intent_str", intent_clip_str);

            startActivity(ctf_captured);
        } else if (result.isEmpty()) {
            flg.setError("Try again");
        }
    }

}