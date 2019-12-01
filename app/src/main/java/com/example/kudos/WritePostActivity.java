package com.example.kudos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudos.data.DatabaseHandler;
import com.example.kudos.model.Status;
import com.example.kudos.model.UserPost;
import com.google.gson.Gson;

public class WritePostActivity extends AppCompatActivity {
    private EditText postET;
    private Button trigger;
    private  Bundle bundle;
    private  UserPost up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        trigger=findViewById(R.id.post_btn);
        postET=findViewById(R.id.userPost);

        bundle= getIntent().getExtras();
        if(bundle!=null){
            //retrieve object from intent
            Gson gson= new Gson();
            up=gson.fromJson(bundle.getString("thePost"),UserPost.class);

            //Toast.makeText(this,up.getPost(),Toast.LENGTH_LONG).show();

            //put old post in a TextView and let the user update
            postET.setText(up.getPost());
        }


            trigger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Status status= new Status();
                    Activity activity=WritePostActivity.this;

                    //user has to be logged in to perform any task

                    if(status.userLoggedIn(activity)){

                        //if an intent has been passed from the RecyclerViewAdapter
                        //it means its an update
                        //else its a create
                        if(bundle!=null){

                            //take updated post from user
                            String newPost=postET.getText().toString();
                            up.setPost(newPost);

                            //update the Database
                            DatabaseHandler db= new DatabaseHandler(v.getContext());
                            db.updatePost(up);

                            db.close();

                            //redirect user to the home page
                            Intent intent= new Intent(v.getContext(),MainActivity.class);
                            startActivity(intent);


                        }else{

                            //get txt from TextView and put it in the object
                            postET=findViewById(R.id.userPost);
                            String txt=postET.getText().toString();

                            if(txt.length()>0){

                                DatabaseHandler db= new DatabaseHandler(v.getContext());
                                UserPost newPost= new UserPost();

                                newPost.setPost(txt);

                                //add new UserPost obj to db
                                db.addPost(newPost);
                                db.close();

                                //redirect user to the home page
                                Intent intent= new Intent(v.getContext(),MainActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(v.getContext(),"Please write a post",Toast.LENGTH_LONG).show();
                            }

                        }

                    }else{

                        Toast.makeText(v.getContext(),"You Need To Login",Toast.LENGTH_LONG).show();

                    }

                }
            });


        }

    }

