package com.example.pantallas.globalapp20;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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


public class Fragment_VerEstadoDeCuenta extends Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener {

    //variables indispensables para la libreriá 'Volley'
    RequestQueue rq;
    JsonRequest jrq;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //variables para recibir los argumentos del activity Main_menu_desplegable
    private String texto_Nombre_usu, texto_Documento_usu, texto_id_usu, texto_NumeroContrato_usu;

    //boton enviar Descripcion Servicios

    Button btnDescripcionServicios;
    ImageView btnEnviarComprobante;


    //resto de campos
    TextView lblnombre_usuario, lblDocumento_usu, lblNumeroContrato_usu, lblDireccionServicio, lblServicioAsignado, lblEstadoServicio, lblDeudaAnterior,
            lblCargoActual, lblDeudaPendiente, lblValorServicio, lblSubtotal, lblDescuentoPP, lblTotalConPP, lblTotalSinPP, lblDireccionSede, lblWhatsappSede,
            lblFechaFacturacion, lblFechaLimitePago, lblPeriodoFacturado, lblFechaProntoPago;


    //intanciar el constructor

    Constructor_datos_estado_cuenta datosCuenta = new Constructor_datos_estado_cuenta();

    public Fragment_VerEstadoDeCuenta() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment_VerEstadoDeCuenta newInstance(String param1, String param2) {
        Fragment_VerEstadoDeCuenta fragment = new Fragment_VerEstadoDeCuenta();
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


            //Toma de datos enviados desde la clase 'Login'
            texto_Nombre_usu = getArguments().getString("texto_Nombre_usu");
            texto_Documento_usu = getArguments().getString("texto_Documento_usu");
            texto_id_usu = getArguments().getString("texto_id_usu");
            texto_NumeroContrato_usu = getArguments().getString("texto_Contrato_usu");


        }

        //toma de contexto de la librería 'volley'
        rq = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //Asignamiento a la variable 'view' para que corra la vista
        View view = inflater.inflate(R.layout.fragment_fragment__ver_estado_de_cuenta, container, false);


        //Instanciamiento de variables asociadas a los id de su respectivo layout
        lblnombre_usuario = (TextView) view.findViewById(R.id.lbl_nombre_suscriptor);
        lblDocumento_usu = (TextView) view.findViewById(R.id.lblDocumento_susc);
        lblNumeroContrato_usu = (TextView) view.findViewById(R.id.lblContrato_susc);
        lblDireccionServicio = (TextView) view.findViewById(R.id.lblDireccionUsuario);
        lblServicioAsignado = (TextView) view.findViewById(R.id.lblServicio);
        lblEstadoServicio = (TextView) view.findViewById(R.id.lblEstadoCuenta);
        lblDeudaAnterior = (TextView) view.findViewById(R.id.lblDeudaAnterior);
        lblCargoActual = (TextView) view.findViewById(R.id.lblCargoActual);
        lblDescuentoPP = (TextView) view.findViewById(R.id.lblDescuentoProntoPago);
        lblTotalConPP = (TextView) view.findViewById(R.id.lblTotalApagarConPP);
        lblTotalSinPP = (TextView) view.findViewById(R.id.lblTotalPagarSinPP);
        lblDireccionSede = (TextView) view.findViewById(R.id.lblDireccionSucursal);
        lblWhatsappSede = (TextView) view.findViewById(R.id.lblWhatsapp);
        lblFechaFacturacion = (TextView) view.findViewById(R.id.lblFechafacturacion);
        lblFechaLimitePago = (TextView) view.findViewById(R.id.lblFechalimitePago);
        lblPeriodoFacturado = (TextView) view.findViewById(R.id.lblPeriodo);
        lblFechaProntoPago = (TextView) view.findViewById(R.id.lblAntesDe);

        lblValorServicio = (TextView) view.findViewById(R.id.lblValorServicio);
        lblSubtotal = (TextView) view.findViewById(R.id.lblSubtotal);


        lblnombre_usuario.setText(texto_Nombre_usu);
        lblDocumento_usu.setText(texto_Documento_usu);
        lblNumeroContrato_usu.setText(texto_NumeroContrato_usu);


        //boton enviar Descripcion Servicios

        btnDescripcionServicios = (Button) view.findViewById(R.id.btnDescripcionServicios);


        btnEnviarComprobante = (ImageView) view.findViewById(R.id.btnEnvioComprobante);

        // se ejecuta el metodo Consultar_EstadoCuenta_usuario
        Consultar_EstadoCuenta_usuario();


