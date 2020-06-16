package com.example.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    //in this activity, we can set title and read details of notes
    EditText noteTitle, noteDetails;

    //init calendar
    //save date and time when note is created
    Calendar mCalendar;
    String todaysDate;
    String currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //get toolbar
        toolbar = findViewById(R.id.toolbar2);

        //set background color for toolbar -- GET the color FROM colors.xml
       //GO TO colors.xml and add color of choice
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        //display "New Note" on actionbar when you click on the plus icon
        getSupportActionBar().setTitle("New Note");

        //enable back button
        //set in androidmanifest.xml
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get noteTile and noteDetails fields
        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);


        //next steps
        //connect noteactivity to the database so we can add and retrieve notes from db
        //for that we need data structure - a class called 'Note' to be able to pass data to the db

        //feature --
        //when user starts typing, it should appear in toolbar
        //call the addtextchangedlistener method
        //once you add this parameter new TextWatcher, it displays all the overide methods
        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get whatever user typed from noteTitle -- line 28
                //check if user made any input
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get month, year,date
        //get instance of calendar
        mCalendar = Calendar.getInstance();
        //get current year, month, time
        //Calendar months stop at 11, so use + 1 to Iincrement and get current month
        todaysDate = mCalendar.get(Calendar.YEAR)+"/"+(mCalendar.get(Calendar.MONTH)+1)+"/"+mCalendar.get(Calendar.DAY_OF_MONTH);
        //curren time, add '0' to numbers greater than 10
        currentTime = pad(mCalendar.get(Calendar.HOUR))+":"+pad(mCalendar.get(Calendar.MINUTE));

        //log
        Log.d("Calendar", "Date and time: " + todaysDate +"and " + currentTime);
    }

    //if number is less than 10, add a leading zero behind eg 09, 08
    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    //register the save menu
    //this will display the menu in the toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    //handle the onclick event of the save menu or delete menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "delete btn is clicked", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.save){
            //save note to db
            //create note object
            Note note = new Note(noteTitle.getText().toString(), noteDetails.getText().toString(), todaysDate, currentTime);
            //create object of Notedatabase class and call addnote method
            NoteDatabase db = new NoteDatabase(this);
            db.addNote(note);
            Toast.makeText(this, "save btn is clicked", Toast.LENGTH_SHORT).show();

            //go back to main activity after saving
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
