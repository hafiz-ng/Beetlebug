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


        String location = intent.getStringExtra("location");
        String data = intent.getStringExtra("data");

        Toast.makeText(context, "data: " + data, Toast.LENGTH_LONG).show();
        Log.i("beetle", "Location = " + location);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US);

        try {
            FileOutputStream fos = new FileOutputStream(location, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(dateFormat.format(new Date()) + ": " + data + "<br>");
            pw.flush();
            pw.close();
            fos.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
            Log.i("beetle", "LOG FILE NOT FOUND (is WRITE_EXTERNAL_STORAGE permission present in the manifest?)");
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
