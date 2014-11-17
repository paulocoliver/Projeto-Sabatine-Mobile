package com.example.model;

import android.content.Context;
import android.util.Log;
import com.example.dao.UsuarioDAO;
import com.example.rest.RestResponse;
import com.example.rest.UsuarioRest;
import org.json.JSONException;

/**
 * Created by paulo on 14/11/14.
 */
public class Usuario {
    private Long   id = null;
    private String nome = null;
    private String email = null;
    private String senha = null;

    private UsuarioRest usuarioRest = new UsuarioRest();


    public Usuario() {

    }

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    private Usuario setAll(Usuario usuario) {
        this.setId(usuario.getId());
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        return this;
    }


    public boolean login (Context context) {
        RestResponse res = usuarioRest.login(this);
        if (res.getStatusCode() == 200) {

            if (res.getContent() != null && res.getContent().isEmpty()) {

                Log.d("Json return", res.getContent());

                this.setAll(usuarioRest.fromJson(res.getContent()));
                UsuarioDAO usuarioDAO = new UsuarioDAO(context);
                usuarioDAO.salvar(this);
            }
            return true;
        }

        return false;
    }

    public void read(Context context) {

        //context.

    }

    public boolean create(Context context, Usuario usuario) {
        RestResponse res = usuarioRest.post(usuarioRest.toJson(usuario));
        if (res.getStatusCode()  == 201) {
            if (res.getContent() != null && res.getContent().isEmpty()) {
                this.setAll(usuarioRest.fromJson(res.getContent()));
                UsuarioDAO usuarioDAO = new UsuarioDAO(context);
                usuarioDAO.salvar(this);
            }
            return true;
        }
        return false;
    }

    public void update(Usuario usuario) {

    }

    public void delete(Usuario usuario) {

    }
}
