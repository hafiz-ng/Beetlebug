package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import app.beetlebug.R;

public class EmulatorDetectionActviity extends AppCompatActivity {

    TextView result;
    ImageView img;
    Button emulatorStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emulator_detection_actviity);
        emulatorStat = findViewById(R.id.checkEmulatorStatus);
        result = findViewById(R.id.rootStatus);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }

    public void checkEmulatorStatus(View view) {
        Boolean isEmulator = checkIfDeviceIsEmulator();
        if(isEmulator==true)
        {
            result.setText("App is running on an Emulator!");
//            Drawable res = getResources().getDrawable(R.drawable.root);
        }
        else
        {
            Toast.makeText(EmulatorDetectionActviity.this, "App running on physical device", Toast.LENGTH_LONG).show();
        }
    }

    private Boolean checkIfDeviceIsEmulator() {
        if(Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT))
        {
            return true;
        }
        return false;
    }

}