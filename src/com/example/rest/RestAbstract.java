package com.example.rest;

import android.util.Base64;
import com.example.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paulo on 14/11/14.
 */
abstract public class RestAbstract {

    protected String endPoint = "http://ec2-54-191-139-233.us-west-2.compute.amazonaws.com/";
    protected String resource = "";

    protected Gson gson;

    protected RestRequest restRequest;

    private void init() {
        gson = new Gson();
        restRequest = new RestRequest();
        restRequest.addHeader("X-API-KEY", "d2UyM3dlMjN3ZTIz");
    }

    public RestAbstract() {
        this.init();
    }

    public RestAbstract(Usuario usuario) {
        this.init();
        setAuthUsuario(usuario);
    }

    public void setAuthUsuario(Usuario usuario) {
        if (usuario.getEmail() != null || !usuario.getEmail().isEmpty()) {
            String auth = usuario.getEmail() + ":" + usuario.getSenha();
            restRequest.addHeader("Authorization", "Basic " + Base64.encodeToString(auth.getBytes(), Base64.DEFAULT));
        }
    }


    public <T>List getList (Class<T> classOfT) {
        RestResponse res = restRequest.get(endPoint+resource);
        if (res.getStatusCode() == 200) {
            String json = res.getContent();
            Object object = gson.fromJson(json, (Type)classOfT);
            Type collectionType = new TypeToken<List<T>>(){}.getType();
            List<T> lista = gson.fromJson(json, collectionType);
            return lista;
        }
        return null;
    }

    public <T> T  get (int id, Class<T> classOfT) {
        RestResponse res = restRequest.get(endPoint+resource+id);
        if (res.getStatusCode() == 200) {
            String json = res.getContent();
            Object object = gson.fromJson(json, (Type)classOfT);
            return Primitives.wrap(classOfT).cast(object);
        }
        return null;
    }

    public RestResponse get (int id) {
        return restRequest.get(endPoint+resource+id);
    }

    public RestResponse post (String json) {
        return restRequest.post(endPoint+resource, json);
    }

    public RestResponse put (int id, String json) {
        return restRequest.put(endPoint+resource+id, json);
    }

    public RestResponse delete (int id) {
        return restRequest.delete(endPoint+resource+id);
    }



}
