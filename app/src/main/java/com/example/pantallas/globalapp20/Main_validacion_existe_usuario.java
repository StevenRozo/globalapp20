package com.example.pantallas.globalapp20;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.pantallas.globalapp20.NotificacionService.NOTIFICACION_ID;

public class Main_validacion_existe_usuario extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;

    EditText cajadocumento,cajaUsulog,cajaPasslog,cajaDocu;
    Button btnIngresar, btnValidarDocu,btnRegUsu,btnSalir;
    TextView lblDigiteIdentificacion;

    private Typeface FuenteLetra;

    //variable para la encriptación de la contraseña
    private String textoSalida;

    String apiKeyEncriptada ;
    String passwordEncriptacion;

    User usuario = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_validacion_existe_usuario);

        //para cancelar la notificación entrante

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(NOTIFICACION_ID);

        String fuente1 ="fuentes/quenda.otf";
        String fuente2 ="fuentes/massa.ttf";



        this.FuenteLetra =Typeface.createFromAsset(getAssets(),fuente2);


        cajadocumento = (EditText) findViewById(R.id.txtDocuUsu);
        cajadocumento.setTypeface(FuenteLetra);

        btnValidarDocu =(Button) findViewById(R.id.btnValidarDocu);
        btnValidarDocu.setTypeface(FuenteLetra);
        btnSalir= (Button) findViewById(R.id.btnSalir);
        btnSalir.setTypeface(FuenteLetra);


        lblDigiteIdentificacion = (TextView) findViewById(R.id.lblDigiteIdentificacion);
        lblDigiteIdentificacion.setText(Html.fromHtml("<b>DIGITE SU NÚMERO DE IDENTIFICACIÓN</b>"));
        lblDigiteIdentificacion.setTypeface(FuenteLetra);



        // line de codigo de instancia de la librería Volley

        rq = Volley.newRequestQueue(getApplicationContext());



        btnValidarDocu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!cajadocumento.getText().toString().isEmpty() && cajadocumento.length() > 5) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Main_validacion_existe_usuario.this);
                    builder.setMessage("Documento No : "+cajadocumento.getText().toString()+ " ?");
                    builder.setTitle("Confirmar");
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            validarDatos();

                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog=builder.create();
                    dialog.show();




                } else {

                    Toast.makeText(getApplicationContext(), "CAMPO UBLIGATORIO", Toast.LENGTH_SHORT).show();
                    cajadocumento.setText("");

                    //campoRequerido.setVisibility(View.VISIBLE);
                }



            }

        });



        btnSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Salir();
            }

        });


    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // Toast.makeText(getApplicationContext(), "USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
        Usu_no_existe();
    }

    @Override
    public void onResponse(JSONObject response) {
        // se van a tomar los datos de la clase usuario



        //creación de objeto tipo Json para recuperar los datos del array del archivo php

        // el objeto de tipo array se relaciona directamente con el array "datos" que se crea en el archivo php
        JSONArray jsonArray= response.optJSONArray("datos");
        JSONObject jsonObject=null;

        // se optienen los datos que devuelve el arreglo y que devuelven la consulta para guardarlos en los atributos de la clase "User"
        try {

            jsonObject = jsonArray.getJSONObject(0);

            cajadocumento.setText(jsonObject.optString("per_id"));

            usuario.setId_persona(jsonObject.optString("per_id"));
            usuario.setDocu_persona(jsonObject.optString("per_num_documento"));
            usuario.setNomb_usu(jsonObject.optString("per_nombre"));
            usuario.setApe_usu(jsonObject.optString("per_apellido"));
            usuario.setUsuario(jsonObject.optString("per_usuario"));
            usuario.setPass(jsonObject.optString("per_contrasenia"));
            usuario.setUsu_publickey(jsonObject.optString("publicKey"));
            usuario.setUsu_privatekey(jsonObject.optString("privateKey"));






        } catch (JSONException e) {
            e.printStackTrace();
        }

       // Toast.makeText(getApplicationContext(), "DATOS OK", Toast.LENGTH_SHORT).show();
        Login();
    }

    // metodo para validar que el documento del usuario coincida con el de la DB
    private void validarDatos(){

        //para conexion remota se coloca el dns, y para local la ip
        String url ="http://seller.global.net.co/prueba_android/validacion_usu_documento.php?documento="+cajadocumento.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);



    }

    // metodo para pasar a validacion de usuario y contraseña

    public void Login(){

        Intent intencion = new Intent(getApplicationContext(),Main_login.class);
        intencion.putExtra(Main_login.nombres,usuario.getNomb_usu()+" "+usuario.getApe_usu());
        intencion.putExtra(Main_login.id_usuario,usuario.getId_persona());
        intencion.putExtra(Main_login.documento_log,usuario.getDocu_persona());
        intencion.putExtra(Main_login.usu_log,usuario.getUsuario());
        intencion.putExtra(Main_login.usu_pass,usuario.getPass());
        intencion.putExtra(Main_login.usu_privatekey,usuario.getUsu_privatekey());
        intencion.putExtra(Main_login.usu_publickey,usuario.getUsu_publickey());


        startActivity(intencion);
    }

    // metodo para pasar al activity usuario_no_existe

    public void Usu_no_existe(){

        Intent intencion = new Intent(getApplicationContext(),Main_usuario_no_existe.class);

        startActivity(intencion);
    }

    //metodo para cerrar la app

    public void Salir(){

        System.exit(0);

    }


}
