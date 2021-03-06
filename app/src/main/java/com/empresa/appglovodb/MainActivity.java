package com.empresa.appglovodb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass, pass2, davidEsElMejor_Y_Carlos_El_Peor;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;
    // recogiendo el nombre

           Editable usuario ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao= new daoUsuario(this);

        //traer el nombre
        
        usuario=user.getText();
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnEntrar:
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this, "Error: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux= dao.getUsuario(u,p);
                          Toast.makeText(this, " Datos correctos Bienvenido "+usuario  , Toast.LENGTH_LONG).show();
                    Intent i2= new Intent (MainActivity.this, MenuActivity.class);
                    i2.putExtra("id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario y/o password incorrectos", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnRegistrar:
                Intent i=new Intent(MainActivity.this, Registrar.class);
                startActivity(i);
                break;
        }


    }


}


