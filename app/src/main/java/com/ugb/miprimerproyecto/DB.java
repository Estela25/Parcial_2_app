package com.ugb.miprimerproyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    Context miContext;
    static String nombreDB = "db_auto2";
    static String tblAmigos = "CREATE TABLE tblamigos(idAmigo integer primary key autoincrement, Marca_y_Modelo text, Caracteristicas text, Requisitos text, Caja text, Precio text, urlPhoto text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version); //CREATE DATABASE db_amigos; -> MySQL, SQL Server, Oracle, PostGreeSQL, other...
        miContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAmigos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NO, porque es para migrar o actualizar a una nueva version...
    }
    public Cursor administracion_Auto(String accion, String[] datos){
        try {
            Cursor datosCursor = null;
            SQLiteDatabase sqLiteDatabaseW = getWritableDatabase();
            SQLiteDatabase sqLiteDatabaseR = getReadableDatabase();
            switch (accion) {
                case "consultar":
                    datosCursor = sqLiteDatabaseR.rawQuery("select * from tblamigos order by Marca_y_Modelo", null);
                    break;
                case "nuevo":
                    sqLiteDatabaseW.execSQL("INSERT INTO tblamigos(Marca_y_Modelo,Caracteristicas,Requisitos,Caja,Precio,urlPhoto) VALUES ('" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "','" + datos[5]+ "','" + datos[6] + "')");
                    break;
                case "modificar":
                    sqLiteDatabaseW.execSQL("UPDATE tblamigos SET Marca_y_Modelo='" + datos[1] + "',Caracteristicas='" + datos[2] + "',Requisitos='" + datos[3] + "',Caja='" + datos[4] +"',Precio='" + datos[5] + "',urlPhoto='"+ datos[6] + "' WHERE idAmigo='" + datos[0] + "'");
                    break;
                case "eliminar":
                    sqLiteDatabaseW.execSQL("DELETE FROM tblamigos WHERE idAmigo='" + datos[0] + "'");
                    break;
            }
            return datosCursor;
        }catch (Exception e){
            Toast.makeText(miContext, "Error en la administracion de la BD "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
