package com.example.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;
import com.google.gson.Gson;

import android.util.Base64;
import com.example.model.Usuario;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestRequest {

    RestResponse restResponse = new RestResponse();

    private HashMap<String, String> headers = new HashMap<String, String>();

    public RestRequest() {
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    private void processResponse(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();

        restResponse.setStatusCode(statusLine.getStatusCode());
        String content = this.getContent(response);

        Log.i("Result Content", content);
        //Log.i("StatusCode", statusLine.getStatusCode()+"");

        try {
            JSONObject jsonObject = new JSONObject(content);
            if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                restResponse.setSuccess(true);
            }
            restResponse.setContent(jsonObject.getString("result"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private StringEntity setContent(String json) {
        try {
            StringEntity entity = new StringEntity(json);
            entity.setContentType("application/json;charset=UTF-8");
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            return entity;
        } catch (IOException e) {}
        return null;
    }

    private String getContent(HttpResponse response) {
        StringBuilder builder = new StringBuilder();
        try {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    //Metodo Get
    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
    public RestResponse get(String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        for (String key : headers.keySet())
            httpGet.addHeader(key, headers.get(key));

        try {
            HttpResponse response = client.execute(httpGet);
            this.processResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return restResponse;
    }

    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
    public RestResponse post(String url, String json) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        for (String key : headers.keySet())
            httpPost.addHeader(key, headers.get(key));

        Log.i("url", url);
        Log.i("json", json);

        try {
            httpPost.setEntity(this.setContent(json));
            HttpResponse response = client.execute(httpPost);
            Log.i("url request TESTE ", url);
            this.processResponse(response);

        } catch (ClientProtocolException e) {
            Log.i("ClientProtocolException ", e.getMessage());
        } catch (IOException e) {
            Log.i("IOException ", e.getMessage());
        }
        //Log.i("getContent", restResponse.getContent());

        return restResponse;
    }

    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
    public RestResponse put(String url, String json) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpClient client = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(url);
        for (String key : headers.keySet())
            httpPut.addHeader(key, headers.get(key));

        try {
            httpPut.setEntity(this.setContent(json));
            HttpResponse response = client.execute(httpPut);
            this.processResponse(response);

        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }
        return restResponse;
    }


    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
    public RestResponse delete(String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpClient client = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        //for (String key : headers.keySet())
        //    HttpDelete.addHeader(key, headers.get(key));

        try {
            HttpResponse response = client.execute(httpDelete);
            this.processResponse(response);
        } catch (ClientProtocolException e) {
        } catch (IOException e) {}
        return restResponse;
    }

}
