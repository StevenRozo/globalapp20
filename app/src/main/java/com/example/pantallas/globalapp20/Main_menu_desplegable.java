package com.example.pantallas.globalapp20;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

public class Main_menu_desplegable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Fragment_menu_desplegable_noticias.OnFragmentInteractionListener,Fragment_menu_desplegable_VerContratos.OnFragmentInteractionListener{


//variables para toamar los datos que se enviaron desde la calase 'login'
    public static final String nombres_usuario="names";
    public static final String id_usuario="id_usuario";
    public static final String documento_usuario="documento";




    private static final int COD_SELECCIONA =10;
    private static final int COD_FOTO =20;

    //variable para el instanciamiento del webView que muestra el periodico virtual en el inicio del 'Main_menu_desplegable'
    private WebView miWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_desplegable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      //  fab.setOnClickListener(new View.OnClickListener() {
     /*       @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setItemIconTintList(null);


        //cargue del webview del periodico virtual a través de la url
        miWebView = findViewById(R.id.Wv_main);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());
        miWebView.loadUrl("https://www.elespectador.com/periodico");






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
        //    return true;
       // }

        return super.onOptionsItemSelected(item);
    }


    // indica cuando seleccionamos algun elemento del menú
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        //Lógica para el instancimiento y vista de los fragments respectivos asociados al 'Main_menu_desplegable'


        int id = item.getItemId();
        Fragment miFragment = null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_estadocuenta) {


            miFragment = new Fragment_menu_desplegable_VerContratos();
            fragmentSeleccionado = true;

            //desclaracion de variables que toman los datos enviados desde la Bd capturados por Validacion_existe_usuario

            String Nombre_usuario = getIntent().getStringExtra("names");
            String documento_usuario = getIntent().getStringExtra("documento");
            String id_usuario = getIntent().getStringExtra("id_usuario");


            Bundle args = new Bundle();
            args.putString("texto_Nombre_usu", Nombre_usuario);
            args.putString("texto_Documento_usu", documento_usuario);
            args.putString("texto_id_usu", id_usuario);

            miFragment.setArguments(args);


            //desclaracion de variables que toman los datos enviados desde la Bd capturados por Validacion_existe_usuario

        }  else if (id == R.id.nav_fotoComprobante) {

            String Nombre_usuario1 = getIntent().getStringExtra("names");
            String documento_usuario1 = getIntent().getStringExtra("documento");
            String id_usuario1 = getIntent().getStringExtra("id_usuario");


            Bundle args1 = new Bundle();
            args1.putString("texto_Nombre_usu", Nombre_usuario1);
            args1.putString("texto_Documento_usu", documento_usuario1);
            args1.putString("texto_id_usu", id_usuario1);

            miFragment = new Fragment_enviar_comprovante();
            fragmentSeleccionado = true;



            miFragment.setArguments(args1);

        } else if (id == R.id.nav_noticias) {
            miFragment = new Fragment_periodico();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_slideshow) {
            miFragment = new Fragment_preguntas();
            fragmentSeleccionado = true;

        }  else if (id == R.id.nav_share) {
            miFragment = new Fragment_informacion_general();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_send) {
            miFragment = new Fragment_zona_cobertura();
            fragmentSeleccionado=true;
        }

        else if (id == R.id.nav_programacion_canales) {
            miFragment = new Fragment_programacion_canales();
            fragmentSeleccionado=true;
        }
        else if (id == R.id.nav_canal_via_tres_vivo) {
            miFragment = new Fragment_canalEnvivo();
            fragmentSeleccionado=true;
        }
        else if (id == R.id.nav_tienda_virtual) {
            miFragment = new Fragment_Tienda_Virtual();
            fragmentSeleccionado=true;
        }




        if(fragmentSeleccionado==true){
         getSupportFragmentManager().beginTransaction().replace(R.id.content_main_menu,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
