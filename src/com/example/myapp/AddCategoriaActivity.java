package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.rest.CategoriaRest;
import com.example.rest.RestResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by paulo on 24/11/14.
 */
public class AddCategoriaActivity extends AbstractActivity {

    int id_cat = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categoria);

        Intent intent = getIntent();
        id_cat = intent.getStringExtra(CategoriaActivity.EXTRA_ID_CAT);

        CategoriaRest categoriaRest = new CategoriaRest();

        if (id_cat > 0) {

            categoriaRest.get(id_cat);
            Toast.makeText(getApplicationContext(), "  id_cat : " + id_cat, Toast.LENGTH_LONG).show();
        }


    }
}