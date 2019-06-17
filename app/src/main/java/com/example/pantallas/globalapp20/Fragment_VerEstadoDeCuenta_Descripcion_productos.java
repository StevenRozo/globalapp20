package com.example.pantallas.globalapp20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
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

import java.util.ArrayList;


public class Fragment_VerEstadoDeCuenta_Descripcion_productos extends  Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener  {

    RequestQueue rq;
    JsonRequest jrq;


    private String texto_NumeroContrato_usu;

    TextView lblservicio_Descripcion,lblservicio_valor,lblservicio_DescuentoXcombo,lbl_TotalServicio;

    Spinner SpnServicio;


    //Variables para la operación del valor de los productos
    int ValorProducto,ValorDescuento,ValorTotal;
    String ValorTotalTexto;


    Descripcion_producto DesProducto = new Descripcion_producto();

    public Fragment_VerEstadoDeCuenta_Descripcion_productos() {
        // Required empty public constructor
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            texto_NumeroContrato_usu =getArguments().getString("texto_Contrato_usu");

        }
        rq = Volley.newRequestQueue(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment__ver_estado_de_cuenta__descripcion_productos, container, false);

        lblservicio_Descripcion=(TextView) view.findViewById(R.id.lblservicio_Descripcion);
        lblservicio_valor=(TextView) view.findViewById(R.id.lblservicio_valor);
        lblservicio_DescuentoXcombo=(TextView) view.findViewById(R.id.lblservicio_DescuentoXcombo);
        lbl_TotalServicio=(TextView) view.findViewById(R.id.lbl_TotalServicio);

        SpnServicio =(Spinner) view.findViewById(R.id.SpnServicio);

        //ejecución del metodo
        Consultar_DescripcionProducto();


