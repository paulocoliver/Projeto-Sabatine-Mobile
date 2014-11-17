package com.example.rest;


import android.util.Log;
import com.example.model.Usuario;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by paulo on 14/11/14.
 */
public class UsuarioRest extends RestAbstract {

    private String resource = "usuario/";


    public Usuario fromJson(String json) {
        return gson.fromJson(json, Usuario.class);
    }

    public String toJson(Usuario usuario) {
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.d("Gson", "user JSON String: " + json);


        return json;
    }

    public RestResponse login(Usuario usuario) {
        resource += "login";
        Log.d("resource", resource);
        return this.post(this.toJson(usuario));
    }

}
