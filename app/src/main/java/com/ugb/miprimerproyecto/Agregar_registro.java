package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.CarroDB;

public class Agregar_registro extends AppCompatActivity {
    Button btn,btn1;
    EditText txt,txt1,txt2,txt3;
    CarroDB auti;
    String  idVenta,accion="nuevo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_registro);

        auti = new CarroDB(getApplicationContext(),"",null,1);
        btn1 = findViewById(R.id.btnVolver);
        btn1.setOnClickListener(v->{
            mostrarVistaPrincipal();
        });

        btn = findViewById(R.id.BTNRegistrar);
        btn.setOnClickListener(v->{
            txt = findViewById(R.id.tMarc);
            String Marca = txt.getText().toString();

            txt1 = findViewById(R.id.TPrec);
            String Precio= txt1.getText().toString();

            txt2 = findViewById(R.id.TFecha);
            String Fecha= txt2.getText().toString();

            txt3= findViewById(R.id.TVendedor);
            String Vendedor = txt3.getText().toString();

            String[] datos = {idVenta,Marca,Precio,Fecha,Vendedor,};
            auti.administracion_Auto(accion,datos);
            mostrarMsgToast("Informacion guardada con exito.");

            mostrarVistaPrincipal();
        });
        mostrarDatosAuti();
    }
    private void mostrarDatosAuti() {
        try{
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString("accion");
            if(accion.equals("modificar")){
                String[] datos = recibirParametros.getStringArray("datos");

                idVenta = datos[0];

                txt = findViewById(R.id.tMarc);
                txt.setText(datos[1]);

                txt1 = findViewById(R.id.TPrec);
                txt1.setText(datos[2]);

                txt2 = findViewById(R.id.TFecha);
                txt2.setText(datos[3]);

                txt3 = findViewById(R.id.TVendedor);
                txt3.setText(datos[4]);


            }
        }catch (Exception e){
            mostrarMsgToast(e.getMessage());
        }
    }
    private void mostrarVistaPrincipal(){
        Intent iprincipal = new Intent(getApplicationContext(), Registro_de_ventas.class);
        startActivity(iprincipal);
    }
    private void mostrarMsgToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}