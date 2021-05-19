package com.example.hp.myapplication_9;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class insTsk extends AppCompatActivity {
    private static final String TAG = "insTsk";
    private EditText etID,etESPN;
    private MyDBHandler dbHandler;
    private SQLiteDatabase db;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_tsk);
        mDisplayDate = (TextView) findViewById(R.id.edLength);
        etID = (EditText)findViewById(R.id.edID);
        etESPN = (EditText)findViewById(R.id.edESPN);
        //etLength = (EditText)findViewById(R.id.edLength);
        dbHandler = new MyDBHandler(getApplicationContext());
        db = dbHandler.getWritableDatabase();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        insTsk.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

    mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

            String date = month + "/" + day + "/" + year;
            mDisplayDate.setText(date);
        }
    };
}

public void addButtonClicked(View view){
        String ID = etID.getText().toString();
        String ESPN = etESPN.getText().toString();
        String Length = mDisplayDate.getText().toString();
        if(ID.isEmpty() || ESPN.isEmpty() || Length.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show();
            return ;
        }
        db.execSQL("insert into "+ dbHandler.TABLE_NAME + "(" + dbHandler.COLUMN_ID + ","+
                dbHandler.COLUMN_ESPN +","+dbHandler.COLUMN_mDisplayDate+") VALUES (?,?,?)", new String[] {ID,ESPN,Length});
        String tstMsg;
        tstMsg = "Record is inserted";
        Toast.makeText(getApplicationContext(),tstMsg,Toast.LENGTH_LONG).show();
        etID.setText("");
        etESPN.setText("");
        mDisplayDate.setText("");

        Intent t = new Intent(this,insTsk.class);
        startActivity(t);
        dbHandler.close();
        finish();
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
