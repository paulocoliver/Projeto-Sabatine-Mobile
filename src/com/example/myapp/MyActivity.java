package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import android.content.Intent;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        //usuarioDAO.remover();
        Usuario usuario = usuarioDAO.getUsuario();

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }

        // intent.putExtra(EXTRA_ID_PORTAL, id_portal);

        setContentView(R.layout.main);
    }
}
