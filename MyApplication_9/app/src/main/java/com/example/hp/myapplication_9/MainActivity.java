package com.example.hp.myapplication_9;

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
import static com.example.hp.myapplication_9.MyDBHandler.COLUMN_RECID;

public class MainActivity extends AppCompatActivity {
    private MyDBHandler dbHandler;
    private SQLiteDatabase database;
    private TextView etID,etESPN,etStuID;
    private TextView mDisplayDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //etID = (EditText)findViewById(R.id.idInp);
        etStuID = (TextView)findViewById(R.id.idInp);
        etESPN = (TextView)findViewById(R.id.etESPN);
        mDisplayDate = (TextView)findViewById(R.id.etLength);
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
        etESPN.setText(c.getString(2));
        mDisplayDate.setText(c.getString(3));
        c.close();
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