        return view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {


        //Trayendo los resultados del Json que retorna php
        JSONArray jsonArray1= response.optJSONArray("datos1");
        JSONArray jsonArray2= response.optJSONArray("datos2");
        JSONArray jsonArray3= response.optJSONArray("datos3");

        JSONObject jsonObject1=null;
        JSONObject jsonObject2=null;
        JSONObject jsonObject3=null;

        // Toast.makeText(getContext(), "NO SE HIZO EL INGRESO DE DATOS", Toast.LENGTH_SHORT).show();



        try {

            //recibe datos de php en array "'Datos1'"

            jsonObject1 = jsonArray1.getJSONObject(0);

            DesProducto.setNombreProducto1(jsonObject1.optString("prod_nombre"));
            DesProducto.setValorProducto1(jsonObject1.optString("prod_valor"));
            DesProducto.setDescuentoProducto1(jsonObject1.optString("prod_descuento_x_combo"));

            //recibe datos de php en array "'Datos2'"
            jsonObject2 = jsonArray2.getJSONObject(0);


            DesProducto.setNombreProducto2(jsonObject2.optString("prod_nombre"));
            DesProducto.setValorProducto2(jsonObject2.optString("prod_valor"));
            DesProducto.setDescuentoProducto2(jsonObject2.optString("prod_descuento_x_combo"));

            //recibe datos de php en array "'Datos3'"

            // si se encuentra algo en un objeto Json Datos3 se ejecuta el siguiente fragmento
            if(jsonArray3 != null){


                jsonObject3 = jsonArray3.getJSONObject(0);

                DesProducto.setNombreProducto3(jsonObject3.optString("prod_nombre"));
                DesProducto.setValorProducto3(jsonObject3.optString("prod_valor"));
                DesProducto.setDescuentoProducto3(jsonObject3.optString("prod_descuento_x_combo"));
            }

            // si nay nada queda vacío
            else{


                DesProducto.setNombreProducto3("");
                DesProducto.setValorProducto3("");
                DesProducto.setDescuentoProducto3("");

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    // Se ejecuta el metodo CargarSpinner
        CargarSpinner();
    }


    //Metodo que carga el combobox con los datos de los nombres de los productos
    private void CargarSpinner(){



        ArrayList<String> combonumeroServiciosList = new ArrayList<>();


        String Servicio1= DesProducto.getNombreProducto1();
        String Servicio2= DesProducto.getNombreProducto2();
        String Servicio3= DesProducto.getNombreProducto3();

        combonumeroServiciosList.add("SERVICIO: ▼ ");

        if(!Servicio1.equals("null")){

            combonumeroServiciosList.add(Servicio1);
        }
        if(!Servicio2.equals("null")){


            combonumeroServiciosList.add(Servicio2);
        }
        if(!Servicio3.equals("null")){


            combonumeroServiciosList.add(Servicio3);
        }



        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,combonumeroServiciosList);




        SpnServicio.setAdapter(adapter);

        SpnServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Selecciona el numero de la posición del spinner

                int idItemSelected = parent.getId();

                //Segun la posicion del producto se aplica la lógica pertinente para cargar los valos a los TextView (Nombre,Valor,DescuetoXCombo y Total del Servicio)
                switch(idItemSelected){
                    case R.id.SpnServicio:
                        if(position ==1){
                            lblservicio_Descripcion.setText(DesProducto.getNombreProducto1());
                            lblservicio_valor.setText(" $ "+DesProducto.getValorProducto1());
                            lblservicio_DescuentoXcombo.setText(" $ "+DesProducto.getDescuentoProducto1());

                            //Total servicio Valor del producto menos descuentoXcombo
                            ValorProducto = Integer.parseInt(DesProducto.getValorProducto1());
                            ValorDescuento= Integer.parseInt(DesProducto.getDescuentoProducto1());
                            ValorTotal= ValorProducto-ValorDescuento;
                            ValorTotalTexto = Integer.toString(ValorTotal);

                            lbl_TotalServicio.setText(" $ "+ValorTotalTexto);

                        }
                        else  if(position ==2){
                            lblservicio_Descripcion.setText(DesProducto.getNombreProducto2());
                            lblservicio_valor.setText(" $ "+DesProducto.getValorProducto2());
                            lblservicio_DescuentoXcombo.setText(" $ "+DesProducto.getDescuentoProducto2());

                            //Total servicio Valor del producto menos descuentoXcombo
                            ValorProducto = Integer.parseInt(DesProducto.getValorProducto2());
                            ValorDescuento= Integer.parseInt(DesProducto.getDescuentoProducto2());
                            ValorTotal= ValorProducto-ValorDescuento;
                            ValorTotalTexto = Integer.toString(ValorTotal);

                            lbl_TotalServicio.setText(" $ "+ValorTotalTexto);

                        }
                        else  if(position ==3){

                            if(!DesProducto.getNombreProducto3().equals("")){
                                lblservicio_Descripcion.setText(DesProducto.getNombreProducto3());

                            }
                            else {
                                lblservicio_Descripcion.setText("");
                            }
                            if(!DesProducto.getValorProducto3().equals("")){
                                lblservicio_valor.setText(" $ "+DesProducto.getValorProducto3());
                            }
                            else {
                                lblservicio_valor.setText("  ");
                            }

                           if(!DesProducto.getDescuentoProducto3().equals("")){
                               lblservicio_DescuentoXcombo.setText(" $ "+DesProducto.getDescuentoProducto3());

                           }
                           else {
                               lblservicio_DescuentoXcombo.setText(" ");

                           }

                           if(!DesProducto.getValorProducto3().equals("") && !DesProducto.getDescuentoProducto3().equals("")){
                               //Total servicio Valor del producto menos descuentoXcombo
                               ValorProducto = Integer.parseInt(DesProducto.getValorProducto3());
                               ValorDescuento= Integer.parseInt(DesProducto.getDescuentoProducto3());
                               ValorTotal= ValorProducto-ValorDescuento;
                               ValorTotalTexto = Integer.toString(ValorTotal);

                               lbl_TotalServicio.setText(" $ "+ValorTotalTexto);

                           }
                           else {
                               lbl_TotalServicio.setText(" ");
                           }


                        }

                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



// Metodo que hace la consulta a la BD trayendo los datos de los productos
    private void Consultar_DescripcionProducto(){
        //para conexion remota se coloca el dns, y para local la ip
        String url ="http://seller.global.net.co/prueba_android/consulta_descripcion_producto.php?contrato="+texto_NumeroContrato_usu;

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);




    }
}
