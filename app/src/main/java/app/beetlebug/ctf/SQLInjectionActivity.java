package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.beetlebug.R;

public class SQLInjectionActivity extends AppCompatActivity {

    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlinjection);

        try {
            mDB = openOrCreateDatabase("sqli", MODE_PRIVATE, null);
            mDB.execSQL("DROP TABLE IF EXISTS sqliuser;");
            mDB.execSQL("CREATE TABLE IF NOT EXISTS sqliuser(user VARCHAR, password VARCHAR, credit_card VARCHAR);");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('admin', 'passwd123', '1234567812345678');");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('diva', 'p@ssword', '1111222233334444');");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('john', 'password123', '5555666677778888');");
            mDB.execSQL("INSERT INTO sqliuser VALUES ('flag 10', 'flg_10', '');");

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
    }

    public void search(View view) {
        EditText search_text = (EditText) findViewById(R.id.editTextSearchUser);
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