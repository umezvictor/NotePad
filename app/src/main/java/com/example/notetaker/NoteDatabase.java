package com.example.notetaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//this is the database class
public class NoteDatabase extends SQLiteOpenHelper {

    //parameter to be passed to super constructor
    //current DB VERSION
    private static final int DATABASE_VERSION = 2;
    //DB NAME
    private static final String DATABASE_NAME = "NotesDB";
    //table name
    private static final String DATABASE_TABLE = "NotesTable";

    //column names for db tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";


    //constructor that matches super constructor -- i.e constructor of the inherited class
    NoteDatabase(Context context){
        //the context is important because this db class will be called from different classes
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this class is called whenever an instance of Notedatabase is created
        //this is where our db is created
        String query = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TITLE +
                " TEXT, " + KEY_CONTENT + " TEXT, " + KEY_DATE + " TEXT, "
                + KEY_TIME + " TEXT" + ")";
        //EXECUTE QUERY
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //this method is called if db version gets updated
        //check if we have current version
        if(oldVersion >= newVersion)
            return;
        //else, drop table and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //method to handle note insertion
    //this method will be used in addNote activity
    public  long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        //int version = db.getVersion();
        // Log.d("dbversion", "version" + version);
        //get values from Note class
        ContentValues c = new ContentValues();
        //creates a dictionary of key value pairs
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());

        //insert data
        //if successful, it will return long value as the primary key
        //returns -1 if error occurs
        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("message", "inserted: " + ID);

        return ID;
    }

    public Note getNote(long id){
        //get single note from db
        SQLiteDatabase db = this.getWritableDatabase();
        //cursor is a pointer that points to the current data in the table
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME}, KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        //return data

        //create new note and save data from the database to the Note
        //usee the contsructor that accepts id as param, because we are reading from the db
        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor
        .getString(3),cursor.getString(4));
        //ensure you reference all table columns starting from index 0
        //return note

        //return note
    }

    //return list of all notes
    public List<Note> getNotes(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Note> allNotes = new ArrayList<>();
        String query = "SELECT * FROM "+DATABASE_TABLE;

        Cursor cursor = db.rawQuery(query,null);
        //if record exist, pass to list view
        if(cursor.moveToFirst()){
            do{
                //create new note and save the data from the cursor
                Note note = new Note();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                allNotes.add(note);
            }while(cursor.moveToFirst());
        }
        return allNotes;
        //display all notes in adapter view
        //recycler view needs adapter
        //create new class that extends Recycler.MyAdapter
        //package-
    }
}
