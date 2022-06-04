package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.CarroDB;

public class Registro_de_ventas extends AppCompatActivity {

    CarroDB db;
    public Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_de_ventas);
        obtenerDatos();

    }

    public void obtenerDatos(){
        db = new CarroDB(Registro_de_ventas.this, "", null, 1);
        c = db.administracion_Auto("consultar",null);
        if(c.moveToFirst()){

            ListView ltsUser = (ListView)findViewById(R.id.ltAuti);
            final ArrayList<String> alUsers = new ArrayList<String>();

            final ArrayAdapter<String> aaUsers = new
                    ArrayAdapter<String>(Registro_de_ventas.this, android.R.layout.simple_list_item_1,
                    alUsers);
            ltsUser.setAdapter(aaUsers);

            do{
                alUsers.add(c.getString(1));
            }while(c.moveToNext());
            aaUsers.notifyDataSetChanged();
            registerForContextMenu(ltsUser);

        }else{
            Toast.makeText(Registro_de_ventas.this, "No hay Registros que mostrar ",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void registrar_auto(View v){
        Intent iagregar = new Intent(Registro_de_ventas.this, Agregar_registro.class);
        startActivity(iagregar);
    }
}

