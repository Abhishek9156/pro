package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Customer extends AppCompatActivity implements View.OnClickListener {
    Button save, view,del;
    TextView dofb;
    EditText nm, add, id, eid, no;
    SQLiteDatabase db;
    DatePickerDialog datePickerDialog;

    String userGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        nm = findViewById(R.id.cname);
        add = findViewById(R.id.cadd);
        id = findViewById(R.id.cid);
        dofb = findViewById(R.id.dob);
        del=findViewById(R.id.delete);
        no = findViewById(R.id.cmno);
        eid = findViewById(R.id.ceid);
        save = findViewById(R.id.save);
        view = findViewById(R.id.viweall);

        save.setOnClickListener(this);
        view.setOnClickListener(this);
        del.setOnClickListener(this);

        db = openOrCreateDatabase("pro", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS customer(name VARCHAR,address VARCHAR,id VARCHAR,datebirth VARCHAR,gender VARCHAR,eid VARCHAR,mno VARCHAR);");

        dofb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Customer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dofb.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cm:
                if (checked)
                    userGender = "male";
                break;
            case R.id.cf:
                if (checked)
                    userGender = "Female";

                break;

            //Toast.makeText(this, "gender" + userGender, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (nm.getText().toString().trim().length() == 0 ||
                        add.getText().toString().trim().length() == 0 ||
                        id.getText().toString().trim().length() == 0 ||
                        dofb.getText().toString().trim().length() == 0 ||
                        eid.getText().toString().trim().length() == 0 ||
                        no.getText().toString().trim().length() == 0
                ) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO customer VALUES('" + nm.getText().toString() + "','" + add.getText().toString() +
                        "','" + id.getText().toString() + "','" + dofb.getText().toString() + "','" + userGender + "','" + eid.getText().toString() + "','" + no.getText().toString() + "');");
                showMessage("Success", "Record added");
                clearText();
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.viweall:
                Cursor c2 = db.rawQuery("SELECT * FROM customer", null);
                if (c2.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c2.moveToNext()) {
                    buffer.append("Name: " + c2.getString(0) + "\n");
                    buffer.append("Address: " + c2.getString(1) + "\n");
                    buffer.append("Customer id: " + c2.getString(2) + "\n");
                    buffer.append("Date of birth: " + c2.getString(3) + "\n");
                    buffer.append("Gender : " + c2.getString(4) + "\n");
                    buffer.append("Email id: " + c2.getString(5) + "\n");
                    buffer.append("Mobile No : " + c2.getString(6) + "\n\n");
                }
                showMessage("Customer Details", buffer.toString());
                Toast.makeText(this, "viweall", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                if(+id.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Customer Id");
                    return;
                }
                Cursor c11=db.rawQuery("SELECT * FROM customer WHERE id='"+id.getText()+"'", null);
                if(c11.moveToFirst())
                {
                    db.execSQL("DELETE FROM customer WHERE id='"+id.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Customer Id");
                }
                clearText();

                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    private void clearText() {
        nm.setText("");
        add.setText("");
        id.setText("");
        dofb.setText("");
        eid.setText("");
        no.setText("");

    }

    private void showMessage(String error, String please_enter_all_values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);
        builder.setMessage(please_enter_all_values);
        builder.show();
    }
}