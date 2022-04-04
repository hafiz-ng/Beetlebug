package app.beetlebug.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

import app.beetlebug.db.AdminHelper;

public class MyLoader extends CursorLoader {

    AdminHelper myDatabaseHelper;

    public MyLoader(@NonNull Context context, AdminHelper db) {
        super(context);
        myDatabaseHelper = db;
    }

    public Cursor loadInBackground() {
        return myDatabaseHelper.getAllEmployees();

    }

}
