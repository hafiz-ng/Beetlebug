package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class VulnerableClipboardActivity extends AppCompatActivity {

    public Button m_pay;
    public EditText u_card, u_exp, u_cvv;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_clipboard);
        u_card = (EditText) findViewById(R.id.editTextCardNumber);
        u_exp = (EditText) findViewById(R.id.editTextExpires);
        u_cvv = (EditText) findViewById(R.id.editTextCvv);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_pay = findViewById(R.id.pay);

        m_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String card = u_card.getText().toString();
                String exp = u_exp.getText().toString();
                String cvv = u_cvv.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("card: " + card + "\n  ");
                sb.append("expires: " + exp + "\n  ");
                sb.append("cvv: " + cvv + "\n  ");
                sb.append("flag: " + "0x11352c4");


                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", sb);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(VulnerableClipboardActivity.this, "Copied to clipboard" + sb, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void flg(View view) {
        EditText flg = findViewById(R.id.flag);
        String rslt = flg.getText().toString();
        if (rslt.isEmpty()) {
            flg.setError("Enter flag");
        } else if (rslt.equals("0x1132c4")) {
            int user_score_clip = 5;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_clip", user_score_clip);
            editor.commit();
            Intent ctf_captured = new Intent(VulnerableClipboardActivity.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_clip", user_score_clip);
            startActivity(ctf_captured);
        }
    }

}