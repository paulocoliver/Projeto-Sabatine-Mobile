package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.rest.CategoriaRest;
import com.example.rest.RestResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by paulo on 24/11/14.
 */
public class CategoriaActivity extends AbstractActivity {

    public final static String EXTRA_ID_CAT = "com.example.myapp.ID_CAT";
    ListView listView;
    private String[] values = {};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        ArrayList<String> valores = new ArrayList<String>();

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listviewCategoria);


        CategoriaRest categoriaRest = new CategoriaRest();

        RestResponse res = categoriaRest.getList(usuarioLogado);
        if (res.isSuccess()) {


            try {
                JSONArray jArray = new JSONArray(res.getContent());
                if (jArray != null) {
                    for (int i=0; i<jArray.length(); i++) {
                        JSONObject userObject = new JSONObject(jArray.get(i).toString());
                        valores.add(userObject.getString("id")+" - "+userObject.getString("titulo"));
                        //Log.i("rest"+i, userObject.getString("titulo"));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valores);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);

                String[] tokens = itemValue.split(" - ");
                Toast.makeText(getApplicationContext(), "  id : " + tokens[0], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), AddCategoriaActivity.class);
                intent.putExtra(EXTRA_ID_CAT, Integer.parseInt(tokens[0]));
                startActivity(intent);
            }

        });


        findViewById(R.id.btn_add_cat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCategoriaActivity.class);
                intent.putExtra(EXTRA_ID_CAT, "");
                startActivity(intent);
            }
        });

    }
}