package com.example.pantallas.globalapp20;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.example.pantallas.globalapp20.NotificacionService.NOTIFICACION_ID;

public class Main_login extends AppCompatActivity {


    //variables para la toma de datos que envía la clase 'MainPortada'
    public static final String nombres="names";
    public static final String id_usuario="id_usuario";
    public static final String documento_log="documento";
    public static final String usu_log="usu";
    public static final String usu_pass="pass";
    public static final String usu_privatekey="usu_privateckey";
    public static final String usu_publickey="usu_publickey";

    //variable para el tipo de letra
    private Typeface FuenteLetra;

    Button btnIngresar,btnRegUsu,btnSalir;;
    EditText cajadocumento,cajaUsulog,cajaPasslog,cajaDocu;

    TextView lblNombres,lblDocumento,lblUsuario,lblPass;

    ImageView imgbienvenido;

    //variable para la encriptación de la contraseña
    private String textoSalida,decode_text;

    String publicKey, privateKey;




    User usuario = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);


        //variables que toman la fuente
        String fuente1 ="fuentes/quenda.otf";
        String fuente2 ="fuentes/massa.ttf";

        this.FuenteLetra =Typeface.createFromAsset(getAssets(),fuente2);

        lblNombres = (TextView)findViewById(R.id.lblNombre);
        lblDocumento = (TextView)findViewById(R.id.lbl_documento);
        lblUsuario = (TextView)findViewById(R.id.lbl_Usu_usuario);
        lblPass = (TextView)findViewById(R.id.lbl_Usu_Pass);

        btnIngresar =(Button) findViewById(R.id.btnIngresar);
        btnIngresar.setTypeface(FuenteLetra);
        btnRegUsu =(Button) findViewById(R.id.btnRegistroUsu);
        btnRegUsu.setTypeface(FuenteLetra);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setTypeface(FuenteLetra);

        cajaUsulog = (EditText) findViewById(R.id.txtUsulog);
        cajaUsulog.setTypeface(FuenteLetra);
        cajaPasslog = (EditText) findViewById(R.id.txtPasslog);
        cajaPasslog.setTypeface(FuenteLetra);




        //variables que reciben los datos enviados desde la clase validación de usuario
        String Nombre_usuario = getIntent().getStringExtra("names");
        String documento_usuario = getIntent().getStringExtra("documento");
        String id_usuario = getIntent().getStringExtra("id_usuario");
        String Usu_usuario = getIntent().getStringExtra("usu");
        String Pass_usuario = getIntent().getStringExtra("pass");

        String Usuario_privateKey = getIntent().getStringExtra("usu_privateckey");
        String Usuario_publicKey = getIntent().getStringExtra("usu_publickey");


        //rellenar el contructor en la clase usuario
        usuario.setId_persona(id_usuario);
        usuario.setNomb_usu(Nombre_usuario);
        usuario.setDocu_persona(documento_usuario);


        lblNombres.setText(Nombre_usuario);
        lblNombres.setTypeface(FuenteLetra); // se anida el tipo de fuente
        lblDocumento.setText(documento_usuario);
        lblUsuario.setText(Usu_usuario);
        lblPass.setText(Pass_usuario);


        publicKey=Usuario_publicKey;
        privateKey=Usuario_privateKey;

      // Toast.makeText(getApplicationContext()," "+Usuario_privateKey, Toast.LENGTH_SHORT).show();


        imgbienvenido = (ImageView)findViewById(R.id.img_bienvenido);




        //validación para mostrar o no el formulario de registro de usuario
        if(lblPass.getText().toString().equals("") || lblPass.getText().toString().equals("null") ){
            //Toast.makeText(getApplicationContext(),"No tiene usu y pass", Toast.LENGTH_SHORT).show();
            btnRegUsu.setVisibility(View.VISIBLE);
            btnIngresar.setVisibility(View.INVISIBLE);
            cajaUsulog.setVisibility(View.INVISIBLE);
            cajaPasslog.setVisibility(View.INVISIBLE);
            //imgbienvenido.setVisibility(View.VISIBLE);
            //lblNombres.setVisibility(View.VISIBLE);
        }
        else{
            //Toast.makeText(getApplicationContext(),"YA TIENE USUARIO Y CONTRASEÑA", Toast.LENGTH_SHORT).show();

        }

        //logica para el bonton btnIngresar
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Validación para campos vacíos
                if(cajaUsulog.getText().toString().isEmpty() ){
                    cajaUsulog.requestFocus();
                    //Toast.makeText(getApplicationContext(),"CAMPO OBLIGATORIO", Toast.LENGTH_SHORT).show();
                    cajaUsulog.setError("CAMPO OBLIGATORIO");

                }else if(cajaPasslog.getText().toString().isEmpty()){
                    cajaPasslog.requestFocus();
                  //  Toast.makeText(getApplicationContext(),"CAMPO OBLIGATORIO", Toast.LENGTH_SHORT).show();
                    cajaPasslog.setError("CAMPO OBLIGATORIO");
                }

                else{

                            //logica para la des encriptación de la contraseña
                    try {


                        //Creamos otro objeto de nuestra clase RSA
                        RSA rsa2 = new RSA();

                        //Le pasamos el contexto
                        rsa2.setContext(getBaseContext());

                        //Cargamos las claves que creamos anteriormente
                        // rsa2.openFromDiskPrivateKey("rsa.pri");
                        //rsa2.openFromDiskPublicKey("rsa.pub");

                        rsa2.setPublicKeyString(publicKey);
                        rsa2.setPrivateKeyString(privateKey);

                        //Desciframos
                         decode_text = rsa2.Decrypt(lblPass.getText().toString());


                   //     Toast.makeText(getApplicationContext(),""+privateKey, Toast.LENGTH_SHORT).show();

                        //Mostramos el texto ya descifrado
                       // txtDecode.setText(decode_text);

                        if(cajaUsulog.getText().toString().equals(lblUsuario.getText().toString()) && cajaPasslog.getText().toString().equals(decode_text)  ){
                           IngresoAlmenu();
                           // Toast.makeText(getApplicationContext(),"DATOS CORRECTOS ", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //validación para usuario y contraseña

                    if(!cajaUsulog.getText().toString().equals(lblUsuario.getText().toString()) ){
                        //Toast.makeText(getApplicationContext(),"USUARIO INCORRECTO", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getApplicationContext(),""+decode_text, Toast.LENGTH_SHORT).show();

                        cajaUsulog.setError("USUARIO INCORRECTO");
                        //cajaPasslog.setText("");
                        cajaUsulog.requestFocus();


                    }

                    else if(!decode_text.equals(cajaPasslog.getText().toString())){
                       // ben
                        cajaPasslog.setError("CONTRASEÑA INCORRECTA");
                        //cajaPasslog.setText("");
                        cajaPasslog.requestFocus();

                    }

                }

            }
        });

        btnRegUsu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RegistrarUsuario();


            }

        });

        btnSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               System.exit(0);


            }

        });

    }



    //metodo para ingresar al menú
    public void  IngresoAlmenu(){
        Intent intencion = new Intent(getApplicationContext(),Main_menu_desplegable.class);

        intencion.putExtra(Main_menu_desplegable.id_usuario,usuario.getId_persona());
        intencion.putExtra(Main_menu_desplegable.documento_usuario,usuario.getDocu_persona());
        intencion.putExtra(Main_menu_desplegable.nombres_usuario,usuario.getNomb_usu());

        startActivity(intencion);
    }


   //metodo para pasar al registro de usuario y contraseña

    public void RegistrarUsuario(){


        Intent intencion = new Intent(getApplicationContext(),Main_Registro_De_UsuYPass.class);
        intencion.putExtra("nombres",lblNombres.getText().toString());
        intencion.putExtra("documento",lblDocumento.getText().toString());

        startActivity(intencion);
    }


    //metodo para cerrar la app

    public void Salir(){
        System.exit(0);

    }




}
