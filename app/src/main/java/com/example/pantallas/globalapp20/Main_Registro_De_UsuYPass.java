package com.example.pantallas.globalapp20;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TooltipCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Gravity;
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
import com.tooltip.Tooltip;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Main_Registro_De_UsuYPass extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{


    RequestQueue rq;
    JsonRequest jrq;

    String newPass,confirPass,varibletexto;
    String AES= "AES";


    //variable para el tipo de letra
    private Typeface FuenteLetra;



    TextView datocedulalbl,datoNombreSuscriptorlbl,lbl1,lbl2,lbl3,lbl4,lbl5;
    EditText cajaNewUsu,cajaNewPass,cajaConfirPass;
    Button btnGuardarRegistro,btnSalir;

    //variable para la encriptación de la contraseña
    private String textoSalida,encode_text;

    String publicKey,privateKey;

    User usuario = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //variables que toman la fuente
        String fuente1 ="fuentes/quenda.otf";
        String fuente2 ="fuentes/massa.ttf";
        this.FuenteLetra =Typeface.createFromAsset(getAssets(),fuente2);

        setContentView(R.layout.activity_main__registro__de__usu_ypass);
        datocedulalbl=(TextView) findViewById(R.id.lblCedulaSuscriptor);
        datocedulalbl.setTypeface(FuenteLetra);
        datoNombreSuscriptorlbl=(TextView)findViewById(R.id.lblNombreSuscriptor);
        datoNombreSuscriptorlbl.setTypeface(FuenteLetra);

        lbl1 =(TextView) findViewById(R.id.textView1);
        lbl1.setTypeface(FuenteLetra);
        lbl2 =(TextView) findViewById(R.id.textView2);
        lbl2.setTypeface(FuenteLetra);
        lbl3 =(TextView) findViewById(R.id.textView3);
        lbl3.setTypeface(FuenteLetra);
        lbl4 =(TextView) findViewById(R.id.textView4);
        lbl4.setTypeface(FuenteLetra);
        lbl5 =(TextView) findViewById(R.id.textView5);
        lbl5.setTypeface(FuenteLetra);



        cajaNewUsu=(EditText) findViewById(R.id.txtNewUsu);
        cajaNewUsu.setTypeface(FuenteLetra);

        cajaNewPass=(EditText) findViewById(R.id.txtNewPass);
        cajaNewPass.setTypeface(FuenteLetra);

        cajaConfirPass=(EditText) findViewById(R.id.txtConfirPass);
        cajaConfirPass.setTypeface(FuenteLetra);


        btnGuardarRegistro=(Button)findViewById(R.id.btnGuardarReg);
        btnGuardarRegistro.setTypeface(FuenteLetra);

        btnSalir=(Button)findViewById(R.id.btnSalir);
        btnSalir.setTypeface(FuenteLetra);


        //variables para capturar los datos que vienen del Main_login


        String datoNombreSuscriptor=getIntent().getStringExtra("nombres");
        String datoCedula=getIntent().getStringExtra("documento");

        //enlazando los datos que vienen de fragment_validar_usu a los label del layout
        datocedulalbl.setText(datoCedula);
        datoNombreSuscriptorlbl.setText(datoNombreSuscriptor);





        //metodo para dae el mensaje de ayuda

        TextWatcher textWatcher = new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s , int start,int before,int count){

            }
            @Override
            public void afterTextChanged(Editable s){

                if (s.toString().length() <= 5) {
                        cajaNewPass.setError("LA CONTRASEÑA ES MUY CORTA");
                    } else {

                    }





            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after){

            }

        };

        cajaNewPass.addTextChangedListener(textWatcher);

        TextWatcher textWatcher1 = new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s , int start,int before,int count){

            }
            @Override
            public void afterTextChanged(Editable s){

                if(s.toString().length()<=5 ){
                cajaNewUsu.setError("EL USUARIO ES MUY CORTO");
                }
                else {


                }


            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after){

            }

        };

        cajaNewUsu.addTextChangedListener(textWatcher1);





        rq = Volley.newRequestQueue(getApplicationContext());

        btnGuardarRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                guardarRegistro();


            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);


            }
        });


    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getApplicationContext(), "SE HIZO EL INGRESO DE DATOS", Toast.LENGTH_SHORT).show();
        volver();

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray= response.optJSONArray("datos");
        JSONObject jsonObject=null;
        Toast.makeText(getApplicationContext(), "NO SE HIZO EL INGRESO DE DATOS", Toast.LENGTH_SHORT).show();

    }


    //metodo guardar el registro

    public void guardarRegistro() {

        if(!cajaNewUsu.getText().toString().isEmpty() && !cajaNewPass.getText().toString().isEmpty() && !cajaConfirPass.getText().toString().isEmpty() && cajaNewUsu.length()>5){
            newPass=cajaNewPass.getText().toString();
            confirPass=cajaConfirPass.getText().toString();




            if(newPass.equals(confirPass)){

                //encriptación de contraseña

                try {

                    //Obtenemos el texto desde el cuadro de texto
                    String original = cajaNewPass.getText().toString();

                    RSA rsa = new RSA();


                    //le asignamos el Contexto
                    rsa.setContext(getBaseContext());

                    //Generamos un juego de claves
                    rsa.genKeyPair(1024);

                    //Guardamos en la memoria las claves
                     //publicKey = rsa.getPublicKeyString();
                     //privateKey = rsa.getPrivateKeyString();

                    publicKey = rsa.getPublicKeyString();
                    privateKey = rsa.getPrivateKeyString();



                    //Ciframos
                    encode_text = rsa.Encrypt(original);

                   // Toast.makeText(getApplicationContext(),""+privateKey, Toast.LENGTH_SHORT).show();

                    //Mostramos el texto cifrado
                    // datoNombreSuscriptorlbl.setText(encode_text);



                } catch (Exception e) {
                    e.printStackTrace();
                }

                IngresarDatos_Usu_Pass();

            }


        }


        if(cajaNewUsu.getText().toString().isEmpty()){
            cajaNewUsu.requestFocus();
            Toast.makeText(getApplicationContext(), "CAMPO OBLIGATORIO", Toast.LENGTH_SHORT).show();


        }
        else if(cajaNewUsu.length()<=5){
            cajaNewUsu.setText("");
            cajaNewUsu.requestFocus();
            Toast.makeText(getApplicationContext(), "EL CAMPO DEBE SER MAYOR A 5 CARACTERES", Toast.LENGTH_SHORT).show();

        }



        else if(cajaNewPass.getText().toString().isEmpty()){
            cajaNewPass.setText("");
            cajaNewPass.requestFocus();
            Toast.makeText(getApplicationContext(), "CAMPO OBLIGATORIO", Toast.LENGTH_SHORT).show();
        }
        else if(cajaNewPass.length()<=5){
            cajaNewPass.setText("");
            cajaConfirPass.setText("");
            cajaNewPass.requestFocus();
            Toast.makeText(getApplicationContext(), "EL CAMPO DEBE SER MAYOR A 5 CARACTERES", Toast.LENGTH_SHORT).show();

        }

        else if(!cajaNewPass.getText().toString().equals(cajaConfirPass.getText().toString())){
            Toast.makeText(getApplicationContext(), "LA CONSTRASEÑA NO ES LA MISMA", Toast.LENGTH_SHORT).show();
            cajaConfirPass.setText("");
            cajaConfirPass.requestFocus();
            newPass="";
            confirPass="";
        }
        else if(cajaConfirPass.getText().toString().isEmpty()){
            cajaConfirPass.requestFocus();
            //Toast.makeText(getApplicationContext(), "CAMPO OBLIGATORIO", Toast.LENGTH_SHORT).show();
        }

    }

    //metodo para ingresar el usuario y la contraseña a la BD con la contraseña encriptada
    //metodo para ingresar el nuevo usuario y la nueva contraseña del titular de la cuenta

    private void IngresarDatos_Usu_Pass(){
        //para conexion remota se coloca el dns, y para local la ip
        String url ="http://seller.global.net.co/prueba_android/insertarLog.php?usuario="+cajaNewUsu.getText().toString()+
                "&pass="+encode_text
                +"&publickey="+publicKey+"&privatekey="+privateKey+
                "&documento="+datocedulalbl.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);



    }

    //metodo para regresar al usuario al login
    public void volver(){
        Intent intencion = new Intent(this, Main_Portada.class);

        startActivity(intencion);
        finish();

    }




}
