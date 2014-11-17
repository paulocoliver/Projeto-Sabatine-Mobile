package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.model.Usuario;

/**
 * Created by paulo on 14/11/14.
 */
public class UsuarioDAO {

    public static final String TABLE_USUARIO = "usuario";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SENHA = "senha";

    private String[] allColumns = { COLUMN_ID, COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA};

    private SQLiteDatabase database;

    public  DataBaseHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void openWrite() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void openRead() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Usuario getUsuario (){
        try{
            openRead();
            Cursor cursor = database.query(TABLE_USUARIO, allColumns, null, null,null, null, null);
            cursor.moveToFirst();
            return cursorToUsuario(cursor);
        }
        finally {
            close();
        }


    }

    public void remover (){
        try{
            openWrite();
            database.delete(TABLE_USUARIO, null, null);
        }finally {
            close();
        }
    }

    public Usuario salvar(Usuario usuario) {
        try{
            openWrite();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, usuario.getId());
            values.put(COLUMN_NOME, usuario.getNome());
            values.put(COLUMN_EMAIL, usuario.getEmail());
            values.put(COLUMN_SENHA, usuario.getSenha());
            database.insert(TABLE_USUARIO, null, values);
            return usuario;
        }finally {
            close();
        }
    }

    public Usuario cursorToUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();

        if (cursor.getCount() > 0) {
            usuario.setId(cursor.getLong(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
        }

        return usuario;
    }

}
