package app.beetlebug.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.beetlebug.MainActivity;
import app.beetlebug.R;

public class UserSignUp extends AppCompatActivity {
    EditText mName;
    Button mSignUpButton;
    public static final String MyPREFERENCES = "user";
    public static final String m_name = "name";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        Button user_button = (Button) findViewById(R.id.buttonSignUp);
//        EditText user_name = (EditText) findViewById(R.id.editTextName);
//        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

//        if(sharedPreferences.contains(m_name)) {
//            Intent i = new Intent(UserSignUp.this, MainActivity.class);
//            startActivity(i);
//        }

        user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proceed_intent = new Intent(UserSignUp.this, MainActivity.class);
                startActivity(proceed_intent);
            }
        });

//        user_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // put data in sharePreferences
//                String name_u = user_name.getText().toString();
//
//                Intent intent = new Intent(UserSignUp.this, MainActivity.class);
//                startActivity(intent);
//                if (name_u.isEmpty()) {
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(m_name, name_u);
//                    editor.apply();
//                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
//
//                    Intent proceed_intent = new Intent(UserSignUp.this, MainActivity.class);
//                    startActivity(proceed_intent);
//                    finish();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
//                    user_name.setText("");
//                }
//
//            }
//        });

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }
}