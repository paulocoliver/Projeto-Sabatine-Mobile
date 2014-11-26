package com.example.rest;


import android.util.Base64;
import android.util.Log;
import com.example.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
    public String resource = "";


    protected RestRequest restRequest;

    private void init() {
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

    // JSONObject userObject = new JSONObject(jsonString);

    public void setAuthUsuario(Usuario usuario) {
        if (usuario.getEmail() != null || !usuario.getEmail().isEmpty()) {
            String auth = usuario.getEmail() + ":" + usuario.getSenha();
            byte[] data = new byte[0];
            try {
                data = auth.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String base64 = Base64.encodeToString(data, Base64.DEFAULT);


            //String authorizationString = "Basic " + Base64.encode(base).toString();


            //String authorizationString = "Basic " + new String(new Base64().encode(base));

            //byte[] authEncBytes = Base64.(auth.getBytes());
            //String authStringEnc = new String(authEncBytes);

            //String Basic = "Basic " + Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);

            String authorizationString = "Basic " + base64;
            Log.i("Auth Basic", authorizationString);
            restRequest.addHeader("Authorization", authorizationString);
        }
    }

    public RestResponse getList (Usuario usuario) {
        //this.setAuthUsuario(usuario);
        return restRequest.get(endPoint+resource);
    }

    public RestResponse get (int id) {
        return restRequest.get(endPoint+resource+id);
    }

    public RestResponse post (String json) {
        Log.d("endPoint+resource", endPoint+resource);
        return restRequest.post(endPoint+resource, json);
    }

    public RestResponse put (int id, String json) {
        return restRequest.put(endPoint+resource+id, json);
    }

    public RestResponse delete (int id) {
        return restRequest.delete(endPoint+resource+id);
    }



}
