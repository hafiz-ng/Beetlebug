package app.beetlebug.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SPF extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;


    public void base64encodeData() {
        sharedPreferences = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("result-one", "NzQzMjU4MA==");
    }
}


