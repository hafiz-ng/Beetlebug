package app.beetlebug.handlers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VulnerableReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String data = intent.getStringExtra("data");

        Toast.makeText(context, "Flag Found: " + data, Toast.LENGTH_LONG).show();
    }
}
