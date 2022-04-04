package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.beetlebug.R;

public class DeeplinkAccountActivity extends AppCompatActivity {

    TextView flag, msg;
    Button copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink_account);

        flag = findViewById(R.id.textViewFlag);
        copy = findViewById(R.id.copy);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        // initializing our variable
        msg = findViewById(R.id.textViewUrl);

        // getting the data from our
        // intent in our uri.
        Uri uri = getIntent().getData();

        // checking if the uri is null or not.
        if (uri != null) {
            // if the uri is not null then we are getting the
            // path segments and storing it in list.
            List<String> parameters = uri.getPathSegments();

            // after that we are extracting string from that parameters.
            String param = parameters.get(parameters.size() - 1);

            // on below line we are setting
            // that string to our text view
            // which we got as params.
            msg.setText(param);

            Toast.makeText(DeeplinkAccountActivity.this, "Fingerprint Auth Successful", Toast.LENGTH_LONG).show();


            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("TextView", flag.getText().toString());
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(DeeplinkAccountActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}