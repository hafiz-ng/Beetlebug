package ng.hafiz.databaseexample;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

public class MyLoader extends CursorLoader {

    DatabaseHelper myDatabaseHelper;

    public MyLoader(@NonNull Context context, DatabaseHelper db) {
        super(context);
        myDatabaseHelper = db;
    }

    public Cursor loadInBackground() {
        return myDatabaseHelper.getAllEmployees();

    }

}
