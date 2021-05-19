package com.example.hp.myapplication_9;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hp.myapplication_9.MyDBHandler.COLUMN_ID;
import static com.example.hp.myapplication_9.MyDBHandler.COLUMN_ESPN;
import static com.example.hp.myapplication_9.MyDBHandler.COLUMN_RECID;
import static com.example.hp.myapplication_9.MyDBHandler.COLUMN_mDisplayDate;
import static com.example.hp.myapplication_9.MyDBHandler.TABLE_NAME;


public class Update extends AppCompatActivity {
    private EditText etStuID;

    private MyDBHandler dbHandler;
    SQLiteDatabase database;
    private TextView etLength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etLength = (TextView) findViewById(R.id.etLength);
        etStuID = (EditText) findViewById(R.id.idInp);
        //dbHandler = new MyDBHandler(this);
        dbHandler = new MyDBHandler(getApplicationContext());
        database = dbHandler.getWritableDatabase();
    }
    public void shwIDInfo(View V){
        String id = etStuID.getText().toString();
        if(id.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show();
            return;
        }
        String sqltStmt = "Select * from " + dbHandler.TABLE_NAME
                + " where " + COLUMN_ID + " = ?";
        Cursor c = database.rawQuery(sqltStmt, new String[] {id});
        if(!c.moveToFirst()){
            Toast.makeText(getApplicationContext(),"No ID has matched",Toast.LENGTH_LONG).show();
            return;
        };
        //etStuID.setText(c.getString(1));

        etLength.setText(c.getString(3));
        c.close();
    }

    public void update(View view){
        String ID = etStuID.getText().toString();
        String Length = etLength.getText().toString();
        if(ID.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show();
            return ;
        }
        database.execSQL("update "+ dbHandler.TABLE_NAME + "(" + "set "
                +dbHandler.COLUMN_mDisplayDate+") VALUES (?)", new String[] {Length});
        String tstMsg;
        tstMsg = "Record is udgated";
        Toast.makeText(getApplicationContext(),tstMsg,Toast.LENGTH_LONG).show();
        etLength.setText(COLUMN_mDisplayDate);

    }



    public void delForm(View V){
        Intent t = new Intent(this,delTsk.class);
        startActivity(t);
        dbHandler.close();
        finish();

    }


    public void insForm(View V){
        Intent t = new Intent(this,insTsk.class);
        startActivity(t);
        dbHandler.close();
        finish();
    }

    public void updForm(View V){
        Intent t = new Intent(this,Update.class);
        startActivity(t);
        dbHandler.close();
        finish();

    }

    public void print(View V){
        Intent t = new Intent(this,MainActivity.class);
        startActivity(t);
        dbHandler.close();
        finish();

    }
}
