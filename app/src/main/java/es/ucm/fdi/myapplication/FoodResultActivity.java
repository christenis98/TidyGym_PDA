package es.ucm.fdi.myapplication;

import java.io.*;
import okhttp3.*;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FoodResultActivity extends AppCompatActivity {

    public String url, code;
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_result);

        view = findViewById(R.id.test);

        Intent intent = getIntent();
        code = intent.getStringExtra("name");

        view.setText("you chose " + code);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://nutritionix-api.p.rapidapi.com/v1_1/search/" + code +
                        "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat%2Cnf_total_carbohydrate%2Cnf_sugars")
                .get()
                .addHeader("X-RapidAPI-Host", "nutritionix-api.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "61ae615dd0msh0a938c89a8d8f83p1abc67jsn152700cef34f")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(FoodResultActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()){
                    String resp = response.body().string();
                    FoodResultActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                String displayString = new JSONHandler().getFoodTypeData(jsonObject.toString());
                                String val1 = jsonObject.toString();
                                view.setText(displayString);
                            }catch(JSONException e){
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });



    }

    //populates the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //controls what happens when the actions are selected
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        boolean iscolor = true;
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.resultLayout);
        TextView title = (TextView)findViewById(R.id.title);
        switch (item.getItemId()) {
            case R.id.action_help:
                showDialog(this, "Help", "Esta aplicación de nutrición tiene como objetivo ayudar a las personas a acceder fácilmente a la información nutricional  utilizando la API de Nutritionix!");
                return true;

            case R.id.action_paint:
                Toast.makeText(this, "Has pintado la pagina!", Toast.LENGTH_LONG).show();
                if(iscolor)
                {
                    layout.setBackgroundColor(Color.BLACK);
                    view.setTextColor(Color.WHITE);
                    title.setTextColor(Color.WHITE);
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

    //designs the dialog box that shows when certain actions are pressed

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


//    //AsyncTask used to connect to the url and gather information from the API
//    private class Network extends AsyncTask<String, Void, String> {
//
//        /*@Override
//        protected String doInBackground(String... strings) {
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            String foodDataJSON = null;
//
//            try{
//                URL url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("x-app-id","fb3ee27b");
//                urlConnection.setRequestProperty("x-app-key","af128963cba662322cac7aa890c6172e");
//                urlConnection.connect();
//                InputStream in = urlConnection.getInputStream();
//                if(in == null) {
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(in));
//                foodDataJSON = getBufferStringFromBuffer(reader).toString();
//                Log.d("JSON", foodDataJSON);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null){
//                    try{
//                        reader.close();
//                    } catch (IOException e){
//                        Log.e("e", "Error" + e.getMessage());
//                        return null;
//                    }
//                }
//            }
//            return foodDataJSON;
//        }
//*/
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                try {
//                    String displayString = new JSONHandler().getFoodData(result);
//                    view.setText(displayString);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        private StringBuffer getBufferStringFromBuffer(BufferedReader br) throws Exception{
//            StringBuffer buffer = new StringBuffer();
//
//            String line;
//            while((line = br.readLine()) != null){
//                buffer.append(line + '\n');
//            }
//
//            if (buffer.length() == 0)
//                return null;
//
//            return buffer;
//        }
//    }



}
