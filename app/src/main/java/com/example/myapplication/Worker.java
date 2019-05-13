package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Worker extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button esavebtn,eview;

    EditText ename, eadd, eage,eteam, ecode, econtact, epost, esalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        //operation();
        ename = findViewById(R.id.editname);
        eadd = findViewById(R.id.editaddress);
        eage = findViewById(R.id.editage);
        eteam = findViewById(R.id.editteam);
        ecode = findViewById(R.id.editworkcode);
        econtact = findViewById(R.id.editcontact);
        epost = findViewById(R.id.editpost);
        esalary = findViewById(R.id.editsalary);
        esavebtn = findViewById(R.id.esavebtn);
        eview=findViewById(R.id.view);

        db = openOrCreateDatabase("WorkerData", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS worker(name VARCHAR,address VARCHAR, age VARCHAR,team VARCHAR,code VARCHAR,contact VARCHAR,post VARCHAR,salary VARCHAR);");

        esavebtn.setOnClickListener(this);
        eview.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.esavebtn:
                if (ename.getText().toString().trim().length() == 0 ||
                        eadd.getText().toString().trim().length() == 0 ||
                        eage.getText().toString().trim().length() == 0 ||
                        eteam.getText().toString().trim().length() == 0 ||
                        ecode.getText().toString().trim().length() == 0 ||
                        econtact.getText().toString().trim().length() == 0 ||
                        epost.getText().toString().trim().length() == 0 ||
                        esalary.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showMessage("Error", "Please enter all values");
                    return;
                }

                db.execSQL("INSERT INTO worker VALUES('" + ename.getText() + "','" + eadd.getText() +"','" + eage.getText() + "','" + eteam.getText() + "','" + ecode.getText() + "','" + econtact.getText() + "','" + epost.getText() + "','" + esalary.getText() + "');");
                showMessage("Success", "Record added");
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
                clearText();
                break;

            case R.id.view:
                Cursor c4 = db.rawQuery("SELECT * FROM worker", null);
                if (c4.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c4.moveToNext()) {
                    buffer.append("name: " + c4.getString(0) + "\n");
                    buffer.append("address: " + c4.getString(1) + "\n");
                    buffer.append("age: " + c4.getString(2) + "\n");
                    buffer.append("team: " + c4.getString(3) + "\n");
                    buffer.append("code: " + c4.getString(4) + "\n");
                    buffer.append("contact: " + c4.getString(5) + "\n");
                    buffer.append("post: " + c4.getString(6) + "\n");
                    buffer.append("salary: " + c4.getString(7) + "\n\n");

                }
                showMessage("worker Details", buffer.toString());
                Toast.makeText(this, "View_All", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void clearText() {
        ename.setText(" ");
        eadd.setText(" ");
        eage.setText(" ");
        eteam.setText(" ");
        ecode.setText(" ");
        econtact.setText(" ");
        epost.setText(" ");
        esalary.setText(" ");
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}




