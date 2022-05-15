package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Database.BDHelper;

public class Registro_data extends AppCompatActivity {

    String Title="Registro de Usuarios";
    /*EditText para el ingreso de datos del usuario*/
    EditText Etusurname,EtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_data);

        this.setTitle(Title);
        /*Instanciamos las variables
         * capturamos los datos*/
        Etusurname = (EditText) findViewById(R.id.txtusuario);
        EtPass=(EditText) findViewById(R.id.txtpass);
    }

    public void RegistrarDataUser(View v){
        /*creamos un objeto de la clase DBHelper
         * inicializamos el constructor
         * nombramos la base de datos
         * version de la base de datos*/
        BDHelper admin=new BDHelper(this,"instituto",null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        /*creamos dos variables string
         * inicializamos y convertimos*/
        String UserName=Etusurname.getText().toString();
        String PassUser=EtPass.getText().toString();
        /*Creamos un objeto contentvalues y instanciamos*/
        ContentValues values = new ContentValues();
        /*capturamos valores*/
        values.put("username",UserName);
        values.put("clave_user",PassUser);
        /*llamamos al insert damos el nombre de la base de datos
         * y los valores*/
        db.insert("userstable",null,values);
        /*cerramos la base de datos*/
        db.close();
        /*Lanzamos una notificacion toast*/
        Toast ToastMens= Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT);
        /*mostramos el toast*/
        ToastMens.show();
        /*lanzamos la actividad*/
        Intent intent=new Intent(this, Login.class);
        /*iniciamos la actividad*/
        startActivity(intent);
    }
}