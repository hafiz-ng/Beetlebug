package app.beetlebug.db;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // define constants that represent the table name columns in our database

    public static final String TABLE_NAME = "USER";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    // TODO: Create constant for flag

    // database info
    static final String DB_NAME = "user_info.db";

    // database version
    static final int DB_VERSION = 1;

    // instance of SQLiteDatabase used to perform CRUD operations
    private SQLiteDatabase database;

    // create table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + PASSWORD + " CHAR(50));";

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
        contentValues.put(PASSWORD, address);


        database.insert(TABLE_NAME, null, contentValues);
    }

    // get all employees
    public Cursor getAllEmployees() {

        // columns we want to retrieve from our table
        String[] projection = {
                _ID, NAME, PASSWORD
        };

        // create a cursor and call the query method on that object
        Cursor cursor = database.query(TABLE_NAME, projection, null, null, null, null, null);

        return cursor;

    }

    public int update(long _id, String name, String address) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(PASSWORD, address);

        // we have to make sure you update the correct record
        // so we pass in a selection criteria for the update method
        // the update method returns a count of how many records we are updating
        int count = database.update(TABLE_NAME, contentValues, this._ID + " = " + _id, null);
        return count;
    }

    public void delete(long _id) {
        // passing the delete method on the database instance
        // pass in the table table and the WHERE clause
        database.delete(TABLE_NAME, _ID + " = " + _id, null);
    }
}

