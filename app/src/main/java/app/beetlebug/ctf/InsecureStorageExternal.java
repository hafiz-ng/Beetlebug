package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class InsecureStorageExternal extends AppCompatActivity {
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    EditText m_pass, m_userIrs;
    public static String sqli_pref = "sqli_pref";
    public static String m_score = "score";


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_storage_external);
        m_pass = findViewById(R.id.editTextPassword);
        m_userIrs = findViewById(R.id.editTextIRSId);

        sharedPreferences = getSharedPreferences(sqli_pref, Context.MODE_PRIVATE);

        boolean isAvailable= false;
        boolean isWritable= false;
        boolean isReadable= false;
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)) {
            // Operation possible - Read and Write
            isAvailable= true;
            isWritable= true;
            isReadable= true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Operation possible - Read Only
            isAvailable= true;
            isWritable= false;
            isReadable= true;
        } else {
            // SD card not available
            isAvailable = false;
            isWritable= false;
            isReadable= false;
        }


        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }

    public void captureFlag(View view) {
        Intent ctf_captured = new Intent(InsecureStorageExternal.this, FlagCaptured.class);
        startActivity(ctf_captured);
    }

    public void saveCreds(View view) {
        // Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        StringBuilder sb = new StringBuilder();
        sb.append(m_pass.getText().toString());
        sb.append(m_userIrs.getText().toString());
        String data = sb.toString();


        // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
//        File folder = Environment.getExternalStorageDirectory();

        // Storing the data in file with name as UserIdData.txt
        File file = new File(folder, "irs_data.txt");
        writeTextData(file, data);

        Toast.makeText(this, "Data saved to" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        int my_score = 50;

        // write to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(m_score, my_score);
        editor.commit();


    }

    // writeTextData() method save the data into the file in byte format
    // It also toast a message "Done/filepath_where_the_file_is_saved"
    private void writeTextData(File file, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}