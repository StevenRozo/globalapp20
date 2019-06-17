package com.example.pantallas.globalapp20;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // FragmentManager fm = getSupportFragmentManager();

        //  fm.beginTransaction().replace(R.id.escenario, new SesionFragment()).commit();

        //fm.beginTransaction().replace(R.id.escenario, new fragment_login()).commit();

       Intent intencion = new Intent(getApplicationContext(),Main_Portada.class);


        //segundo menu
       // Intent intencion = new Intent(getApplicationContext(),Main_menu_desplegable.class);

        startActivity(intencion);
    }

}
