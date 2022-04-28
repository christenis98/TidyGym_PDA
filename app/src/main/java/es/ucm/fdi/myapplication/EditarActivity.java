package es.ucm.fdi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.myapplication.db.DbRutina;

public class EditarActivity extends AppCompatActivity {
    EditText txtEjercicio, txtRepeticiones, txtPeso;
    Button btnGuarda, btnEditar, btnBorrar;
    boolean correct = false;
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
        btnEditar.setVisibility(View.INVISIBLE);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnBorrar.setVisibility(View.INVISIBLE);

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

        DbRutina dbRutina = new DbRutina(EditarActivity.this);
        ejercicio = dbRutina.AmpliarEjercicio(id);

        if(ejercicio != null){
            txtEjercicio.setText(ejercicio.getNombreEjercicio());
            txtRepeticiones.setText(ejercicio.getNumRepes());
            txtPeso.setText(ejercicio.getPeso());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtEjercicio.getText().toString().equals("")&&!txtRepeticiones.getText().toString().equals("")&&
                        !txtPeso.getText().toString().equals("")){
                   correct = dbRutina.editarRutina(id,txtEjercicio.getText().toString(),txtRepeticiones.getText().toString(),
                            txtPeso.getText().toString());

                   if(correct){
                       Toast.makeText(EditarActivity.this,"EJERCICIO MODIFICADO", Toast.LENGTH_LONG).show();
                       verRegistro();
                   }else{
                       Toast.makeText(EditarActivity.this,"ERROR MODIFICANDO", Toast.LENGTH_LONG).show();
                   }
                }else{
                    Toast.makeText(EditarActivity.this, "DEBE RELLENAR TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
