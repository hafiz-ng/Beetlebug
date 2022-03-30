package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.R;

public class VulnerableClipboardActivity extends AppCompatActivity {

    public Button m_pay;
    public EditText u_card, u_exp, u_cvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_clipboard);
        u_card = (EditText) findViewById(R.id.editTextCardNumber);
        u_exp = (EditText) findViewById(R.id.editTextExpires);
        u_cvv = (EditText) findViewById(R.id.editTextCvv);

        TextView txt = (TextView) findViewById(R.id.textResult);

        m_pay = findViewById(R.id.pay);

        m_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String card = u_card.getText().toString();
                String exp = u_exp.getText().toString();
                String cvv = u_cvv.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("card: " + card  + "\n");
                sb.append("expires: " + exp + "\n");
                sb.append("cvv: " + cvv + "\n");


                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", sb);
                clipboardManager.setPrimaryClip(clipData);
                
                Toast.makeText(VulnerableClipboardActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }
}