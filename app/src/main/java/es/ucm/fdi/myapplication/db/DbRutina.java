package es.ucm.fdi.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import es.ucm.fdi.myapplication.Ejercicio;

public class DbRutina extends DbHelper {
    Context context;

    public DbRutina(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRutina(String ejercicio, String repeticiones, String peso){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("ejercicio", ejercicio);
            values.put("repeticiones", repeticiones);
            values.put("peso", peso);

            id = db.insert(TABLE_RUTINAS, null, values);
        }catch(Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Ejercicio> mostrarEjercicios(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        Ejercicio ejercicio = null;
        Cursor cursorEjercicio = null;

        cursorEjercicio = db.rawQuery("SELECT * FROM " + TABLE_RUTINAS, null);

        if(cursorEjercicio.moveToFirst()){
            do{
                ejercicio = new Ejercicio();
                ejercicio.setId(cursorEjercicio.getInt(0));
                ejercicio.setNombreEjercicio(cursorEjercicio.getString(1));
                ejercicio.setNumRepes(cursorEjercicio.getString(2));
                ejercicio.setPeso(cursorEjercicio.getString(3));
                listaEjercicios.add(ejercicio);
            }while(cursorEjercicio.moveToNext());
        }

        cursorEjercicio.close();
        return listaEjercicios;
    }

    public Ejercicio AmpliarEjercicio(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Ejercicio ejercicio = null;
        Cursor cursorEjercicio;

        cursorEjercicio = db.rawQuery("SELECT * FROM " + TABLE_RUTINAS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorEjercicio.moveToFirst()){

            ejercicio = new Ejercicio();
            ejercicio.setId(cursorEjercicio.getInt(0));
            ejercicio.setNombreEjercicio(cursorEjercicio.getString(1));
            ejercicio.setNumRepes(cursorEjercicio.getString(2));
            ejercicio.setPeso(cursorEjercicio.getString(3));


        }

        cursorEjercicio.close();
        return ejercicio;
    }
    public boolean editarRutina(int id,String ejercicio, String repeticiones, String peso){
        boolean correct = false;

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {


            db.execSQL("UPDATE "+ TABLE_RUTINAS + " SET ejercicio = '"+ejercicio+"',repeticiones = '"+repeticiones+"',peso = '"+peso+"' WHERE id='"+ id +"'");
            correct = true;
        }catch(Exception ex){
            ex.toString();
            correct=false;
        }finally {
            db.close();
        }
        return correct;
    }
    public boolean borrarRutina(int id){
        boolean correct = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {


            db.execSQL("DELETE FROM "+ TABLE_RUTINAS + " WHERE id='"+ id +"'");
            correct = true;
        }catch(Exception ex){
            ex.toString();
            correct=false;
        }finally {
            db.close();
        }
        return correct;
    }
}

