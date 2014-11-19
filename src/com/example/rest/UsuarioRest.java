package com.example.rest;


import android.util.Log;
import com.example.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by paulo on 14/11/14.
 */
public class UsuarioRest extends RestAbstract {


    public UsuarioRest() {
        this.resource = "usuario/";
    }


    public Usuario fromJson(String jsonString) {
        //return gson.fromJson(json, Usuario.class);

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
        //Gson gson = new GsonBuilder().create();
        //String json = gson.toJson(usuario);
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

    public RestResponse login(Usuario usuario) {
        resource += "login";

        String json = this.toJson(usuario);
        Log.d("json encode", json);
        return this.post(json);
    }

}
