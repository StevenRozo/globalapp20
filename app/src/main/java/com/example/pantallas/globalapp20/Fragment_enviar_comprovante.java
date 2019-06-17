package com.example.pantallas.globalapp20;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


public class Fragment_enviar_comprovante extends Fragment {

    //variable para el tipo de letra
    private Typeface FuenteLetra;

    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"Comprobantes Global Play";

    //variables para recibir los argumentos del Fragment_VerEstadoDeCuenta
    private String texto_Nombre_usu,texto_Documento_usu,texto_id_usu,texto_NumeroContrato_usu,texto_Numero_Whatsappsede;

    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;

    String path;

    private String texto_NombreUsuario,texto_DocumentoUsuario;

    Button botonCargar;
    ImageView imagen,botonEnviarComprobante;
    TextView lblGestionarImagen;
    String url;


    public Fragment_enviar_comprovante() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            texto_Nombre_usu = getArguments().getString("texto_Nombre_usu");
            texto_Documento_usu=getArguments().getString("texto_Documento_usu");
            texto_id_usu=getArguments().getString("texto_id_usu");
            texto_NumeroContrato_usu =getArguments().getString("texto_Contrato_usu");
            texto_Numero_Whatsappsede=getArguments().getString("NumeroWhatsappSede");




        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_enviar_comprovante, container, false);

        //variables que toman la fuente
        String fuente1 ="fuentes/quenda.otf";
        String fuente2 ="fuentes/massa.ttf";

        this.FuenteLetra =Typeface.createFromAsset(getActivity().getAssets(),fuente2);

        imagen =(ImageView) view.findViewById(R.id.imagemId);
        lblGestionarImagen = (TextView) view.findViewById(R.id.textViewGestionarImagen);
        lblGestionarImagen.setTypeface(FuenteLetra);
        botonCargar=(Button) view.findViewById(R.id.btnCargarImg);
        botonCargar.setTypeface(FuenteLetra);
        botonEnviarComprobante=(ImageView) view.findViewById(R.id.btnEnviarComprobante);


        // Variables que reciben los datos de la clase "Fragment_menu_desplegable"

        texto_NombreUsuario = getArguments().getString("texto_Nombre_usu");
        texto_DocumentoUsuario= getArguments().getString("texto_Documento_usu");




        if(validaPermisos()){
            botonCargar.setEnabled(true);
        }else{
            botonCargar.setEnabled(false);
        }

        botonCargar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //llamarIntent();
                cargarImagen();
            }
        });


        botonEnviarComprobante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EnviarComprobante();
            }
        });
        return view;
    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((getContext().checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)&&
                (getContext().checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                botonCargar.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }

    }


    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getActivity().getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    public void onclick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {

        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    tomarFotografia();
                }else{
                    if (opciones[i].equals("Cargar Imagen")){
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();

    }


    private void tomarFotografia() {
        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }

        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";

        }


        path=Environment.getExternalStorageDirectory()+
                File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen=new File(path);

        Intent intent=null;
        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=getContext().getApplicationContext().getPackageName()+".provider";
            Uri imageUri=FileProvider.getUriForFile(getContext(),authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        startActivityForResult(intent,COD_FOTO);

        ////
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                    break;

                case COD_FOTO:
                    MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);
                    Toast.makeText(getContext(),"LA FOTO SE HA GUARDADO EN LA CARPETA '' Comprobantes Global Play ''", Toast.LENGTH_SHORT).show();

                    break;
            }


        }
    }



    private void EnviarComprobante(){



        //   https://api.whatsapp.com/send?phone=573158306064&text=Comprobante%20de%20pago



        Fragment_EnviarComprobanteWhatsapp verEstadoDeCuenta = new Fragment_EnviarComprobanteWhatsapp();


        url = "  https://api.whatsapp.com/send?phone=57"+texto_Numero_Whatsappsede+"=&text=Comprobante%20de%20pago%20 "+texto_Nombre_usu+", Documento No: "+texto_Documento_usu+", Contrato No: "+texto_NumeroContrato_usu;



        Bundle args = new Bundle();
        args.putString("valorUrl",url);


        verEstadoDeCuenta.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_menu,verEstadoDeCuenta);
        transaction.addToBackStack(null);
        transaction.commit();

    }



}

