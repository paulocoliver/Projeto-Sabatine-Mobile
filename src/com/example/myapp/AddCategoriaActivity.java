package com.example.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.example.model.Categoria;
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
    CategoriaRest categoriaRest;
    EditText txtCategoria;

    private void redirList() {
        Intent intentCategorias = new Intent(getApplicationContext(), CategoriaActivity.class);
        startActivity(intentCategorias);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categoria);

        Intent intent = getIntent();
        id_cat = intent.getIntExtra(CategoriaActivity.EXTRA_ID_CAT, 0);

        categoriaRest = new CategoriaRest();

        txtCategoria = (EditText) findViewById(R.id.txt_categoria);
        TextView titleAddCategoria = (TextView) findViewById(R.id.titleAddCategoria);
        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);



        if (id_cat > 0) {
            titleAddCategoria.setText("Editar Categoria");
            Log.i("res id_cat", "id_cat-" + id_cat);


            //txtCategoria.setText("teste");

            RestResponse res = categoriaRest.get(usuarioLogado, id_cat);
            if (res.isSuccess()) {
                Log.i("categoria result", res.getContent());
                Categoria categoria = categoriaRest.fromJson(res.getContent());

               // Log.i("categoria titulo res2", categoria.getTitulo());
                try {
                    JSONObject obj = new JSONObject(res.getContent());
                    txtCategoria.setText( obj.getString("titulo") );
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            } else {
                Toast.makeText(getApplicationContext(), "Categoria não encontrada ", Toast.LENGTH_LONG).show();
            }

        } else {
            btn_delete.setVisibility(Button.INVISIBLE);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = new Categoria();
                categoria.setTitulo(txtCategoria.getText().toString());
                String json = categoriaRest.toJson(categoria);
                if (id_cat > 0) {
                    RestResponse res = categoriaRest.put(usuarioLogado, id_cat, json);
                    if (res.isSuccess()) {
                        sendMsg("Categoria alterada");
                        redirList();
                    } else {
                        sendMsg("Erro ao tentar alterar a categoria");
                    }

                } else {
                    RestResponse res = categoriaRest.post(usuarioLogado, json);
                    if (res.isSuccess()) {
                        sendMsg("Categoria adicionada");
                        redirList();
                    } else {
                        sendMsg("Erro ao tentar adicionar a categoria");
                    }
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddCategoriaActivity.this);
                alertDialog.setTitle("Confirmar");
                alertDialog.setMessage("Tem certeza de que deseja excluir a categoria?");

                alertDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        RestResponse res = categoriaRest.delete(usuarioLogado, id_cat);
                        if (res.isSuccess()) {
                            sendMsg("Categoria apagada");
                            redirList();
                        } else {
                            sendMsg("Categoria não encontrada");
                        }

                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

            }
        });


    }
}