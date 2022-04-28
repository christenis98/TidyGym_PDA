package es.ucm.fdi.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import es.ucm.fdi.myapplication.adaptadores.ListaEjerciciosAdapter;
import es.ucm.fdi.myapplication.db.DbRutina;

public class VerActivity extends AppCompatActivity {
    RecyclerView listaEjercicios;
    ArrayList<Ejercicio> listaArrayEjercicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        listaEjercicios = findViewById(R.id.listaEjercicios);
        listaEjercicios.setLayoutManager(new LinearLayoutManager(this));

        DbRutina dbRutina = new DbRutina(VerActivity.this);
        listaArrayEjercicios = new ArrayList<>();
        ListaEjerciciosAdapter adapter = new ListaEjerciciosAdapter(dbRutina.mostrarEjercicios());
        listaEjercicios.setAdapter(adapter);
    }
}