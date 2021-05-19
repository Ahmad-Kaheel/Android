package com.example.hp.myapplication_9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class pdatePswr extends AppCompatActivity {
    SharedPreferences usrAccs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdate_pswr);
    }
    public void done(View V){
        EditText userName = (EditText) findViewById(R.id.name);
        EditText passInput = (EditText) findViewById(R.id.lpwd);
        EditText npassInput = (EditText) findViewById(R.id.npwd);

        String user = userName.getText().toString();
        String pass = passInput.getText().toString();
        String npass = npassInput.getText().toString();
        usrAccs = getApplicationContext().getSharedPreferences("UsersAccounts", 0); //0 - for private mode
        SharedPreferences.Editor usrAccsEditor = usrAccs.edit();

        SharedPreferences usrAccounts = getSharedPreferences("UsersAccounts", 0);
        if( (usrAccounts.getString(user,null)).equals(pass)){
            usrAccounts = getSharedPreferences("UsersAccounts", 0);
            usrAccsEditor.putString(userName.getText().toString(),npassInput.getText().toString());
            usrAccsEditor.commit();
            Toast.makeText(getApplicationContext(),"The password modified ",Toast.LENGTH_LONG).show();
            Intent Int = new Intent(getApplicationContext(),login.class);
            startActivity(Int);
            finish();

        }
        else{
            Toast.makeText(getApplicationContext(),"The last password or name is wrong ",Toast.LENGTH_LONG).show();
        }
    }
}
