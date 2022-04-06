package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import app.beetlebug.handlers.VulnerableContentProvider;

public class InsecureContentProvider extends AppCompatActivity {

    SharedPreferences sharedPreferences, preferences;

    Uri CONTENT_URI = Uri.parse("content://app.beetlebug.provider/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_content_provider);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

    }

    public void loadData(View view) {

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(Uri.parse("content://app.beetlebug.provider/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
        }
    }


    public void insertData(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put(VulnerableContentProvider.name, ((EditText) findViewById(R.id.username)).getText().toString() + " - flg 0x733421M");

        // inserting into database through content URI
        getContentResolver().insert(VulnerableContentProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }

    public void flg(View view) {
        EditText flg = findViewById(R.id.flag);
        String result = flg.getText().toString();
        String pref_result = preferences.getString("7_content", "");
        byte[] data = Base64.decode(pref_result, Base64.DEFAULT);
        String text = new String(data, StandardCharsets.UTF_8);

        if (result.equals(text)) {
            int user_score_content = 5;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_content_provider", user_score_content);
            editor.commit();
            Intent ctf_captured = new Intent(InsecureContentProvider.this, FlagCaptured.class);
            ctf_captured.putExtra("ctf_score_content_provider", user_score_content);
            startActivity(ctf_captured);

        }
        else if (result.isEmpty()) {
            flg.setError("Try again");
        }
    }
}