package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;

/**
 * Created by paulo on 24/11/14.
 */
public class AbstractActivity extends Activity {

    Usuario usuarioLogado;

    protected void sendMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        //usuarioDAO.remover();
        usuarioLogado = usuarioDAO.getUsuario();

        if (usuarioLogado.getEmail() == null || usuarioLogado.getEmail().isEmpty()) {
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
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
                Intent intentCategoria = new Intent(this, CategoriaActivity.class);
                startActivity(intentCategoria);
                finish();
                return true;
            case R.id.action_transacoes:
                Intent intentTransacoes = new Intent(this, MyActivity.class);
                startActivity(intentTransacoes);
                finish();
                return true;
            case R.id.action_configuracoes:
                Intent intentConfiguracao = new Intent(this, ConfiguracaoActivity.class);
                startActivity(intentConfiguracao);
                finish();
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