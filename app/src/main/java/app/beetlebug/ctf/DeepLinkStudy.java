package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import app.beetlebug.R;

public class DeepLinkStudy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_study);

        Uri uri = getIntent().getData();
        if (uri != null) {
            String path = uri.toString();
            Toast.makeText(DeepLinkStudy.this, "Deeplink =" + path, Toast.LENGTH_SHORT).show();
        }
    }
}