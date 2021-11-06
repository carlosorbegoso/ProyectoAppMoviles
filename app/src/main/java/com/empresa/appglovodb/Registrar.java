package com.empresa.appglovodb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
    DatePickerDialog.OnDateSetListener setListener;
    EditText us, pas, nom, ap, etDate;
    Button reg, can;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        us =(EditText) findViewById(R.id.RegUser);
        pas =(EditText) findViewById(R.id.RegPass);
        nom =(EditText) findViewById(R.id.RegNombre);
        ap =(EditText) findViewById(R.id.RegApellido);
        etDate =(EditText) findViewById(R.id.et_date);
        reg =(Button) findViewById(R.id.btnRegRegistrar);
        can =(Button) findViewById(R.id.btnRegCancelar);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dao = new daoUsuario(this);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registrar.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = year + "/" + month + "/" + day;
                                etDate.setText(date);
                            }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

    }

    public void onClick(View v){
       switch (v.getId()) {
           case R.id.btnRegRegistrar:
               Usuario u=new Usuario();
               u.setUsuario(us.getText().toString());
               u.setPassword(pas.getText().toString());
               u.setNombre(nom.getText().toString());
               u.setApellidos(ap.getText().toString());
               if(!u.isNull()){
                   Toast.makeText(this, "Error: campos vacios", Toast.LENGTH_LONG).show();
               }else if (dao.insertUsuario(u)){
                   Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                   Intent i2=new Intent(Registrar.this, MainActivity.class);
                   startActivity(i2);
                   finish();
               }else{
                   Toast.makeText(this, "USUARIO ya existe ", Toast.LENGTH_LONG).show();
               }

               break;

           case R.id.btnRegCancelar:
               Intent i=new Intent(Registrar.this,MainActivity.class);
               startActivity(i);
               finish();
               break;
           }
       }


    }



