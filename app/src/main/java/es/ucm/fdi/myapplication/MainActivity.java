package es.ucm.fdi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void crear_rutina(View view) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }

    public void ver_rutina(View view) {

        Intent intent = new Intent(MainActivity.this, VerActivity.class);
        startActivity(intent);
    }


    public void dietas(View view) {
        Intent intent = new Intent(MainActivity.this, DietaActivity.class);
        startActivity(intent);
    }
}