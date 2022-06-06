package com.ugb.miprimerproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Database.Auto;

public class Mostrar_Auto extends AppCompatActivity {

    FloatingActionButton btn;
    DB miBD;
    ListView ltsAmigos;
    Cursor datosAmigosCursor = null;
    ArrayList<Auto> amigosArrayList=new ArrayList<Auto>();
    ArrayList<Auto> amigosArrayListCopy=new ArrayList<Auto>();
    Auto misAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_auto);

        btn = findViewById(R.id.btnAgregarAuto);
        btn.setOnClickListener(v->{
            agregarAmigos("nuevo", new String[]{});
        });
        obtenerDatosAmigos();
        buscarAmigos();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_amigos, menu);

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
        datosAmigosCursor.moveToPosition(adapterContextMenuInfo.position);
        menu.setHeaderTitle(datosAmigosCursor.getString(1));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.mnxAgregar:
                    agregarAmigos("nuevo", new String[]{});
                    break;
                case R.id.mnxModificar:
                    String[] datos = {
                            datosAmigosCursor.getString(0),
                            datosAmigosCursor.getString(1),
                            datosAmigosCursor.getString(2),
                            datosAmigosCursor.getString(3),
                            datosAmigosCursor.getString(4),
                            datosAmigosCursor.getString(5),
                            datosAmigosCursor.getString(6)
                    };
                    agregarAmigos("modificar", datos);
                    break;
                case R.id.mnxEliminar:
                    eliminarAmigo();
                    break;
            }
        }catch (Exception ex){
            mostrarMsgToask(ex.getMessage());
        }
        return super.onContextItemSelected(item);
    }

    private void eliminarAmigo(){
        try {
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(Mostrar_Auto.this);
            confirmacion.setTitle("Esta seguro de eliminar el registro?");
            confirmacion.setMessage(datosAmigosCursor.getString(1));
            confirmacion.setPositiveButton("Si", (dialog, which) -> {
                miBD = new DB(getApplicationContext(), "", null, 1);
                datosAmigosCursor = miBD.administracion_Auto("eliminar", new String[]{datosAmigosCursor.getString(0)});//idAmigo
                obtenerDatosAmigos();
                mostrarMsgToask("Registro Eliminado con exito...");
                dialog.dismiss();//cerrar el cuadro de dialogo
            });
            confirmacion.setNegativeButton("No", (dialog, which) -> {
                mostrarMsgToask("Eliminacion cancelada por el usuario...");
                dialog.dismiss();
            });
            confirmacion.create().show();
        }catch (Exception ex){
            mostrarMsgToask(ex.getMessage());
        }
    }

    private void buscarAmigos() {
        TextView tempVal = findViewById(R.id.txtBuscarAuto);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    amigosArrayList.clear();
                    if( tempVal.getText().toString().trim().length()<1 ){
                        amigosArrayList.addAll(amigosArrayListCopy);
                    } else {
                        for (Auto am : amigosArrayListCopy){
                            String nombre = am.getMarcayModelo();
                            String tel = am.getRequisitos();
                            String email = am.getCaja();
                            String direccion = am.getPrecio();

                            String buscando = tempVal.getText().toString().trim().toLowerCase();

                            if(nombre.toLowerCase().trim().contains(buscando) ||
                                    tel.trim().contains(buscando) ||
                                    email.trim().toLowerCase().contains(buscando) ||
                                    direccion.trim().toLowerCase().contains(buscando)
                            ){
                                amigosArrayList.add(am);
                            }
                        }
                    }
                    adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), amigosArrayList);
                    ltsAmigos.setAdapter(adaptadorImagenes);
                }catch (Exception e){
                    mostrarMsgToask(e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void agregarAmigos(String accion, String[] datos) {
        try {
            Bundle parametrosAmigos = new Bundle();
            parametrosAmigos.putString("accion", accion);
            parametrosAmigos.putStringArray("datos", datos);

            Intent agregarAmigos = new Intent(getApplicationContext(), AgregarAuto.class);
            agregarAmigos.putExtras(parametrosAmigos);
            startActivity(agregarAmigos);
        }catch (Exception e){
            mostrarMsgToask(e.getMessage());
        }

    }

    private void obtenerDatosAmigos(){
        miBD = new DB(getApplicationContext(),"",null,1);
        datosAmigosCursor = miBD.administracion_Auto("consultar",null);
        if( datosAmigosCursor.moveToFirst() ){//si hay datos que mostrar
            mostrarDatosAmigos();
        } else {
            mostrarMsgToask("No hay productos que mostrar, agregue uno nuevo ...");
            agregarAmigos("nuevo", new String[]{});
        }
    }

    private void mostrarDatosAmigos(){
        ltsAmigos = findViewById(R.id.lsAuto);
        amigosArrayList.clear();
        amigosArrayListCopy.clear();
        do{
            misAmigos = new Auto(
                    datosAmigosCursor.getString(0),
                    datosAmigosCursor.getString(1),
                    datosAmigosCursor.getString(2),
                    datosAmigosCursor.getString(3),
                    datosAmigosCursor.getString(4),
                    datosAmigosCursor.getString(5),
                    datosAmigosCursor.getString(6)
            );
            amigosArrayList.add(misAmigos);
        }while(datosAmigosCursor.moveToNext());
        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), amigosArrayList);
        ltsAmigos.setAdapter(adaptadorImagenes);

        registerForContextMenu(ltsAmigos);

        amigosArrayListCopy.addAll(amigosArrayList);
    }

    private void mostrarMsgToask(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }


    public void Salir(View view) {
        Intent volver = new Intent(this, Menu_P.class);
        finish();
        startActivity(volver);
    }




}