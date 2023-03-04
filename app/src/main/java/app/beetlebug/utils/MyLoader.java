package app.beetlebug.utils;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import app.beetlebug.db.DatabaseHelper;

public class MyLoader extends CursorLoader {

    DatabaseHelper myDatabaseHelper;

    public MyLoader(@NonNull Context context, DatabaseHelper db) {
        super(context);
        myDatabaseHelper = db;
    }

//    public Cursor loadInBackground() {
//        return myDatabaseHelper.getAllEmployees();
//
//    }

}
