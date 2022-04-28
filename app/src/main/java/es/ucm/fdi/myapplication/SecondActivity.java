package es.ucm.fdi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import es.ucm.fdi.myapplication.db.DbRutina;

public class SecondActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private EditText txtEjercicio;
    private EditText txtRepeticiones;
    private EditText txtPeso;
    private String[]header={"Id","Ejercicio","Repeticiones","Peso"};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TablaDinamica tablaDinamica;
    private Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tableLayout=(TableLayout)findViewById(R.id.table);
        txtEjercicio=findViewById(R.id.txtEjercicio);
        txtRepeticiones=findViewById(R.id.txtRepeticiones);
        txtPeso=findViewById(R.id.txtPeso);

        btnGuardar=findViewById(R.id.guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbRutina dbRutina = new DbRutina(SecondActivity.this);
                long id = dbRutina.insertarRutina(txtEjercicio.getText().toString(),
                        txtRepeticiones.getText().toString(), txtPeso.getText().toString());

                if(id > 0){
                    Toast.makeText(SecondActivity.this,"EJERCICIO GUARDADA",
                            Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(SecondActivity.this,"ERROR BD",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtEjercicio.setText("");
        txtPeso.setText("");
        txtRepeticiones.setText("");
    }
    private ArrayList<String[]>getEjercicios(){
        rows.add(new String[]{"1","1","1","1"});
        return rows;
    }

}