        btnDescripcionServicios.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PasoFragment_VerEstadoDeCuenta_Descripcion_productos();

            }
        });

        btnEnviarComprobante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Fragment_Enviar_comprobante();

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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //metodos propios de la librería volley que hacen la logica respectiva si la consulta se ejecutó o no en el webservise

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray1 = response.optJSONArray("datos1");
        JSONArray jsonArray2 = response.optJSONArray("datos2");
        JSONArray jsonArray3 = response.optJSONArray("datos3");
        JSONArray jsonArray4 = response.optJSONArray("datos4");

        JSONArray jsonArray5 = response.optJSONArray("diafacturado");

        JSONArray jsonArray6 = response.optJSONArray("dialimitedepago");

        JSONArray jsonArray7 = response.optJSONArray("datomesanio");

        JSONObject jsonObject1 = null;
        JSONObject jsonObject2 = null;
        JSONObject jsonObject3 = null;
        JSONObject jsonObject4 = null;
        JSONObject jsonObject5 = null;
        JSONObject jsonObject6 = null;
        JSONObject jsonObject7 = null;

        try {

            //se le pasa el numero del contrato a los constructores de la clase "Contrato_usu" en su respectivo campo

            //recibe datos de php en array "'Datos1'"
            jsonObject1 = jsonArray1.getJSONObject(0);

            datosCuenta.setDireccion(jsonObject1.optString("cont_direccion_serv"));
            datosCuenta.setBarrio(jsonObject1.optString("cont_barrio"));
            datosCuenta.setTvAnaloga(jsonObject1.optString("cont_tv_analogica"));
            datosCuenta.setTvDigital(jsonObject1.optString("cont_tv_digital"));
            datosCuenta.setInternet(jsonObject1.optString("cont_internet"));
            datosCuenta.setValorServicio(jsonObject1.optString("cont_valor_basico_mes"));
            datosCuenta.setEstadoServicio(jsonObject1.optString("est_serv_nombre"));


            //recibe datos de php en array "'Datos2'"
            jsonObject2 = jsonArray2.getJSONObject(0);

            datosCuenta.setValorProntoPago(jsonObject2.optString("prod_valor_pronto_pago"));

            //recibe datos de php en array "'Datos3'"
            jsonObject3 = jsonArray3.getJSONObject(0);

            datosCuenta.setDeudaAnterior(jsonObject3.optString("est_cta_saldo_anterior"));
            datosCuenta.setCargoActual(jsonObject3.optString("est_cta_haber"));
            datosCuenta.setDeudaPendiente(jsonObject3.optString("est_cta_saldo_actual"));

            //recibe datos de php en array "'Datos4'"


            jsonObject4 = jsonArray4.getJSONObject(0);

            datosCuenta.setDireccionSede(jsonObject4.optString("sed_direccion"));
            datosCuenta.setWhatsappSede(jsonObject4.optString("sed_telefono_2"));


            //Para los datos de las fechas estáticas

            //recibe datos de php en array "'diafacturado'"

            jsonObject5 = jsonArray5.getJSONObject(0);
            datosCuenta.setDiaFacturacion(jsonObject5.optString("diaF"));

            //recibe datos de php en array "'dialimitedepago'"
            jsonObject6 = jsonArray6.getJSONObject(0);
            datosCuenta.setDiaLimitePago(jsonObject6.optString("diaLimite"));

            //recibe datos de php en array "'datomesanio'"
            jsonObject7 = jsonArray7.getJSONObject(0);
            datosCuenta.setMesAnioServidor(jsonObject7.optString("datoMesAnio"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Toast.makeText(getContext(),"ok consulta"+datosCuenta.getValorServicio(), Toast.LENGTH_SHORT).show();
        CargueDeDatosAEstadoCuenta();


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//metodo para la muestra del servicio asociado al número del contrato
    private void CargueDeDatosAEstadoCuenta(){


        //Dirección
        lblDireccionServicio.setText(datosCuenta.getDireccion()+" "+datosCuenta.getBarrio());
        //Servicio Asignado

        if(datosCuenta.getTvAnaloga().equals("1") && datosCuenta.getTvDigital().equals("1")&& datosCuenta.getInternet().equals("1")){

            lblServicioAsignado.setText("TV analoga + TV digital + Internet");
            btnDescripcionServicios.setVisibility(View.VISIBLE);

        }
        else if(datosCuenta.getTvAnaloga().equals("1") && datosCuenta.getTvDigital().equals("1")&& datosCuenta.getInternet().equals("0")){
            lblServicioAsignado.setText("TV analoga + TV digital");
            btnDescripcionServicios.setVisibility(View.VISIBLE);

        }
        else if(datosCuenta.getTvAnaloga().equals("1") && datosCuenta.getTvDigital().equals("0")&& datosCuenta.getInternet().equals("1")){
            lblServicioAsignado.setText("TV analoga + Internet");
            btnDescripcionServicios.setVisibility(View.VISIBLE);

        }

        else if(datosCuenta.getTvAnaloga().equals("0") && datosCuenta.getTvDigital().equals("1")&& datosCuenta.getInternet().equals("1")){
            lblServicioAsignado.setText("TV digital + Internet");
            btnDescripcionServicios.setVisibility(View.VISIBLE);

        }
        else if(datosCuenta.getTvAnaloga().equals("1") && datosCuenta.getTvDigital().equals("0")&& datosCuenta.getInternet().equals("1")){
            lblServicioAsignado.setText("Internet");

        }
        else if(datosCuenta.getTvAnaloga().equals("1") && datosCuenta.getTvDigital().equals("0")&& datosCuenta.getInternet().equals("0")){
            lblServicioAsignado.setText("TV analoga");

        }
        else if(datosCuenta.getTvAnaloga().equals("0") && datosCuenta.getTvDigital().equals("1")&& datosCuenta.getInternet().equals("0")){
            lblServicioAsignado.setText("TV digital");

        }
        else if(datosCuenta.getTvAnaloga().equals("0") && datosCuenta.getTvDigital().equals("0")&& datosCuenta.getInternet().equals("1")){
            lblServicioAsignado.setText("Internet");

        }

        else {
            Toast.makeText(getContext(),"Error de servicio ", Toast.LENGTH_SHORT).show();

        }

        //Estado servicio

        lblEstadoServicio.setText(datosCuenta.getEstadoServicio());
        //Deuda Anterior
        lblDeudaAnterior.setText("$ "+datosCuenta.getDeudaAnterior());

        //Cargo Actual
        lblCargoActual.setText("$ "+datosCuenta.getCargoActual());

        // Deuda Actual
    //        lblDeudaPendiente.setText("$ "+datosCuenta.getDeudaPendiente());

        //Valor del servicio

       lblValorServicio.setText("$ "+datosCuenta.getValorServicio());

        //Subtotal

        lblSubtotal.setText("$ "+datosCuenta.getDeudaPendiente());

        //Descuento Pronto Pago
        lblDescuentoPP.setText("$ "+datosCuenta.getValorProntoPago());
        //Total Con Pronto pago

        int ValorPP= Integer.parseInt(datosCuenta.getValorProntoPago());
        int ValorSubtotal = Integer.parseInt(datosCuenta.getDeudaPendiente());

        int ValorTotalConPP = ValorSubtotal-ValorPP;

        String ValorTotalConPPString = String.valueOf(ValorTotalConPP);

        lblTotalConPP.setText("$ "+ValorTotalConPPString);

        //Total Sin Pronto Pago

        lblTotalSinPP.setText("$ "+datosCuenta.getDeudaPendiente());

        //Direccion de sucursal

        lblDireccionSede.setText(datosCuenta.getDireccionSede());

        //Numero de Whatsapp Sede
       lblWhatsappSede.setText(datosCuenta.getWhatsappSede());


        //Para la fecha

        //fecha de facturación
        lblFechaFacturacion.setText(datosCuenta.getDiaFacturacion()+datosCuenta.getMesAnioServidor());
        //Toast.makeText(getContext(),"dia fecha"+datosCuenta.getDiaFacturacion()+datosCuenta.getMesAnioServidor(), Toast.LENGTH_SHORT).show();

        //fecha límite de pago

        lblFechaLimitePago.setText(datosCuenta.getDiaLimitePago()+datosCuenta.getMesAnioServidor());

        //Periodo Facturado
        lblPeriodoFacturado.setText(datosCuenta.getDiaFacturacion()+datosCuenta.getMesAnioServidor()+" al "+"30-31/"+datosCuenta.getMesAnioServidor());

        //Fecha pronto pago
        lblFechaProntoPago.setText(datosCuenta.getDiaLimitePago()+datosCuenta.getMesAnioServidor());



    }

// metodo para pasar al fragment 'Enviar_comprobante'

    private void Fragment_Enviar_comprobante(){
        Fragment_enviar_comprovante FragEnviarComprobante = new Fragment_enviar_comprovante();

        Bundle args = new Bundle();
        args.putString("texto_Nombre_usu",texto_Nombre_usu);
        args.putString("texto_Documento_usu",texto_Documento_usu);
        args.putString("texto_Contrato_usu",texto_NumeroContrato_usu);
        args.putString("NumeroWhatsappSede",datosCuenta.getWhatsappSede());

        FragEnviarComprobante.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_menu,FragEnviarComprobante);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    // metodo para pasar al fragment 'Descripción de producto'

    private void PasoFragment_VerEstadoDeCuenta_Descripcion_productos(){


        Fragment_VerEstadoDeCuenta_Descripcion_productos Descripcion_productos = new  Fragment_VerEstadoDeCuenta_Descripcion_productos();


        Bundle args = new Bundle();
        args.putString("texto_Contrato_usu",texto_NumeroContrato_usu);


        Descripcion_productos.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_menu,Descripcion_productos);
        transaction.addToBackStack(null);
        transaction.commit();



    }

    private void Consultar_EstadoCuenta_usuario(){
        //para conexion remota se coloca el dns, y para local la ip
        String url ="http://seller.global.net.co/prueba_android/consulta_datos_estadoCuenta_asignadoAcontrato.php?Nu_cont="+texto_NumeroContrato_usu;

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);



    }
}
