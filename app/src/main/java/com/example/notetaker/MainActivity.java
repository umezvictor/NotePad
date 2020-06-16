package com.example.notetaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<Note> notes;

    //the main activity class is the first class that shows when the user launches the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get toolbar
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //get notes from db
        NoteDatabase db = new NoteDatabase(this);
        notes = db.getNotes();//returns all notes
        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, notes);

        recyclerView.setAdapter(adapter);//we can display list of notes in listview

    }

    //..to display options menu in main activity, it needs to be registered
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true; //this allows the plus button at the top to show
    }

    //override other methods to handle onclick events

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item id of whatever menu has been clicked
        //the id for the menu here is 'add'
        //this refers to the + icon
        if(item.getItemId() == R.id.add){
            //start AddNote activity when user clicks on + icon -- i.e menu
            Intent i = new Intent(this, AddNote.class);
            startActivity(i);//redirects user to AddNote activity
            //displays a message at the bottom that fades away
            Toast.makeText(this, "add btn is clicked", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
