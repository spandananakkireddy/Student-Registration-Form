// Name- Priyanka Manusanipally - 801017222
//       Sai Spandana Nakkireddy - 801023658



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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etemail;
    RadioGroup rg;
    TextView tvdepartment;
    TextView tvmood;
    SeekBar sbvalue;
    Button btnsubmit;
    String name="";
    String mail="";
    String dept;
    String mood;
    static String Student_key="student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etemail = (EditText) findViewById(R.id.etemail);
        rg=(RadioGroup) findViewById(R.id.rg);
        tvdepartment = (TextView) findViewById(R.id.tvdepartment);
        tvmood= (TextView) findViewById(R.id.tvmood);
        btnsubmit=(Button) findViewById(R.id.btnsubmit);
        sbvalue=(SeekBar) findViewById(R.id.sbvalue);

        setTitle("Main Activity");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etName.getText().toString().equals("")) && !(etemail.getText().toString().equals(""))  ) {
                    if (checkName(etName)) {
                        if (checkEmail(etemail)) {
                            if (!(rg.getCheckedRadioButtonId() == -1)) {
                                name = etName.getText().toString();
                                Log.d("demo", name + "");
                                mail = etemail.getText().toString();
                                Log.d("demo", mail + "");
                                dept = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                                mood = String.valueOf(sbvalue.getProgress());


                                Student student = new Student(name, mail, dept, mood);
                                Log.d("demo", student + "");

                                Intent in = new Intent(MainActivity.this, DisplayActivity.class);
                                in.putExtra(Student_key, student);
                                startActivity(in);

                            } else {
                                Toast.makeText(MainActivity.this, "Please select a department", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            etemail.setError("please enter a valid email");
                        }
                    } else {
                        etName.setError("please enter a valid name" + "\n" + "Name should only have character input");
                    }
                }
                else
                    {
                        Toast.makeText(MainActivity.this, "please enter the data", Toast.LENGTH_LONG).show();
                    }


            }
        });
    }

    public static boolean checkEmail(EditText email) {
        String emailReg = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.getText().toString().matches(emailReg)) {
            return true;
        }
        else
        {
            return false;
        }

    }

    public static boolean checkName(EditText name)
    {
        String nameReg = "[a-zA-Z]+";
        if (name.getText().toString().matches(nameReg))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
