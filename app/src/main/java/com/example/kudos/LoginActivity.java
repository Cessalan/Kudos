package com.example.kudos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudos.data.DatabaseHandler;

public class LoginActivity extends AppCompatActivity {
    private Button register_btn;
    private Button login_btn;
    private EditText usernameTV;
    private EditText passwordTV;
    private String username;
    private String password;

    public void showToast(String txt){

        Toast toast=Toast.makeText(this,txt,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instantiate
        login_btn=findViewById(R.id.login_btn);
        register_btn=findViewById(R.id.l_create_btn);
        usernameTV=findViewById(R.id.l_username);
        passwordTV=findViewById(R.id.l_password);



        //trigger login function when login btn is clicked
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=usernameTV.getText().toString().trim();
                password=passwordTV.getText().toString().trim();
                loginProcess(username,password);

            }
        });

        //go to register page when register btn is clicked
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int REQ_CODE=2;
                Intent intent= new Intent(v.getContext(),RegisterActivity.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }



    public void loginProcess(String uname, String pwd){
        DatabaseHandler db= new DatabaseHandler(getApplicationContext());
        if(uname.equals("")||pwd.equals("")){
            Toast.makeText(this,"Please fill out the login form.",Toast.LENGTH_LONG).show();

        }else{

            if(db.getAccount(uname)==null){

                showToast("This account does not exist");
            }else{

                if(db.getAccount(uname).getUsername().equals(uname) && db.getAccount(uname).getPassword().equals(pwd)){

                    //let the user know he/she is logged in
                    showToast("You are logged in");

                    SharedPreferences preferences=getSharedPreferences("status",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putInt("status",1);
                    editor.apply();


                    //display logged in user in the navigation fragment
//                    Bundle bundle= new Bundle();
//                    bundle.putString("userId",uname);
//
//                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//                    navigationFragment menuFragment= new navigationFragment();
//                    menuFragment.setArguments(bundle);
//
//                    ft.replace(R.id.userId,menuFragment);
//                    ft.commit();




                }else{
                    //display error message
                   showToast("Wrong credentials");

                }

            }

        }

    }

}
