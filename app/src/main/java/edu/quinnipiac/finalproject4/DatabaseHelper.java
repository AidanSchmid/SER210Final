package edu.quinnipiac.finalproject4;

// Code mostly take from https://www.geeksforgeeks.org/how-to-pre-populate-database-in-android-using-sqlite-database/

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "assets";
    private static String DB_NAME = "countries.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;


    public static final String COUNTRIES = "countries";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order
     * to access the application assets and resources.
     * @param context*/
    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();
    }

    // Creates an empty database on the system, and rewrites it with your own database.
    public void createDataBase() throws IOException {

        if (checkDataBase()) {
            // do nothing - database already exists
        }
        else {
            // By calling this method, the empty database will be created into the default
            // system path of our application, but our database isn't empty to start, so we'll
            // overwrite that database with our database.
            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // Check if the database already exist, to avoid rebuilding it each time.
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {
            // database doesn't exist yet.
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your local assets folder to the just-created empty database in the
     * system folder, from where it can be accessed and handled. This is done by transferring bytestream.
     * */
    private void copyDataBase()
            throws IOException
    {
        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    // These two methods are required to be here to subclass SQLiteHelper. createDatabase() replaces onCreate()
    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // This method is used to get the requisite information from the database.
    public List<String> getSimilarItems(Context c, String name, int stat) {
        sqLiteOpenHelper = new DatabaseHelper(c);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        List<String> list = new LinkedList<>();

        String query = "SELECT * FROM " + COUNTRIES + " WHERE name IS " + name;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            if (stat == R.id.elevationbutton) { // User selected elevation, query again over elevation range.
                list.add("Countries with a similar elevation to the one you chose:");
                int elevation = cursor.getInt(4);
                String secondQuery = "SELECT * FROM " + COUNTRIES + " WHERE altitude BETWEEN "
                    + (elevation-200) + " AND " + (elevation+200);
                Cursor secondCursor = db.rawQuery(secondQuery, null);
                if (secondCursor.moveToFirst()) {
                    do {
                        list.add(secondCursor.getString(5) + ", with an average elevation of "
                            + secondCursor.getInt(4) + " ft");
                    } while (secondCursor.moveToNext());
                }
            } else if (stat == R.id.languagebutton) { // User selected language, query same language.
                list.add("Countries that speak the same language as the one you chose:");
                String lang = cursor.getString(2);
                String secondQuery = "SELECT * FROM " + COUNTRIES + " WHERE language = '" + lang + "'";
                Cursor secondCursor = db.rawQuery(secondQuery, null);
                if (secondCursor.moveToFirst()) {
                    do {
                        list.add(secondCursor.getString(5));
                    } while (secondCursor.moveToNext());
                }
            } else if (stat == R.id.temperaturebutton) {
                list.add("Countries with a similar temperature to the one you chose:");
                int temperature = cursor.getInt(3);
                String secondQuery = "SELECT * FROM " + COUNTRIES + " WHERE temperature BETWEEN "
                        + (temperature-5) + " AND " + (temperature+5);
                Cursor secondCursor = db.rawQuery(secondQuery, null);
                if (secondCursor.moveToFirst()) {
                    do {
                        list.add(secondCursor.getString(5) + ", with an average yearly " +
                            "temperature of " + secondCursor.getInt(3) + " degrees Celsius");
                    } while (secondCursor.moveToNext());
                }
            }
        } else list.add("No results found for that name.");
        db.close();
        return list;
    }
}