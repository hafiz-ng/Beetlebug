package app.beetlebug.ctf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import app.beetlebug.handlers.VulnerableService;

public class VulnerableServiceActivity extends AppCompatActivity implements View.OnClickListener {


    private Button start, stop;
    SharedPreferences sharedPreferences;
    public static String ctf_score_service = "ctf_score_service";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable_service);

        // assigning ID of startButton
        // to the object start
        start = (Button) findViewById( R.id.buttonStart );

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        // assigning ID of stopButton
        // to the object stop
        stop = (Button) findViewById( R.id.buttonStop);

        // declaring listeners for the
        // buttons to make them respond
        // correctly according to the process
        start.setOnClickListener( this );
        stop.setOnClickListener( this );

        start.setVisibility(View.GONE);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }


    public void onClick(View view) {

        // process to be performed
        // if start button is clicked
        if(view == start){

            // starting the service
            startService(new Intent( this, VulnerableService.class ) );
        }

        // process to be performed
        // if stop button is clicked
        else if (view == stop){

            // stopping the service
            stopService(new Intent( this, VulnerableService.class ) );

        }
    }

    public void captureFlag(View view) {
        EditText m_flag = findViewById(R.id.flag);
        String rslt = m_flag.getText().toString();
        if (rslt.equals("0xe22210")) {
            int user_score_service = 9;
            String ctf_status = "service_ctf_status";

            // save user score to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ctf_score_service, user_score_service);
            editor.putBoolean(ctf_status, true);
            editor.commit();

            Intent ctf_captured = new Intent(VulnerableServiceActivity.this, FlagCaptured.class);
            startActivity(ctf_captured);
        } else {
            m_flag.setError("Wrong answer");
        }
    }



}