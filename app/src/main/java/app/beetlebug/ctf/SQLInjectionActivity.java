package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;

public class SQLInjectionActivity extends AppCompatActivity {

    private SQLiteDatabase mDB;

    EditText flg;
    Button btn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlinjection);
        flg = findViewById(R.id.flag);
        btn = findViewById(R.id.button);

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);

        try {
            mDB = openOrCreateDatabase("sqli", MODE_PRIVATE, null);
            mDB.execSQL("DROP TABLE IF EXISTS sqliuser;");
            mDB.execSQL("CREATE TABLE IF NOT EXISTS sqliuser(user VARCHAR, password VARCHAR, credit_card VARCHAR);");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('admin', 'passwd123', '1234567812345678');");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('user', 'flg_10', '1234567812345678');");

        }
        catch(Exception e) {
            Log.d("beetle-sqli", "Error occurred while creating database for SQLI: " + e.getMessage());
        }
        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_score = 9;
                String result = flg.getText().toString();
                if(result.equals("flg_10")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ctf_score_sqli", user_score);
                    editor.putBoolean("ctf_status", true);
                    editor.apply();
                    Intent ctf_captured = new Intent(SQLInjectionActivity.this, FlagCaptured.class);
                    startActivity(ctf_captured);
                }
            }
        });
    }

    public void search(View view) {
        EditText search_text = (EditText) findViewById(R.id.username);
        Cursor cr = null;
        try {
            cr = mDB.rawQuery("SELECT * FROM sqliuser WHERE user = '" + search_text.getText().toString() + "'", null);
            StringBuilder strb = new StringBuilder("");
            if ((cr != null) && (cr.getCount() > 0)) {
                cr.moveToFirst();
                do {
                    strb.append("User: (" + cr.getString(0) + ") pass: (" + cr.getString(1) + ") Credit card: (" + cr.getString(2) + ")\n");
                } while (cr.moveToNext());
            } else {

                strb.append("User: (" + search_text.getText().toString() + ") not found");
            }
            TextView text_sqli = findViewById(R.id.textViewSqliResult);
            text_sqli.setText(strb.toString());
//            Toast.makeText(this, strb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Beetle-sqli", "Error occurred while searching in database: " + e.getMessage());
        }

    }


}