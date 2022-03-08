package app.beetlebug;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class FingerPrintActivity extends AppCompatActivity {

    Button mBtn;
    TextView mText;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        mBtn = findViewById(R.id.button);
        mText = findViewById(R.id.textView);

        // init the values
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(FingerPrintActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                mText.setText("Error" + errorCode + " " + errorCode);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                mText.setText("Authentication successful");

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                mText.setText("Authentication failed");

            }
        });

        // setup title, description and auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login Using Fingerprint or Face")
                .setNegativeButtonText("Cancel")
                .build();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // show auth dialog
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }



}