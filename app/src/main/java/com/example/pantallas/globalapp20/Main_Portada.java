package com.example.pantallas.globalapp20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Main_Portada extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

    Button btn_ingresar,btn_sincuenta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__portada);

/*
        //instanciamiento del servicio NotificacionService

        Intent intent = new Intent(getApplicationContext(), NotificacionService.class);
        //Se inicia el servicio
        startService(intent);
*/

        //Slider de imagenes
        mSlideViewPager =(ViewPager)findViewById(R.id.slideViewPager);
        mDotLayout= (LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter= new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        btn_ingresar = (Button) findViewById(R.id.bnt_ingresa_login);
        btn_sincuenta = (Button) findViewById(R.id.bnt_no_tengo_cuenta);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ingresarAlogin();

            }

        });

        btn_sincuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoesCliente();
            }
        });


    }
    //metodo que da ingreso al login
 public void ingresarAlogin(){
     Intent intencion = new Intent(getApplicationContext(),Main_validacion_existe_usuario.class);

     startActivity(intencion);

    }


    //metodo que muestra la imagen de 'no eres cliente'
    public void NoesCliente(){


        Intent intencion = new Intent(getApplicationContext(),Main_usuario_no_existe.class);

        startActivity(intencion);

    }



//metodo para enlazar los puntos indicadores de imagen en el slider de la portada
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));


        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            //contenidos analiticos de contenido
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
