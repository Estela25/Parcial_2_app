package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CarroDB extends SQLiteOpenHelper {
    Context miContext;
    static String nombreDB = "db_auto";
    static String tblauto = "CREATE TABLE tblauto(idventa integer primary key autoincrement, Marca text, Precio text, Fecha text, Vendedor text)";
    public CarroDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreDB, factory, version);
        miContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(tblauto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor administracion_Auto(String accion, String[] datos){

        try {
            Cursor datosCursor = null;
            SQLiteDatabase sqLiteDatabaseW = getWritableDatabase();
            SQLiteDatabase sqLiteDatabaseR = getReadableDatabase();
            switch (accion) {
                case "consultar":
                    datosCursor = sqLiteDatabaseR.rawQuery("select * from tblauto order by Marca", null);
                    break;
                case "nuevo":
                    sqLiteDatabaseW.execSQL("INSERT INTO tblauto(Marca,Precio,Fecha,Vendedor) VALUES ('" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "')");
                    break;
                case "modificar":
                    sqLiteDatabaseW.execSQL("UPDATE tblauto SET Marca='" + datos[1] + "',Precio='" + datos[2] + "',Fecha='" + datos[3] + "',Vendedor='" + datos[4] + "' WHERE idAmigo='" + datos[0] + "'");
                    break;
                case "eliminar":
                    sqLiteDatabaseW.execSQL("DELETE FROM tblauto WHERE idauto='" + datos[0] + "'");
                    break;
            }
            return datosCursor;
        }catch (Exception e){
            Toast.makeText(miContext, "Error en la administracion de la BD "+ e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
