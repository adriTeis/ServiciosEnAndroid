package com.example.adrianmontes.serviciosenandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adrian.montes on 31/01/2018.
 */

public class SrvLocal extends Service {
    //creamos un archivo de audio
    MediaPlayer reproductor;
    int i;
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        //en el guardamos un archivo .mp3
        super.onCreate();
        reproductor=MediaPlayer.create(this,R.raw.musica);
        reproductor.setLooping(true);
        reproductor.setVolume(100,100);



    }

    //aqui ejecuto el servicio que es aparte de la APP
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


          reproductor.start();
//        Log.i("Services","Iniciando Servicio");
//        int numMsg=Integer.parseInt(intent.getDataString());
//
//        for (i=0;i<numMsg;i++){
//            Toast.makeText(this,"Mensage"+i,Toast.LENGTH_SHORT).show();
//
//        }
        //De esta manera detenemos el servicio de manera manual
//        stopSelf();


        //Hacemos una notificacion
        Intent notificacion=new Intent(this,MainActivity.class);
        notificacion.setType("srv");
        notificacion.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,notificacion,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Service 24");
        builder.setContentText("Servicio Local");
        builder.setContentIntent(pendingIntent);
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        //le decimos que si se cierra la actividad que se detenga al musica
        super.onDestroy();
        if(reproductor.isPlaying()) reproductor.stop();
        reproductor.release();
        reproductor=null;
        notificationManager.cancel(0);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
