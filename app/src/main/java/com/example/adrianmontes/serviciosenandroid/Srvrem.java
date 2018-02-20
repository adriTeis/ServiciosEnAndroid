package com.example.adrianmontes.serviciosenandroid;

import android.app.Service;
import android.content.Intent;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adrian.montes on 31/01/2018.
 */

//en esta clase extendiendo de Service puedo lanzar servicios que en este caso no estan en local, esta clase hay que declararla
//en el manifest como que es un servicio
public class Srvrem extends Service{
private final IBinder mBinder=new srvBinder();

    public class srvBinder extends Binder {
        public Srvrem getService(){
            return Srvrem.this;
        }

    }


    //con este metodo me conecto a un servicio que esta en ejecucion y me devuelve un canal de comunicacion con el
    // que es el mBinder
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public String getDate(){
        Date ahora=new Date();
        String fecha=new SimpleDateFormat("hh:mm:ss").format(ahora);
        return fecha;

    }
}

