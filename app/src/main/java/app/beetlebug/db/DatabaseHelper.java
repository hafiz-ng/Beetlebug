package app.beetlebug.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // table name
    public static final String TABLE_NAME = "users";

    // table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String PASS = "password";

    // database info
    static final String DB_NAME = "user.db";

    // database version
    static final int DB_VERSION = 1;

    // instance of SQLiteDatabase used to perform CRUD operations
    private SQLiteDatabase database;

    // create table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + PASS + " CHAR(50));";

    // create a constructor to initialize member variables
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates your table, you can create multiple tables
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this drops the existing table and re-creates it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // open database
    public void open() throws SQLException {
        // remember you have to call the getWritableDatabase before you perform any operation on the database
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(String name, String address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PASS, address);


        database.insert(TABLE_NAME, null, contentValues);
    }

    // get all employees
//    public Cursor getAllEmployees() {
//
//        // columns we want to retrieve from our table
//        String[] projection = {
//                _ID, NAME, PASS
//        };
//
//        // create a cursor and call the query method on that object
//        Cursor cursor = database.query(TABLE_NAME, projection, null, null, null, null, null);
//
//        return cursor;
//
//    }

//    public int update(long _id, String name, String address) {
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(NAME, name);
//        contentValues.put(PASS, address);
//
//        // we have to make sure you update the correct record
//        // so we pass in a selection criteria for the update method
//        // the update method returns a count of how many records we are updating
//        int count = database.update(TABLE_NAME, contentValues, this._ID + " = " + _id, null);
//        return count;
//    }

}
