package com.example.adrianmontes.serviciosenandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button botonIniciar;
    Button BotonParar;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonIniciar = (Button) findViewById(R.id.IniciarServicio);
        BotonParar = (Button) findViewById(R.id.PararServicio);

        botonIniciar.setOnClickListener(this);
        BotonParar.setOnClickListener(this);

        //le indico que si es la primera vez que ejecuto la app el getType va a estar vacio, eso quiere decir que el servicio
        //no se lanzo
        if(getIntent().getType()!=null){


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.IniciarServicio:
            intent=new Intent(this,SrvLocal.class);
                //le paso datos al hacer un intent para hacer eso tengo que pasarselo con el metodo
                //UriParse
               intent.setData(Uri.parse("5"));
                startService(intent);
                break;

            case R.id.PararServicio:
                stopService(intent);
                break;
        }

    }
}