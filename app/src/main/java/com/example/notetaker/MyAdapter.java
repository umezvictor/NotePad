package com.example.notetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//we need a custom layout for this adapter - create that in the layout file
//call it custom_list_view. it will contain a card view that will be used to render all our data

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //INIT variables
    LayoutInflater inflater;
    List<Note> notes;

    //we need a constructoir for this adapter so data can be passed
    // from the acticity to the adapter and displayed in the rv

    MyAdapter(Context context, List<Note> notes){
        //set inflater
        this.inflater = LayoutInflater.from(context);
        //set notes
        this.notes = notes;//this will save the data
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       //this created \view will use our custom view to inflate our list of notes
        //this is how we bind customlistviw to recyclerview
        View view = inflater.inflate(R.layout.custom_list_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int i) {
        //i represents position
    //bind the Textview with the data retrieved from the databse
       String title = notes.get(i).getTitle();
       String date = notes.get(i).getDate();
       String time = notes.get(i).getTime();
       //bind to textview using viewholder

        holder.nTitle.setText(title);
        holder.nTime.setText(time);
        holder.nDate.setText(date);
        //lastly, go to main activity and save the adapter to the recycler view
    }

    @Override
    public int getItemCount() {
        //return count of notes
        return notes.size();
    }

    //create MyViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder{

        //start bindding data here, i.e
        //data will be retrieved from notes
        //set/save the data to our title , date and time fields using MyViewHolder

        //create a variable of type TextView
        TextView nTitle, nDate, nTime;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            //set resources for this TextView
            nTitle = itemView.findViewById(R.id.noteTitle);
            nDate = itemView.findViewById(R.id.dateTextView);
            nTime = itemView.findViewById(R.id.timeTextView);
            //next -set the above in the onbindviewholder
        }
    }
}