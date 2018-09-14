package com.example.manup.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvname, tvemail, tvdepartment, tvmood;
    ImageView ivname, ivemail, ivdepartment, ivmood;
    Student student;
    int flag = 0;
    static String Edit_Key = "edit";
    static String Flag_Key = "flag";
    static int reqcode = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Display Activity");


        ivname = (ImageView) findViewById(R.id.ivname);
        ivname.setOnClickListener(this);
        ivemail = (ImageView) findViewById(R.id.ivemail);
        ivemail.setOnClickListener(this);
        ivdepartment = (ImageView) findViewById(R.id.ivdepartment);
        ivdepartment.setOnClickListener(this);
        ivmood = (ImageView) findViewById(R.id.ivmood);
        ivmood.setOnClickListener(this);

        if (getIntent().getExtras() != null) {

            tvname = (TextView) findViewById(R.id.tvname);
            tvemail = (TextView) findViewById(R.id.tvemail);
            tvdepartment = (TextView) findViewById(R.id.tvdepartment);
            tvmood = (TextView) findViewById(R.id.tvmood);
            if (getIntent().getExtras().getSerializable(MainActivity.Student_key) != null) {
                student = (Student) getIntent().getExtras().getSerializable(MainActivity.Student_key);
                tvname.setText(student.getName());
                tvemail.setText(student.getEmail());
                tvdepartment.setText(student.getDepartment());
                tvmood.setText(student.getMood() + " % Positive");
            }
            else if (getIntent().getExtras().getSerializable(EditActivity.Display_Key) != null) {
                student = (Student) getIntent().getExtras().getSerializable(EditActivity.Display_Key);
                tvname.setText(student.getName());
                tvemail.setText(student.getEmail());
                tvdepartment.setText(student.getDepartment());
                tvmood.setText(student.getMood() + " % Positive"    );
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivname) {
            flag = 1;
        }
        if (v.getId() == R.id.ivemail) {
            flag = 2;
        }
        if (v.getId() == R.id.ivdepartment) {
            flag = 3;
        }
        if (v.getId() == R.id.ivmood) {
            flag = 4;
        }


        Intent intent = new Intent("com.example.manup.inclass03.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Edit_Key, student);
        intent.putExtra(Flag_Key, flag);
        startActivityForResult(intent, reqcode);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqcode) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras().getSerializable(EditActivity.Display_Key) != null) {
                    student = (Student) data.getExtras().getSerializable(EditActivity.Display_Key);
                    tvname.setText(student.getName());
                    tvemail.setText(student.getEmail());
                    tvdepartment.setText(student.getDepartment());
                    tvmood.setText(student.getMood() +  " % Positive");

                }
                else if (requestCode == RESULT_CANCELED) {
                    Log.d("demo", "error");
                }

            }
        }

    }
}