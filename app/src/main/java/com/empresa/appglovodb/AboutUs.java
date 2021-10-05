package com.empresa.appglovodb;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class AboutUs extends AppCompatActivity {


    // incializamos
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_aboutus);

        drawerLayout=findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){

        MenuActivity.openDrawer(drawerLayout);
    }

    public  void ClickLogo(View view){
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){

        MenuActivity.redirectActivity(this,MenuActivity.class);
    }


    public void ClickDashboard(View view){

        MenuActivity.redirectActivity(this,PedidoNuevoActivity.class);
    }
    public  void ClickAboutUs(View view){

        recreate();


    }
    public  void ClickLogout(View view){
        MenuActivity.logout(this);
    }
    @Override
    protected void onPause(){

        super.onPause();
        // cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }
}
