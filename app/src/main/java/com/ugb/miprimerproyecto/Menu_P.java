package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_P extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_p);

        Button bInventario = (Button)findViewById(R.id.btnInventario);
        bInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Menu_P.this,Mostrar_Auto.class);
                startActivity(vuelva);
            }
        });

        Button bRecomendacion= (Button)findViewById(R.id.btnRecome);
        bRecomendacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Menu_P.this,Recomendacion_auto.class);
                startActivity(vuelva);
            }
        });

        Button bRegistro = (Button)findViewById(R.id.btnRegistro);
        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Menu_P.this,Registro_de_ventas.class);
                startActivity(vuelva);
            }
        });

        Button bAyuda = (Button)findViewById(R.id.btnAyuda);
        bAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Menu_P.this,Ayuda.class);
                startActivity(vuelva);
            }
        });

        Button bAcercade = (Button)findViewById(R.id.btnAcerca);
        bAcercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vuelva = new
                        Intent(Menu_P.this,Acerca_de.class);
                startActivity(vuelva);
            }
        });

        Button bSalir  = (Button)findViewById(R.id.btnRegresar);
        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}