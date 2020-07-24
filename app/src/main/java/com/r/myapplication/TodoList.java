package com.r.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoList extends AppCompatActivity {
    ArrayList<task> arrayOfTasks = new ArrayList<>();
    SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        db = new SQLiteHelper(this);
        FillList();


    }

    public void FillList(){
        arrayOfTasks = db.listTasks();
        // Create the adapter to convert the array to views
        TasksAdapter adapter = new TasksAdapter(this, arrayOfTasks);
        // Attach the adapter to a GridView
        GridView gridView = findViewById(R.id.simpleGridView);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                PopupMenu popupMenu = new PopupMenu(TodoList.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.delete_item:

                                String title;

                                title = arrayOfTasks.get(i).getTask_desc();

                                db.removeSingleContact(title);

                                Toast.makeText(TodoList.this, "Task Deleted!" , Toast.LENGTH_SHORT).show();

                                arrayOfTasks = db.listTasks();

                                finish();
                                startActivity(getIntent());
                                return true;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }


    public void onBackPressed()
    {
        //do whatever you want the 'Back' button to do
        //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
        this.startActivity(new Intent(TodoList.this,MainActivity.class));

        return;
    }
}
