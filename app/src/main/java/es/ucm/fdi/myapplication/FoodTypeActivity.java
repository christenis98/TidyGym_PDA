package es.ucm.fdi.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FoodTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);


        Intent intent = getIntent();
        String nameText = intent.getStringExtra("choice");


    }


    public void onContinueResult(View view) {
        EditText messageView = (EditText)findViewById(R.id.editText);
        String foodText = messageView.getText().toString();

        Intent intent = new Intent(this, FoodResultActivity.class);
        intent.putExtra("name", foodText);
        startActivity(intent);
    }
}
