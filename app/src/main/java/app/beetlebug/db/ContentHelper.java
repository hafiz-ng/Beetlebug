package app.beetlebug.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ContentHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "beetleDB";
    private static final String TABLE_DATA = "data";
    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String[] COLUMNS = {
            KEY_ID,
            KEY_TITLE,
            KEY_AUTHOR
    };


    public ContentHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.initDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older data table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // create fresh data table
        this.onCreate(db);
    }



    /**
     * Initialize DB
     * @param db
     */
    public void initDatabase(SQLiteDatabase db) {
        try {
            // SQL statement to create data table
            String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + " ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "author TEXT )";

            // create records table
            db.execSQL(CREATE_DATA_TABLE);
        } catch(Exception e) {

        }
    }

    public void initDatabaseOuter() {
        SQLiteDatabase db = this.getReadableDatabase();
        this.initDatabase(db);
    }

    /**
     * Add record to DB
     * @param record
     */
    public void addRecord(DatabaseRecord record) {
        try {
            Log.d("beetlebug", record.toString());

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, record.getTitle()); // get title
            values.put(KEY_AUTHOR, record.getAuthor()); // get author

            db.insert(TABLE_DATA, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/ values = column values

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Getter of record from DB
     * @param id
     * @return
     */
    public DatabaseRecord getRecord(int id) {
        DatabaseRecord record = new DatabaseRecord();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from data where id='" + String.valueOf(id) + '"', null);

            if (cursor != null) cursor.moveToFirst();

            record.setId(Integer.parseInt(cursor.getString(0)));
            record.setTitle(cursor.getString(1));
            record.setAuthor(cursor.getString(2));

            Log.d("beetlebug","getRecord(" + id + "): " + record.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }


    /**
     * Get all records from DB
     * @return
     */
    public ArrayList<DatabaseRecord> getAllRecords() {
        ArrayList<DatabaseRecord> records = new ArrayList<DatabaseRecord>();

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DATA, null);

            DatabaseRecord record = null;
            if (cursor.moveToFirst()) {
                do {
                    record = new DatabaseRecord();
                    record.setId(Integer.parseInt(cursor.getString(0)));
                    record.setTitle(cursor.getString(1));
                    record.setAuthor(cursor.getString(2));

                    // Add record to records
                    records.add(record);
                } while (cursor.moveToNext());
            }

            Log.d("beetlebug", "getAllRecords(): " + records.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;

    }


    /**
     * Update record in DB
     * @param record
     * @return
     */
    public int updateRecord(DatabaseRecord record) {
        int i = -1;

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("title", record.getTitle());
            values.put("author", record.getAuthor());

            i = db.update(TABLE_DATA, values, KEY_ID + " = ?", new String[]{
                    String.valueOf(record.getId())
            });

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * Delete record from DB
     * @param record
     */
    public void deleteRecord(DatabaseRecord record) {
        try {
            Log.d("beetlebug", "deleteRecord: " + record.toString());

            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_DATA, KEY_ID + " = ?", new String[]{
                    String.valueOf(record.getId())
            });

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Raw SQL Query
     * @param query
     */
    public String rawSQLQuery(String query) {

        StringBuilder sb = new StringBuilder();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Log.d("beetlebug", "rawSQLQuery: " + query);

            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String e = DatabaseUtils.dumpCurrentRowToString(cursor);

                Log.d("beetlebug", e);
                sb.append(e).append("\n");

                cursor.moveToNext();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        db.close();

        return sb.toString();
    }

    /**
     * Raw SQL Query
     * @param query
     */
    public Cursor rawSQLQueryCursor(String query) {


        StringBuilder sb = new StringBuilder();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Log.d("beetlebug", "rawSQLQueryCursor: " + query);

            Cursor cursor = db.rawQuery(query, null);
            return cursor;


        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
