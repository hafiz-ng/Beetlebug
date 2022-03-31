package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.sql.Connection;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RootDetectorActivity extends AppCompatActivity  {


    TextView result;
    ImageView img;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslpinning_by_pass);
        Button m_btn2 = (Button) findViewById(R.id.button2);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        result = findViewById(R.id.rootStatus);
        img = findViewById(R.id.rootStatusImg);
        EditText rslt = findViewById(R.id.flag);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        m_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ctf_score_root = 5;
                String str_result = rslt.getText().toString();
                if (str_result.equals("0x5411n54")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ctf_score_root", ctf_score_root);
                    editor.commit();
                    Intent i = new Intent(RootDetectorActivity.this, FlagCaptured.class);
                    i.putExtra("ctf_score_root", ctf_score_root);
                    startActivity(i);
                }
            }
        });
    }

    private boolean doesSUexist() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/bin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }

    }

    private boolean doesSuperuserApkExist(String s) {

        File rootFile = new File("/system/app/Superuser.apk");
        Boolean doesexist = rootFile.exists();
        if(doesexist == true)
        {
            return(true);
        }
        else
        {
            return(false);
        }
    }

    public void showRootStatus(View view) {

        EditText m_flg = (EditText) findViewById(R.id.flag);

        boolean isrooted = doesSuperuserApkExist("/system/app/Superuser.apk")||
                doesSUexist();
        if(isrooted)
        {
            result.setText("Your Device is Rooted!");
            Drawable res = getResources().getDrawable(R.drawable.root);
            img.setImageDrawable(res);
            m_flg.setText("0x5411n54");
        }
        else
        {
            result.setText("Device not Rooted!");
            Drawable res = getResources().getDrawable(R.drawable.no_root);
            img.setImageDrawable(res);
            m_flg.setText("0x5411n54");

        }
    }





}