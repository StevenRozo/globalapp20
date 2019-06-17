package com.example.pantallas.globalapp20;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pantallas.globalapp20.adapter.Estado_de_cuenta_completo_adapter;
import com.example.pantallas.globalapp20.entidades.Estado_de_cuenta_completo;
import com.example.pantallas.globalapp20.entidades.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ver_estado_de_cuenta_completo extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private String texto_NumeroContrato_usu;

    RecyclerView RecyclerCuenta_completa;
    ArrayList<Estado_de_cuenta_completo> listaEstadoCuentaCompleto;

    ProgressDialog dialog;

    // RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public Fragment_ver_estado_de_cuenta_completo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            texto_NumeroContrato_usu = getArguments().getString("texto_Contrato_usu");

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_fragment_ver_estado_de_cuenta_completo, container, false );

        listaEstadoCuentaCompleto = new ArrayList<>(  );

        RecyclerCuenta_completa =(RecyclerView) view.findViewById(R.id.idRecyclerCuenta_completa);
        RecyclerCuenta_completa.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RecyclerCuenta_completa.setHasFixedSize(true);


        //se inicia la función
        cargarWebService(texto_NumeroContrato_usu);
        return view;
    }

    private void cargarWebService(String numeroContrato) {


        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Consultando Historial");
        dialog.show();


        String url="http://seller.global.net.co/prueba_android/consulta_estado_cuenta_completo.php?contrato="+numeroContrato;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        String concepto;
        Estado_de_cuenta_completo estado_de_cuenta_completo=null;

        JSONArray json=response.optJSONArray("datos");

        try {

            for (int i=0;i<json.length();i++){
                estado_de_cuenta_completo=new Estado_de_cuenta_completo();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                estado_de_cuenta_completo.setEst_cta_fecha_transacc(jsonObject.optString("est_cta_fecha_transacc"));
                estado_de_cuenta_completo.setCon_tran_nombre(jsonObject.optString("con_tran_nombre"));
                estado_de_cuenta_completo.setEst_cta_no_comprobante(jsonObject.optString("est_cta_no_transaccion"));

                concepto = (jsonObject.optString("con_tran_nombre"));
                estado_de_cuenta_completo.setEst_cta_observacion(jsonObject.optString("est_cta_observacion"));

                estado_de_cuenta_completo.setEst_cta_saldo_anterior(jsonObject.optString("est_cta_saldo_anterior"));
                estado_de_cuenta_completo.setEst_cta_haber(jsonObject.optString("est_cta_haber"));
                estado_de_cuenta_completo.setEst_cta_debe(jsonObject.optString("est_cta_debe"));

                estado_de_cuenta_completo.setEst_cta_saldo_actual(jsonObject.optString("est_cta_saldo_actual"));


                if(concepto.equals("COBRO POR SERVICIO") || concepto.equals("DCTO - PRONTO PAGO CAJA") || concepto.equals("SALDO INICIAL") || concepto.equals("PAGO EN EFECTIVO")
                        || concepto.equals("RECONEXION") || concepto.equals("PUNTO ADICIONAL") || concepto.equals("AJUSTE DIAS DE INSTALACION") || concepto.equals("RETIRO ANTICIPADO")
                        || concepto.equals("COBRO POR INSTALACION") || concepto.equals("VENTA DE EQUIPOS") || concepto.equals("TRASLADO")|| concepto.equals("DCTO - FALLA TECNICA")
                        || concepto.equals("DCTO - FIDELIZACIÓN") || concepto.equals("DCTO - PRONTO PAGO ADMIN") || concepto.equals("AJUSTE A FACTURACION") || concepto.equals("PRORRATEO")
                        || concepto.equals("IMPUESTOS")){
                    estado_de_cuenta_completo.setCon_tran_nombre(jsonObject.optString("con_tran_nombre"));

                }
                else{
                    estado_de_cuenta_completo.setCon_tran_nombre( "CONSIGNACIÓN" );
                }

                listaEstadoCuentaCompleto.add(estado_de_cuenta_completo);
            }
            dialog.hide();
            Estado_de_cuenta_completo_adapter adapter=new Estado_de_cuenta_completo_adapter(listaEstadoCuentaCompleto, getContext());
            RecyclerCuenta_completa.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        dialog.hide();
        Log.d("ERROR: ", error.toString());

    }
}
