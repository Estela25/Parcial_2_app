package com.ugb.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Database.BDHelper;

public class Login extends AppCompatActivity {

    EditText et1,et2;
    private Cursor fila;
    String Title = "Inicio de Sesión";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle(Title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        et1= (EditText) findViewById(R.id.etUsuario);
        et2= (EditText) findViewById(R.id.edtClave);
    }

    public void InicioSesion(View v){

        BDHelper admin=new BDHelper(this,"instituto",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String usuario=et1.getText().toString();
        String contrasena=et2.getText().toString();
        fila=db.rawQuery("select username,clave_user from userstable where username='"+
                usuario+"' and clave_user='"+contrasena+"'",null);

        try {

            if(fila.moveToFirst()){

                String usua=fila.getString(0);
                String pass=fila.getString(1);

                if (usuario.equals(usua)&&contrasena.equals(pass)){
                    Intent ven=new Intent(this, Menu_P.class);
                    startActivity(ven);
                    et1.setText("");
                    et2.setText("");
                }
            }
            else {
                Toast toast=Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG);
                toast.show();
            }

        } catch (Exception e) {
            Toast toast=Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void RegistroData(View v){
        Intent rdata=new Intent(this, Registro_data.class);
        startActivity(rdata);
    }
}
