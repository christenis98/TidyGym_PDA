package es.ucm.fdi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import es.ucm.fdi.myapplication.db.DbHelper;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


    }
    public void Inicio(View view) {

        DbHelper dbHelper = new DbHelper(Splashscreen.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(Splashscreen.this,"BASE DE DATOS CREADA",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Splashscreen.this,"ERROR CREANDO BASE DE DATOS",Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(Splashscreen.this,MainActivity.class);
        startActivity(intent);
    }
}