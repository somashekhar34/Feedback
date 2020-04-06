package com.example.feedback;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    private EditText et_name,et_email,et_phone,et_suggest,et_roll;
    private String name,email,phone,suggest,roll;
    Button button;
    String k,date;
    char j,f;
    int p,year1;
    Spinner spin1,spin2,spin3;
    ArrayAdapter<String> adapter;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView yearofjoining1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText)findViewById(R.id.name) ;
        et_email=(EditText)findViewById(R.id.email) ;
        et_phone=(EditText)findViewById(R.id.phone) ;
        et_suggest=(EditText)findViewById(R.id.suggest) ;
        et_roll=(EditText)findViewById(R.id.roll);
        button =(Button)findViewById(R.id.button);
        spin1=(Spinner)findViewById(R.id.yos);
        spin2=(Spinner)findViewById(R.id.branch);
        spin3=(Spinner)findViewById(R.id.section);
        yearofjoining1 =(TextView)findViewById(R.id.yearofjoining);
        additemsonspinner1();
        additemsonspinner2();
        additemsonspinner3();
        addlisteneronspin1();
        addlisteneronspin2();
        addlisteneronspin3();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        yearofjoining1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                 year1 = cal.get(Calendar.YEAR);
                 k=Integer.toString(year1);
                 j=k.charAt(2);
                 f=k.charAt(3);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year1,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"onDataset: mm/dd/yyy:" +month +"/"+dayOfMonth+"/"+year);
                date = month+"/"+dayOfMonth+"/"+year;
                yearofjoining1.setText(date);


            }
        };


    }
    public void register()
    {
        initialize();
        if(!validate()){
            Toast.makeText(this,"Enter the correct details",Toast.LENGTH_LONG).show();
        }
        else
        {
            onsuccess();

        }

    }
    public void onsuccess(){

        //upon success
    }
    public boolean validate(){
       boolean valid =true;
       if(name.isEmpty()||name.length()>32){
           et_name.setError("please enter valid name ");
           valid =false;
       }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("please enter valid email ");
            valid =false;
        }
        if(phone.isEmpty()||!Patterns.PHONE.matcher(phone).matches()||phone.length()!=10){
            et_phone.setError("please enter valid phone no");
            valid =false;
        }
        if(roll.isEmpty()||roll.length()!=10||roll.charAt(2)!='3'||roll.charAt(3)!='1'||roll.charAt(4)!='1'){
            et_roll.setError("please enter valid roll_name");
            valid= false;
        }

        if(suggest.isEmpty()){
            et_suggest.setError("please drop any suggestion!");
            valid =false;
        }
       return valid;
    }
    public void initialize()
    { name=et_name.getText().toString().trim();
        email=et_email.getText().toString().trim();
        phone=et_phone.getText().toString().trim();
        roll=et_roll.getText().toString().trim();
        suggest=et_suggest.getText().toString().trim();

    }

 public void additemsonspinner1(){
        List<String> list1 = new ArrayList<String>();

        list1.add("I");
        list1.add("II");
        list1.add("III");
        list1.add("IV");
        ArrayAdapter<String> dataap1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
        dataap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataap1);
 }
    public void additemsonspinner2(){
        List<String> list2 = new ArrayList<String>();

        list2.add("CSE");
        list2.add("ECE");
        list2.add("EEE");
        list2.add("MECH");
        list2.add("CIVIL");
        list2.add("MECH-TRON");
        list2.add("IT");
        list2.add("ECM");
        ArrayAdapter<String> dataap2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);
        dataap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(dataap2);
    }
    public void additemsonspinner3(){
        List<String> list3 = new ArrayList<String>();

        list3.add("A");
        list3.add("B");
        list3.add("C");
        list3.add("D");
        list3.add("E");
        list3.add("F");
        ArrayAdapter<String> dataap3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list3);
        dataap3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(dataap3);
    }
    public void addlisteneronspin1()
    { spin1=(Spinner)findViewById(R.id.yos);
        spin1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    public void addlisteneronspin2()
    { spin2=(Spinner)findViewById(R.id.branch);
        spin2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    public void addlisteneronspin3()
    { spin3=(Spinner)findViewById(R.id.section);
        spin3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


}