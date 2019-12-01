package com.example.kudos;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kudos.adapter.RecyclerViewAdapter;
import com.example.kudos.data.DatabaseHandler;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate options menu
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //exit app if item is clicked
            case R.id.exit_app:
                try {
                    SECONDS.sleep(2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             System.exit(0);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db= new DatabaseHandler(this);

        //get all the posts from the DB
       final List POSTS=db.getAllPosts();

       //display message if database is empty
       if(POSTS.size()<1){
        Dialog dialog= new Dialog();
        dialog.setTxt("Nobody has made a post yet");
        dialog.show(getSupportFragmentManager(),"go");
       }
       //arrange RecyclerView
       recyclerView=findViewById(R.id.recyclerV);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       recyclerViewAdapter= new RecyclerViewAdapter(this, POSTS);
       recyclerView.setAdapter(recyclerViewAdapter);

    }
}
