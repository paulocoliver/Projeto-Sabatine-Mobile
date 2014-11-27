package com.example.model;

/**
 * Created by paulo on 14/11/14.
 */
public class Categoria {

    private Long   id = null;
    private Long id_usuario = null;
    private Long id_categoria_pai = null;
    private String titulo = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_categoria_pai() {
        return id_categoria_pai;
    }

    public void setId_categoria_pai(Long id_categoria_pai) {
        this.id_categoria_pai = id_categoria_pai;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
