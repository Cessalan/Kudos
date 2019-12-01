package com.example.kudos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private String strUsername;
    private String strPwd;
    private  String strPwdConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.create_btn);

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


                    Toast.makeText(this,"Your Account "+db.getAccount(strUsername).getUsername()+" has successfully been created.",Toast.LENGTH_LONG).show();



                }else{
                    Toast.makeText(this,"Please pick a different username.",Toast.LENGTH_LONG).show();

                }


            }else{
                Toast.makeText(this,"The password does not match.",Toast.LENGTH_LONG).show();
            }

        }

        }






    }




