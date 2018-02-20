package com.example.adrianmontes.serviciosenandroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//en este metodo main me voy a conectar a un servicio externo en vez de uno propio en local como escuchar musica
//creo un avarible de la clase que tenemos extendiendo de Service
public class Main2Activity extends AppCompatActivity {

    //Se crea una clase que va a actuar de servicio donde podemos acceder a sus metodos desde cualquier aplicacion
    Button btnService;
    Srvrem mService; //para comunicarnos con el servicio
    boolean mBound=false;
    // para enlazarte al servicio me pide un objeto de la clase conection que se le pasa al bind servicio
    private ServiceConnection mConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("Paaaaaaa","por aqui");
            Srvrem.srvBinder binder=(Srvrem.srvBinder) service;
            //en mService tengo el enlace al servicio
            mService=binder.getService();
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound=false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnService= (Button) findViewById(R.id.IniciarSrv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Paaaaaaa","por aqui onStart");
        Intent intent= new Intent(this,Srvrem.class);
        //bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        //Toast.makeText(this,"dfs",Toast.LENGTH_SHORT).show();
        bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public void lanzarServicio(View View){

        Toast.makeText(this,"Bton lanzado",Toast.LENGTH_SHORT).show();
        if(mBound){
            String dat=mService.getDate();
            Toast.makeText(this,dat,Toast.LENGTH_SHORT).show();

        }
    }
    public void PararServicio(View view) {
        mService.stopSelf();

    }



}








