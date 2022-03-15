package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class InsecureLoggingActivity extends AppCompatActivity {

    Button m_submit_flag, m_pay;
    EditText m_expires, m_cvv, m_card_number, m_enter_flag;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_logging);
        m_pay = (Button) findViewById(R.id.buttonPay);
        m_submit_flag = (Button) findViewById(R.id.buttonSubmitFlag);
        m_card_number = (EditText) findViewById(R.id.editTextCardNumber);
        m_expires = (EditText) findViewById(R.id.editTextExpires);
        m_cvv = (EditText) findViewById(R.id.editTextCvv);
        m_enter_flag = (EditText) findViewById(R.id.editTextEnterFlag);

        m_submit_flag.setVisibility(View.GONE);
        m_enter_flag.setVisibility(View.GONE);


        m_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();

                m_submit_flag.setVisibility(View.VISIBLE);
                m_enter_flag.setVisibility(View.VISIBLE);


                if (isAllFieldsChecked) {
                    String card = m_card_number.getText().toString();
                    m_card_number.setError("An Error Occurred");
                    Log.e("beetle-log", "Transaction Failed: " + card + "\n" + "flg_08");
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


    public void captureFlag(View view) {
        String flag = m_enter_flag.getText().toString();
        if (flag.equals("flg_08")) {
            Toast.makeText(InsecureLoggingActivity.this, "flag captured", Toast.LENGTH_SHORT).show();
            Intent captured_intent = new Intent(InsecureLoggingActivity.this, FlagCaptured.class);
            startActivity(captured_intent);
        }
    }


    private boolean CheckAllFields(){
        if (m_card_number.length() == 0) {
            m_card_number.setError("This field is required");
            return false;
        }

        if (m_expires.length() == 0) {
            m_expires.setError("This field is required");
            return false;
        }

        if (m_expires.length() == 0) {
            m_expires.setError("This field is required");
            return false;
        }

        if (m_cvv.length() == 0) {
            m_cvv.setError("This field is required");
            return false;
        }

        return true;
    }

}