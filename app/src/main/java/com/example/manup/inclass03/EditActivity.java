package com.example.manup.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etName;
    EditText etemail;
    RadioGroup rg;
    TextView tvdepartment;
    TextView tvmood;
    SeekBar sbvalue;
    Button btnsave;
    Student student;
    int flagval=0;
    RadioButton rbsis,rbcs,rbbio,rbothers;
    static String Display_Key="display";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Activity");

        etName = (EditText) findViewById(R.id.etName);
        etemail = (EditText) findViewById(R.id.etemail);
        rg = (RadioGroup) findViewById(R.id.rg);
        tvdepartment = (TextView) findViewById(R.id.tvdepartment);
        tvmood = (TextView) findViewById(R.id.tvmood);
        btnsave = (Button) findViewById(R.id.btnsave);
        rbsis = (RadioButton) findViewById(R.id.rbsis);
        rbcs = (RadioButton) findViewById(R.id.rbcs);
        rbbio = (RadioButton) findViewById(R.id.rbbio);
        rbothers = (RadioButton) findViewById(R.id.rbothers);
        sbvalue = (SeekBar) findViewById(R.id.sbvalue);


        if (getIntent().getExtras() != null) {
            student = (Student) getIntent().getExtras().getSerializable(DisplayActivity.Edit_Key);
            Log.d("stu", getIntent().getExtras().getSerializable(DisplayActivity.Edit_Key) +"");
            flagval = getIntent().getExtras().getInt(DisplayActivity.Flag_Key);
            Log.d("flag" , flagval +"");
            Log.d("flag", getIntent().getExtras().getInt(DisplayActivity.Flag_Key) + "");
            switch (flagval) {
                case 1:
                    etName.setText(student.getName());
                    etName.setVisibility(View.VISIBLE);
                    etemail.setVisibility(View.INVISIBLE);
                    tvdepartment.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    sbvalue.setVisibility(View.INVISIBLE);
                    tvmood.setVisibility(View.INVISIBLE);
                    break;

                case 2:

                    etName.setVisibility(View.INVISIBLE);
                    etemail.setVisibility(View.VISIBLE);
                    etemail.setText(student.getEmail());
                    tvdepartment.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    tvdepartment.setVisibility(View.INVISIBLE);
                    sbvalue.setVisibility(View.INVISIBLE);
                    tvmood.setVisibility(View.INVISIBLE);
                    break;

                case 3:

                    etName.setVisibility(View.INVISIBLE);
                    etemail.setVisibility(View.INVISIBLE);
                    tvmood.setVisibility(View.INVISIBLE);
                    sbvalue.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    if(student.getDepartment().equals(getResources().getString(R.string.rbsis)))
                {
                    rg.check(R.id.rbsis);
                }
                    if(student.getDepartment().equals(getResources().getString(R.string.rbcs)))
                    {
                        rg.check(R.id.rbcs);
                    }
                    if(student.getDepartment().equals(getResources().getString(R.string.rbbio)))
                    {
                        rg.check(R.id.rbbio);
                    }
                    if(student.getDepartment().equals(getResources().getString(R.string.rbothers)))
                    {
                        rg.check(R.id.rbothers);
                    }

                    break;

                case 4:
                    etName.setVisibility(View.INVISIBLE);
                   etemail.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    tvdepartment.setVisibility(View.VISIBLE);
                    tvdepartment.setVisibility(View.INVISIBLE);

                    sbvalue.setProgress(Integer.parseInt(student.getMood()));
                    break;


            }
        }


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flagval) {
                    case 1:
                        if (!(etName.getText().toString().equals("")) || (MainActivity.checkName(etName))) {
                            student.setName(etName.getText().toString());
                            Log.d("name", etName.getText().toString());
                            Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                            intent.putExtra(Display_Key, student);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            etName.setError("Please enter a valid name");
                        }
                        break;


                    case 2:

                        if (etemail.getText().toString() != null && MainActivity.checkEmail(etemail)) {
                            student.setEmail(etemail.getText().toString());
                            Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                            intent.putExtra(Display_Key, student);
                            setResult(RESULT_OK, intent);
                            finish();

                        }
                        else {
                            etemail.setError("invalid email");
                            setResult(RESULT_CANCELED);
                        }
                        break;

                    case 3:
                        if (!(rg.getCheckedRadioButtonId() == -1)) {
                            student.setDepartment(((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString());
                            Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                            intent.putExtra(Display_Key, student);
                            setResult(RESULT_OK, intent);
                        } else {
                            Toast.makeText(EditActivity.this, "Please select a department", Toast.LENGTH_LONG).show();
                        }

                        finish();
                        break;

                    case 4:
                        student.setMood(String.valueOf(sbvalue.getProgress()));
                        Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                        intent.putExtra(Display_Key, student);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;

                }



        }


        });
    }
    }


