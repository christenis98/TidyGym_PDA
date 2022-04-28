package es.ucm.fdi.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DietaActivity extends AppCompatActivity {


    public RadioButton rButton1;
    public RadioButton rButton2;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta2);


        text = (TextView)findViewById(R.id.topText);

        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rButton1=(RadioButton)findViewById(R.id.foodTypeButton);
        rButton2=(RadioButton)findViewById(R.id.UPCButton);

    }
    //controls what happens when on continue is clicked
    public void onContinue(View view) {

        if (rButton1.isChecked()) {
            Intent intent = new Intent(this, FoodTypeActivity.class);
            startActivity(intent);
        } else if (rButton2.isChecked()) {
            Intent intent = new Intent(this, UPCActivity.class);
            startActivity(intent);
        }
    }

    //populates the action bar with the items from menu_main.xml
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //controls what happens when the actions in the toolbar are selected
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        boolean iscolor = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
        switch (item.getItemId()) {
            case R.id.action_help:
                showDialog(this, "Help", "Esta aplicación de nutrición tiene como objetivo ayudar a las personas a acceder fácilmente a la información nutricional  utilizando la API de Nutritionix!");
                return true;

            case R.id.action_paint:
                Toast.makeText(this, "Has pintado la pagina!", Toast.LENGTH_LONG).show();
                if(iscolor)
                {
                    layout.setBackgroundColor(Color.CYAN);
                    iscolor = false;
                }
                else
                {
                    layout.setBackgroundColor(Color.WHITE);
                    iscolor = true;
                }
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //This is the code for the dialog message box that pops up for the help option
    public void showDialog(Activity activity, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        builder.show();
    }



}
