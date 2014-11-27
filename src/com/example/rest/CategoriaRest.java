package com.example.rest;


import android.util.Log;
import com.example.model.Categoria;
import com.example.model.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by paulo on 14/11/14.
 */
public class CategoriaRest extends RestAbstract {


    public CategoriaRest() {
        this.resource = "categoria";
    }


    public Categoria fromJson(String jsonString) {
        Categoria categoria = new Categoria();
        try {
            JSONObject obj = new JSONObject(jsonString);
            Log.i("titulo categoria", obj.getString("titulo"));

            categoria.setId(obj.getLong("id"));
            categoria.setId_usuario(obj.getLong("id_usuario"));
            categoria.setId_categoria_pai(obj.getLong("id_categoria_pai"));
            categoria.setTitulo(obj.getString("titulo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public String toJson(Categoria categoria) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", categoria.getId());
            object.put("id_usuario", categoria.getId_usuario());
            object.put("id_categoria_pai", categoria.getId_categoria_pai());
            object.put("titulo", categoria.getTitulo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

}
