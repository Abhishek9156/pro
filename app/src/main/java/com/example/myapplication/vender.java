package com.example.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class vender extends AppCompatActivity implements OnClickListener {
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tvm;
    Button button1, bt,b,bu,bv;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = openOrCreateDatabase("pro", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS vender(name VARCHAR, address VARCHAR, contact VARCHAR, emailid VARCHAR, company VARCHAR, venderid VARCHAR);");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender);
        b=findViewById(R.id.delete);
        tv1 = findViewById(R.id.textView9);
        tv2 = findViewById(R.id.textView10);
        tv3 = findViewById(R.id.textView11);
        tv4 = findViewById(R.id.textView12);
        bu=findViewById(R.id.update);
        bv=findViewById(R.id.view);
        tv5 = findViewById(R.id.textView13);
        tv6 = findViewById(R.id.textView15);
        tvm = findViewById(R.id.textView7);
        ed1 = findViewById(R.id.ed_name);
        ed2 = findViewById(R.id.ed_address);
        ed3 = findViewById(R.id.ed_company_name);
        ed4 = findViewById(R.id.ed_e_mail);
        ed5 = findViewById(R.id.ed_contact_no);
        ed6 = findViewById(R.id.edvender);
        button1 = findViewById(R.id.button4);
        bt = findViewById(R.id.btviewall);

        button1.setOnClickListener(this);
        bt.setOnClickListener(this);
        b.setOnClickListener(this);
        bv.setOnClickListener(this);
        bu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                if (ed1.getText().toString().trim().length() == 0 || ed2.getText().toString().trim().length() == 0 || ed3.getText().toString().trim().length() == 0 || ed4.getText().toString().trim().length() == 0 || ed5.getText().toString().trim().length() == 0 || ed6.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showmessage("Error", "Please enter all Details");
                    return;
                }
                db.execSQL("INSERT INTO vender VALUES('" + ed1.getText() + "','" + ed2.getText() + "','" + ed3.getText() + "','" + ed4.getText() + "','" + ed5.getText() + "','" + ed6.getText() + "');");
                showmessage("Success", "Record added");
                clearText();
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btviewall:
                Cursor c4 = db.rawQuery("SELECT * FROM vender", null);
                if (c4.getCount() == 0) {
                    showmessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c4.moveToNext()) {
                    buffer.append("Name: " + c4.getString(0) + "\n");
                    buffer.append("Address: " + c4.getString(1) + "\n");
                    buffer.append("Company Name: " + c4.getString(2) + "\n");
                    buffer.append("Emailid: " + c4.getString(3) + "\n");
                    buffer.append("Contactno: " + c4.getString(4) + "\n");
                    buffer.append("Venderid: " + c4.getString(5) + "\n\n");

                }
                showmessage("vender Details", buffer.toString());
                Toast.makeText(this, "View_All", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                if(ed6.getText().toString().trim().length()==0)
                {
                    showmessage("Error", "Please enter VenderId");
                    return;
                }
                Cursor c11=db.rawQuery("SELECT * FROM vender WHERE venderid='"+ed6.getText()+"'", null);
                if(c11.moveToFirst())
                {
                    db.execSQL("DELETE FROM vender WHERE venderid='"+ed6.getText()+"'");
                    showmessage("Success", "Record Deleted");
                }
                else
                {
                    showmessage("Error", "Invalid Vender Id");
                }
                clearText();

                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.update:
                if(ed6.getText().toString().trim().length()==0)
                {
                    showmessage("Error", "Please enter venderId");
                    return;
                }
                Cursor c111=db.rawQuery("SELECT * FROM vender WHERE venderid='"+ed6.getText()+"'", null);

                if(c111.moveToFirst())
            {

                    db.execSQL("UPDATE vender SET name='"+ed1.getText()+"',contact='"+ed5.getText()+"',emailid='"+ed4.getText()+"',company='"+ed3.getText()+"',address='"+ed2.getText()+
                            "' WHERE venderid='"+ed6.getText()+"'");
                    showmessage("Success", "Record Modified");
                }
                else
                {
                    showmessage("Error", "Invalid vender Id");
                }
                clearText();

                Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();
                break;

            case R.id.view:
                if(ed6.getText().toString().trim().length()==0)
                {
                    showmessage("Error", "Please enter Vender Id");
                    return;
                }
                Cursor c2=db.rawQuery("SELECT * FROM vender WHERE venderid='"+ed6.getText()+"'", null);
                if(c2.moveToFirst())
                {
                    ed1.setText(c2.getString(0));
                    ed2.setText(c2.getString(1));
                    ed3.setText(c2.getString(2));
                    ed4.setText(c2.getString(3));
                    ed5.setText(c2.getString(4));
                }
                else {
                    showmessage("Error", "Invalid Rollno");
                    clearText();
                }
                Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    private void clearText() {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
    }

    private void showmessage(String errorrr, String please_enter_all_information) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(please_enter_all_information);
        builder.setTitle(errorrr);
        builder.show();

    }
}