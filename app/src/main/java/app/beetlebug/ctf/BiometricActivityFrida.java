package app.beetlebug.ctf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

import app.beetlebug.FlagCaptured;
import app.beetlebug.MainActivity;
import app.beetlebug.R;

public class BiometricActivityFrida extends AppCompatActivity {

    private TextView txt;
    private ImageView img;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.AuthenticationCallback authenticationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric_frida);

        img = findViewById(R.id.imageView);
        txt = findViewById(R.id.textView);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        // init auth callback
        authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                txt.setText("error");
                Log.e("finger_error", (String) errString);

            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                txt.setText("help");
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                txt.setText("success");
                super.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationFailed() {
                txt.setText("auth failed");
                super.onAuthenticationFailed();
            }
        };

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

//        FingerprintManager fingerprintManager = (FingerprintManager)
//                getApplication().getSystemService(Context.FINGERPRINT_SERVICE);
//        fingerprintManager.isHardwareDetected();
//
//        fingerprintManager.hasEnrolledFingerprints();



    }
}