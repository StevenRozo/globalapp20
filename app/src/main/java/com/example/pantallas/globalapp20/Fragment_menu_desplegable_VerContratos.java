package com.example.pantallas.globalapp20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_menu_desplegable_VerContratos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_menu_desplegable_VerContratos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_menu_desplegable_VerContratos extends Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener,Fragment_VerEstadoDeCuenta.OnFragmentInteractionListener{


    RequestQueue rq;
    JsonRequest jrq;


    String idContUsu;

    Spinner SpnContratos;
    TextView lblContratoPrueba,lblNombreUsuario,lblCedulaUsuario,lblSeleccione;
    Button btnVerEstadoCuentaUsu,btnVerEstadoCuentaUsu_completo,btnsalir;

    Contrato_usu contUsu = new Contrato_usu();

    private String  textoNumeroDeContrato,texto_Nombre_usu,texto_Documento_usu,texto_id_usu;
    //variable para el tipo de letra
    private Typeface FuenteLetra;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner SpinnerContratos;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_menu_desplegable_VerContratos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment_menu_desplegable_VerContratos newInstance(String param1, String param2) {
        Fragment_menu_desplegable_VerContratos fragment = new Fragment_menu_desplegable_VerContratos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            texto_Nombre_usu = getArguments().getString("texto_Nombre_usu");
            texto_Documento_usu=getArguments().getString("texto_Documento_usu");
            texto_id_usu=getArguments().getString("texto_id_usu");




        }


        rq = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Consultar_contratos_usuario();

       View view= inflater.inflate(R.layout.fragment_fragment_menu_desplegable__ver_contratos, container, false);

        //variables que toman la fuente
        String fuente1 ="fuentes/quenda.otf";
        String fuente2 ="fuentes/massa.ttf";

        this.FuenteLetra =Typeface.createFromAsset(getActivity().getAssets(),fuente2);

       SpnContratos =(Spinner) view.findViewById(R.id.SpnContratosUsu);


       lblContratoPrueba=(TextView) view.findViewById(R.id.lblContratoPrueba);
       lblNombreUsuario=(TextView) view.findViewById(R.id.lblNombreUsuario);
       lblNombreUsuario.setTypeface(FuenteLetra);
       lblCedulaUsuario=(TextView) view.findViewById(R.id.lblCedulaUsuario);
       lblCedulaUsuario.setTypeface(FuenteLetra);
       lblSeleccione=(TextView) view.findViewById(R.id.lblSeleccione);
       lblSeleccione.setTypeface(FuenteLetra);


       lblNombreUsuario.setText(texto_Nombre_usu);
       lblCedulaUsuario.setText(texto_Documento_usu);


       btnVerEstadoCuentaUsu =(Button) view.findViewById(R.id.btnVerEstadoCuentaUsu);
      // btnVerEstadoCuentaUsu.setTypeface(FuenteLetra);

        btnVerEstadoCuentaUsu_completo=(Button) view.findViewById( R.id.btnVerEstadoCuentaUsu_completo );

       btnsalir =(Button) view.findViewById(R.id.btnSalir);
       btnsalir.setTypeface(FuenteLetra);






        btnVerEstadoCuentaUsu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PasoFragmentVerEstadoCuenta();


            }
        });

        btnVerEstadoCuentaUsu_completo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PasoFragmentVerEstadoCuenta_completo();


            }
        });


        btnsalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.exit(0);


            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo hacer la consulta", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {



        JSONArray jsonArray= response.optJSONArray("datos");
        JSONObject jsonObject=null;
       // Toast.makeText(getContext(), "NO SE HIZO EL INGRESO DE DATOS", Toast.LENGTH_SHORT).show();

        try {

            //se le pasa el numero del contrato a los constructores de la clase "Contrato_usu" en su respectivo campo

            jsonObject = jsonArray.getJSONObject(0);

            contUsu.setCont_id_usu1(jsonObject.optString("cont_id"));

            jsonObject = jsonArray.getJSONObject(1);
            contUsu.setCont_id_usu2(jsonObject.optString("cont_id"));

            jsonObject = jsonArray.getJSONObject(2);
            contUsu.setCont_id_usu3(jsonObject.optString("cont_id"));

            jsonObject = jsonArray.getJSONObject(3);
            contUsu.setCont_id_usu4(jsonObject.optString("cont_id"));

            jsonObject = jsonArray.getJSONObject(4);
            contUsu.setCont_id_usu5(jsonObject.optString("cont_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CargarSpinner();

      //  Toast.makeText(getContext(),"1: "+contUsu.getCont_id_usu1()+"  2: "+contUsu.getCont_id_usu2(), Toast.LENGTH_SHORT).show();




    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void PasoFragmentVerEstadoCuenta(){


        Fragment_VerEstadoDeCuenta verEstadoDeCuenta = new Fragment_VerEstadoDeCuenta();

        Bundle args = new Bundle();
        args.putString("texto_Nombre_usu",texto_Nombre_usu);
        args.putString("texto_Documento_usu",texto_Documento_usu);
        args.putString("texto_Contrato_usu",textoNumeroDeContrato);

        verEstadoDeCuenta.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_menu,verEstadoDeCuenta);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void PasoFragmentVerEstadoCuenta_completo(){


        Fragment_ver_estado_de_cuenta_completo ver_estado_de_cuenta_completo = new Fragment_ver_estado_de_cuenta_completo();


        Bundle args = new Bundle();
        args.putString("texto_Contrato_usu",textoNumeroDeContrato);

        ver_estado_de_cuenta_completo.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_menu,ver_estado_de_cuenta_completo);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void CargarSpinner(){

        int numeroContratos1=0,numeroContratos2=0,numeroContratos3=0,numeroContratos4=0,numeroContratos5=0,SumanumeroContratos=0;

        ArrayList<String> comboContratosList = new ArrayList<>();




        String contrato1= contUsu.getCont_id_usu1();
        String contrato2= contUsu.getCont_id_usu2();
        String contrato3= contUsu.getCont_id_usu3();
        String contrato4= contUsu.getCont_id_usu4();
        String contrato5= contUsu.getCont_id_usu5();



        comboContratosList.add("NÚMERO DE CONTRATO: ▼ ");

        if(contrato1 != null){
            numeroContratos1=1;
        }
        else if(contrato2 != null){

            numeroContratos2=1;
        }
        else if(contrato3 != null){

            numeroContratos3=1;
        }
        else if(contrato4 != null){

            numeroContratos4=1;
        }
        else if(contrato5 != null){

            numeroContratos5=1;
        }


        SumanumeroContratos = numeroContratos1+numeroContratos2+numeroContratos3+numeroContratos4+numeroContratos5;



        for ( int i=1; i==SumanumeroContratos;i++){

            if(contrato1 != null){

                comboContratosList.add(contrato1);
            }
            if(contrato2 != null){

                comboContratosList.add(contrato2);
            }
            if(contrato3 != null){

                comboContratosList.add(contrato3);
            }
            if(contrato4 != null){

                comboContratosList.add(contrato4);
            }
            if(contrato5 != null){

                comboContratosList.add(contrato5);
            }

        }

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,comboContratosList);




        SpnContratos.setAdapter(adapter);

        SpnContratos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getContext(), "Seleccionado "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                 textoNumeroDeContrato= parent.getItemAtPosition(position).toString();

                if(!textoNumeroDeContrato.equals("NÚMERO DE CONTRATO: ▼ ") ){

                    btnVerEstadoCuentaUsu.setEnabled(true);
                    btnVerEstadoCuentaUsu_completo.setEnabled( true );
                    lblContratoPrueba.setText(textoNumeroDeContrato);

                }
                else {
                    btnVerEstadoCuentaUsu.setEnabled(false);
                    btnVerEstadoCuentaUsu_completo.setEnabled( false );

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void Consultar_contratos_usuario(){
        //para conexion remota se coloca el dns, y para local la ip
        String url ="http://seller.global.net.co/prueba_android/consulta_contratos_usu.php?per_id="+texto_id_usu;

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);



    }


}
