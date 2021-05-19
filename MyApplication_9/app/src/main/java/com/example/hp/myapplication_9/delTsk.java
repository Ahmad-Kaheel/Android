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
import static com.example.hp.myapplication_9.MyDBHandler.TABLE_NAME;

public class delTsk extends AppCompatActivity {
    private EditText etID;
    private TextView etStuID,etESPN, etLength;
    private MyDBHandler dbHandler;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_tsk);
        etID = (EditText)findViewById(R.id.idInp);
        etESPN = (TextView)findViewById(R.id.etESPN);
        etLength = (TextView)findViewById(R.id.etLength);
        etStuID = (TextView)findViewById(R.id.edID);
        //dbHandler = new MyDBHandler(this);
        dbHandler = new MyDBHandler(getApplicationContext());
        database = dbHandler.getWritableDatabase();
    }
    public void shwIDInfo(View V){
        String id = etID.getText().toString();
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
        etLength.setText(c.getString(3));
        c.close();
    }
    public void delTsk(View view){
        String id = etID.getText().toString();
        if(id.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show();
            return;

        }

        database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = " + id + " ; ");
        Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
        return;
        //The remaining code is left as homework

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
