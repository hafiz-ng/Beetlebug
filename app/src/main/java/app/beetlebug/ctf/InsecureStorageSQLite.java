package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import app.beetlebug.db.DatabaseHelper;

public class InsecureStorageSQLite extends AppCompatActivity {


    private DatabaseHelper myHelper;
    EditText name, pass;
    Button btn;
    SharedPreferences sharedPreferences, preferences;
    public static String flag_scores = "flag_scores";
    public static String m_name = "name";
    public static String m_password = "password";

    LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_storage_sql);


        lin = (LinearLayout) findViewById(R.id.flagLayout);

        btn = findViewById(R.id.button);

        lin.setVisibility(View.GONE);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        myHelper = new DatabaseHelper(this);
        myHelper.open();

    }

    public void captureFlag(View view) {
        float user_score = 6.25F;
        EditText flg = findViewById(R.id.flag);
        String result = flg.getText().toString();

        String pref_result = preferences.getString("5_sqlite", "");
        byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);

        if (result.equals(text)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("ctf_score_sqlite", user_score);
            editor.apply();

            Intent ctf_captured = new Intent(InsecureStorageSQLite.this, FlagCaptured.class);
            String intent_sqlite_str = Float.toString(user_score);
            ctf_captured.putExtra("intent_str", intent_sqlite_str);
            startActivity(ctf_captured);

        } else {
            flg.setError("Try again");
        }
    }

    public void login(View view) {
        name = (EditText) findViewById(R.id.editTextUsername);
        pass = (EditText) findViewById(R.id.editTextPassword);

        lin.setVisibility(View.VISIBLE);

        String flg = getString(R.string.sqlite_string);
        String ps = pass.getText().toString();

        if (isValidPassword(ps)) {
            myHelper.add(ps, flg);
            Toast.makeText(InsecureStorageSQLite.this, "Password saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InsecureStorageSQLite.this, "Use complex alphanumeric password for Master Key", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}