package com.example.kudos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kudos.data.DatabaseHandler;
import com.example.kudos.model.Account;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText pwd;
    private EditText pwdConfirm;
    private Button  register;
    private Button goBackToLogin;
    private String strUsername;
    private String strPwd;
    private  String strPwdConf;


    //custom toast
    public void showToast(String txt){

        Toast toast=Toast.makeText(this,txt,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.create_btn);
        goBackToLogin=findViewById(R.id.goto_login);





        //two way intent, brings back to login when clicked
        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationProcess();
            }
        });


    }



    public void registrationProcess(){
        DatabaseHandler db= new DatabaseHandler(this);
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        pwdConfirm = findViewById(R.id.confirm_password);

        //get txt from TextView
        strUsername = username.getText().toString();
        strPwd = pwd.getText().toString();
        strPwdConf = pwdConfirm.getText().toString();

        //validation
        if(strUsername.equals("")||strPwd.equals("")||strPwdConf.equals("")){
            Toast.makeText(this,"Please pick an username.",Toast.LENGTH_LONG).show();
        }else{

            if(strPwd.equals(strPwdConf)){

                if(db.getAccount(strUsername)==null){
                    //if all validations passed, add account to db
                    Account newAccount = new Account();
                    newAccount.setUsername(username.getText().toString());
                    newAccount.setPassword(pwd.getText().toString());
                    db.addAccount(newAccount);
                    db.close();


                    showToast("Your account  "+ strUsername+" has been created");



                }else{

                    showToast("Please pick a different username");

                }


            }else{

                showToast("The passwords do not match");
            }

        }

        }






    }




