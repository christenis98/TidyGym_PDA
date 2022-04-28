package es.ucm.fdi.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.myapplication.db.DbRutina;

public class AmpliarActivity extends AppCompatActivity {
    EditText txtEjercicio, txtRepeticiones, txtPeso;
    Button btnGuarda, btnEditar, btnBorrar;


    Ejercicio ejercicio;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampliar);

        txtEjercicio = findViewById(R.id.txtEjercicio);
        txtRepeticiones = findViewById(R.id.txtRepeticiones);
        txtPeso = findViewById(R.id.txtPeso);
        btnGuarda = findViewById(R.id.guardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbRutina dbRutina = new DbRutina(AmpliarActivity.this);
        ejercicio = dbRutina.AmpliarEjercicio(id);

        if(ejercicio != null){
            txtEjercicio.setText(ejercicio.getNombreEjercicio());
            txtRepeticiones.setText(ejercicio.getNumRepes());
            txtPeso.setText(ejercicio.getPeso());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtEjercicio.setInputType(InputType.TYPE_NULL);
            txtRepeticiones.setInputType(InputType.TYPE_NULL);
            txtPeso.setInputType(InputType.TYPE_NULL);
        }
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AmpliarActivity.this,EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AmpliarActivity.this);
                builder.setMessage("¿Quieres borrar este ejercicio?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbRutina.borrarRutina(id)) {
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }
    private void lista(){
        Intent intent = new Intent(this, VerActivity.class );
        startActivity(intent);
    }

}