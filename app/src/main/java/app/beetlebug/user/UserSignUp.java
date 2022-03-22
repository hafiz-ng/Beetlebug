package app.beetlebug.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import app.beetlebug.FlagCaptured;
import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.ctf.InsecureLoggingActivity;

public class UserSignUp extends AppCompatActivity {
    EditText mName;
    Button mSignUpButton;
    public static final String MyPREFERENCES = "user";
    public static final String m_name = "name";
    boolean isLoggedIn = false;

    EditText m_username, m_password;
    Button m_signup;
    boolean isAllFieldsChecked = false;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        m_username = findViewById(R.id.editTextUsername);
//        m_password = findViewById(R.id.editTextPassword);

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);

        isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            Intent i = new Intent(UserSignUp.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }

    public void buttonSignUp(View view) {

        String username = m_username.getText().toString();
//        String pass = m_password.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", username);
//        editor.putString("password", encrypt(pass));
        editor.putBoolean("is_logged_in", true);
        editor.apply();

        isAllFieldsChecked = CheckAllFields();

        if (isAllFieldsChecked) {
            Intent i = new Intent(UserSignUp.this, MainActivity.class);
            startActivity(i);
        }
    }

    public boolean CheckAllFields() {
        if (m_username.length() == 0) {
            m_username.setError("This field is required");
            return false;
        }

//        if (m_password.length() == 0) {
//            m_password.setError("This field is required");
//            return false;
//        }
        return true;

    }

    public static String encrypt(String input) {
        // Simple encryption, not very strong!
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }
}