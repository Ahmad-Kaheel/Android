package com.example.hp.myapplication_9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txtV= (TextView) findViewById(R.id.txtvSignUp);
        txtV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Intent it = new Intent(getApplicationContext(),NewUserActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
    public void usrLogIn(View V){
        EditText userName = (EditText) findViewById(R.id.nameInp);
        EditText passInput = (EditText) findViewById(R.id.pwdInp);
        String user = userName.getText().toString();
        String pass = passInput.getText().toString();
        SharedPreferences usrAccounts = getSharedPreferences("UsersAccounts", 0);
        if(!usrAccounts.contains(user)){
            Toast.makeText(getApplicationContext(),"The user account is not existent",Toast.LENGTH_LONG).show();
            return;
        }
        if( (usrAccounts.getString(user,null)).equals(pass)){
            Toast.makeText(getApplicationContext(),"The access is authenticated",Toast.LENGTH_LONG).show();
        Intent Int = new Intent(getApplicationContext(),insTsk.class);
        startActivity(Int);}

        else
            Toast.makeText(getApplicationContext(),"The password entered is wrong",Toast.LENGTH_LONG).show();
    }
    public void usrUpdate(View V){
        EditText userName = (EditText) findViewById(R.id.nameInp);
        EditText passInput = (EditText) findViewById(R.id.pwdInp);
        String user = userName.getText().toString();
        String pass = passInput.getText().toString();
        SharedPreferences usrAccounts = getSharedPreferences("UsersAccounts", 0);
        if(!usrAccounts.contains(user)){
            Toast.makeText(getApplicationContext(),"The user account is not existent",Toast.LENGTH_LONG).show();
            return;
        }
        if( (usrAccounts.getString(user,null)).equals(pass)) {
            Toast.makeText(getApplicationContext(), "The access is authenticated", Toast.LENGTH_LONG).show();
            Intent it = new Intent(getApplicationContext(),pdatePswr.class);
            startActivity(it);
            finish();
        }

        else
            Toast.makeText(getApplicationContext(),"The password entered is wrong",Toast.LENGTH_LONG).show();
    }
}
