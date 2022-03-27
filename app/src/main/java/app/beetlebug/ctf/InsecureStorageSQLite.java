package app.beetlebug.ctf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import app.beetlebug.FlagCaptured;
import app.beetlebug.R;
import app.beetlebug.db.CustomerModel;
import app.beetlebug.db.DatabaseHelper;

public class InsecureStorageSQLite extends AppCompatActivity {


    EditText customer_name, customer_age;
    Button btn;
    SharedPreferences sharedPreferences, sharedPreferences_sqlite;
    public static String flag_scores = "flag_scores";
    public static String ctf_score_sqlite = "ctf_score_sqlite";
    public static String m_name = "name";
    public static String m_password = "password";

    LinearLayout lin;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecure_storage_sql);

        customer_name = (EditText) findViewById(R.id.editTextUsername);
        customer_age = (EditText) findViewById(R.id.editTextPassword);
        lin = (LinearLayout) findViewById(R.id.flagLayout);

        btn = findViewById(R.id.button);

        lin.setVisibility(View.GONE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;


                try {
                    String name = customer_name.getText().toString();
                    int age = Integer.parseInt(customer_age.getText().toString());

                    customerModel = new CustomerModel(-1, name, age, "0xe332c04");
//                    Toast.makeText(InsecureStorageSQLite.this, customerModel.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(InsecureStorageSQLite.this, "Error creating customer" + "\n" + e, Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, "");

                }

                lin.setVisibility(View.VISIBLE);
                DatabaseHelper databaseHelper = new DatabaseHelper(InsecureStorageSQLite.this);
                boolean success = databaseHelper.addOne(customerModel);
                Toast.makeText(InsecureStorageSQLite.this, "Success", Toast.LENGTH_LONG).show();

            }
        });


        sharedPreferences = getSharedPreferences(flag_scores, Context.MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

    }

    public void captureFlag(View view) {
        int user_score = 9;
        EditText flg = findViewById(R.id.flag);
        String result = flg.getText().toString();
        if (result.equals("0xe332c04")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("ctf_score_sqlite", user_score);
            editor.putBoolean("sqlite_status", true);
            editor.apply();

            Intent ctf_captured = new Intent(InsecureStorageSQLite.this, FlagCaptured.class);
            startActivity(ctf_captured);
        }
    }

}