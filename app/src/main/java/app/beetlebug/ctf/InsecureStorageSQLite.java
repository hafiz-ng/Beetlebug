package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import app.beetlebug.db.DatabaseHelper;

public class InsecureStorageSQLite extends AppCompatActivity {


    private DatabaseHelper db;
    EditText mUsername, mPassword;
    SharedPreferences sharedPreferences, sharedPreferences_sqlite;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_sqlite = "ctf_score_sqlite";
    public static String m_name = "name";
    public static String m_password = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_storage_sql);

        mUsername = (EditText) findViewById(R.id.editTextUsername);
        mPassword = (EditText) findViewById(R.id.editTextPassword);

        db = new DatabaseHelper(this);
        db.open();

        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }


    public void saveUser(View view) {
        String name = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (name.isEmpty()) {
            mUsername.setError("Enter your Username");
        } if (password.isEmpty()) {
            mPassword.setError("Enter your password");
        } else {
            int user_score_sqlite = 9;
            db.add(name, password);
            Toast.makeText(InsecureStorageSQLite.this, "Data saved successfully", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(ctf_score_sqlite, user_score_sqlite);
            editor.commit();
        }


    }

    public void captureFlag(View view) {
        Intent ctf_captured = new Intent(InsecureStorageSQLite.this, FlagCaptured.class);
        startActivity(ctf_captured);
    }
}