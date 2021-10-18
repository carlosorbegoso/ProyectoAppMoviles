package com.empresa.appglovodb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MenuActivity  extends AppCompatActivity {

    // variable carrucel
    private int [] mImages = new int[]{
            R.drawable.baner_barbecue , R.drawable.baner_cecina,R.drawable.baner_jugo,R.drawable.baner_pollo,
            R.drawable.baner_rocoto
    };
    private String[] mImagesTitle=new String[]{
            "Barbecue","Cecina","Jugo","Pollo a la braza","Rocoto"
    };


    //inicializamos el drawer
    DrawerLayout drawerLayout;

    @Override
     protected void onCreate(Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_menu);

        // REALIZANDO DRAWER
        //asiganando variable
        drawerLayout=findViewById(R.id.drawer_layout);

        // realizando el carrucel
        //asigannado variable carrusel
        CarouselView carouselView= findViewById(R.id.carrousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MenuActivity.this,mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void ClickMenu(View view){

        openDrawer (drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //abriendo drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){

        closeDrawer (drawerLayout);
    }

    public static void  closeDrawer(DrawerLayout drawerLayout) {
           if(drawerLayout.isDrawerOpen(GravityCompat.START)){
               // CUANDO DRAWER ESTA ABIERTO
               //CERRAR DRAWER
               drawerLayout.closeDrawer(GravityCompat.START);
           }
    }
    public void ClickHome(View view){

        recreate();
    }

    public void ClickDashboard(View view){

        redirectActivity(this, PlatoNuevoActivity.class);
    }

    public  void ClickAboutUs(View view){
        redirectActivity(this,AboutUs.class);
    }

    public  void ClickLogout(View view){
        logout(this);
    }

    public static void logout(final Activity activity) {
        // inicializamos el laert dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        //titulo
        builder.setTitle("Cerrar sesion");
        // mostrar mensaje
        builder.setMessage("estas seguro de salir del sistema");
        //yes
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish activity
                activity.finishAffinity();
                // saliendo
                System.exit(0);
            }
        });
        // no
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish activity
                dialog.dismiss();

            }
        });
        //  mostar dialogo
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aClass) {

        Intent itent=new Intent(activity,aClass);

        itent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(itent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close
        closeDrawer(drawerLayout);
    }




}
