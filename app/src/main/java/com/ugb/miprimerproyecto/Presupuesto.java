 package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Presupuesto extends AppCompatActivity {
Integer estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);
        TextView Estado = (TextView)findViewById(R.id.tbMensaje);
        Button bVolver = (Button)findViewById(R.id.btVolver);
        Bundle datosRecibidos = this.getIntent().getExtras();
        String edad = datosRecibidos.getString("Presupuesto");
        estado = Integer.parseInt(edad);
        if(estado < 3000){
            Estado.setText("SE LE RECOMIENDA UN AUTO TIPO ESTANDAR");
        }else{
            Estado.setText("SE LE RECOMIENDA UN AUTO TIPO AUTOMATICO");
        }
        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Presupuesto.this,Recomendacion_auto.class);
                startActivity(vuelva);
            }
        });
    }
}

