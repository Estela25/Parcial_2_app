package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Recomendacion_auto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacion_auto);


        final EditText eEdad = (EditText)findViewById(R.id.Presupuesto);
        Button bEnviar = (Button)findViewById(R.id.btDeterminar);
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edad = eEdad.getText().toString();
                Bundle pasarDatos = new Bundle();
                pasarDatos.putString("Presupuesto",edad);
                Intent siga = new
                        Intent(Recomendacion_auto.this,Presupuesto.class);
                siga.putExtras(pasarDatos);
                startActivity(siga);
            }
        });
    }
}



