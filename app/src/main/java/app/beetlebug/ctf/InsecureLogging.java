package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class InsecureLogging extends AppCompatActivity {

    Button mSubmit;
    EditText mSubmitEditText, mCardNumber, mExpires, mCvv;
    TextView mFlagTextView;
    ImageView mArrowLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_logging);
        mSubmit = (Button) findViewById(R.id.buttonCaptureFlag);
        mCardNumber = (EditText) findViewById(R.id.cardNumber);
        mExpires = (EditText) findViewById(R.id.expires);
        mSubmitEditText = (EditText) findViewById(R.id.editTextFlag);
        mCvv = (EditText) findViewById(R.id.cvv);
        mArrowLeft = (ImageView) findViewById(R.id.arrowLeft);

        mSubmit.setVisibility(View.GONE);
        mSubmitEditText.setVisibility(View.GONE);

        mArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }

    public void payButton(View view) {
        Log.i("card_log", "Please try again");
        Snackbar.make(InsecureLogging.this, view, "Card Added", Snackbar.LENGTH_LONG).show();


        String expires = mExpires.getText().toString();
        String cvv = mCvv.getText().toString();
        String card_number = mCardNumber.getText().toString();

        if (expires.equals("") && cvv.equals("") && card_number.equals("")) {
            Snackbar.make(InsecureLogging.this, view, "Please complete the fields", Snackbar.LENGTH_LONG).show();

        }
        mSubmit.setVisibility(View.VISIBLE);
        mSubmitEditText.setVisibility(View.VISIBLE);
    }

    public void captureFlag(View view) {
        Intent captured_intent = new Intent(InsecureLogging.this, FlagCaptured.class);
        startActivity(captured_intent);
    }
}