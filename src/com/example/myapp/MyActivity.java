package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.geral, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_categorias:
                //newGame();
                return true;
            case R.id.action_logout:
                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                usuarioDAO.remover();
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
