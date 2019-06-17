package com.example.pantallas.globalapp20;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
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

public class NotificacionService extends Service implements Response.ErrorListener,Response.Listener<JSONObject>{

    RequestQueue rq;
    JsonRequest jrq;

    String diaFecha;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;

    private  int icono;


public void onCreate(){

   // setpendingIntent();


}
    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess){

// corre el tiempo de ejecución de la notificación
        time time = new time();
        time.execute();

    return START_STICKY;

    }


    private void  setpendingIntent(){
        Intent intent = new Intent(this,Main_validacion_existe_usuario.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main_validacion_existe_usuario.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent=stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.logog);

        builder.setContentTitle("GLOBAL PLAY");
        builder.setContentText("Su cobro para este mes ha sido cargado !");
        builder.setSubText("Gracias estimado cliente!");



        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    //hilo que hace la gestion para la ejecuión del metodo cada cierto tiempo con el fin de generar la notificación
    public void hilo(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class time extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=1; i<=3;i++){

                hilo();

            }
            return true;
        }


        public void ejecutarTiempo(){
            time time = new time();
            time.execute();

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            ejecutarTiempo();

            // line de codigo de instancia de la librería Volley


            Consulta();
            //Toast.makeText(getApplicationContext(), "cada 5 segundos", Toast.LENGTH_SHORT).show();

        }
    }

    public void Consulta(){
        rq = Volley.newRequestQueue(getApplicationContext());

        String url ="http://seller.global.net.co/prueba_android/prueba_notificacion.php";

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }




    @Override
    public void onErrorResponse(VolleyError error) {
       // Toast.makeText(getApplicationContext(), "0 conecxion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        //se van a tomar los datos de la clase usuario



        //creación de objeto tipo Json para recuperar los datos del array del archivo php

        // el objeto de tipo array se relaciona directamente con el array "datos" que se crea en el archivo php
        JSONArray jsonArray= response.optJSONArray("diafacturado");
        JSONObject jsonObject=null;


        try {
            jsonObject = jsonArray.getJSONObject(0);

            diaFecha=(jsonObject.optString("datodia"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(diaFecha.equals("01")){
            createNotificationChannel();
            setpendingIntent();
            createNotification();

        }
        else{
            //Toast.makeText(getApplicationContext(), "otro dia", Toast.LENGTH_SHORT).show();



        }


    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){


    }

}
