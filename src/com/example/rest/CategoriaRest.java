package com.example.rest;


import android.util.Log;
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


    public Usuario fromJson(String jsonString) {
        Usuario usuario = new Usuario();
        try {
            JSONObject userObject = new JSONObject(jsonString);
            usuario.setId(userObject.getLong("id"));
            usuario.setNome(userObject.getString("nome"));
            usuario.setEmail(userObject.getString("email"));
            usuario.setSenha(userObject.getString("senha"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public String toJson(Usuario usuario) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", usuario.getId());
            object.put("nome", usuario.getNome());
            object.put("email", usuario.getEmail());
            object.put("senha", usuario.getSenha());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();

    }

}
