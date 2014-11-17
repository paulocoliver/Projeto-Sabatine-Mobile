package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.model.Usuario;

/**
 * Created by paulo on 14/11/14.
 */
public class LoginActivity extends Activity {

    private UserLoginTask mAuthTask = null;

    private String mEmail = null;
    private String mPassword= null;

    private EditText txtEmail = null;
    private EditText txtSenha = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtSenha = (EditText) findViewById(R.id.txt_senha);

        findViewById(R.id.btn_entrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
         });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        txtEmail.setError(null);
        txtSenha.setError(null);

        mEmail = txtEmail.getText().toString();
        mPassword = txtSenha.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(mPassword)) {
            txtSenha.setError("Digite a senha");
            focusView = txtSenha;
            cancel = true;
        }

        if (TextUtils.isEmpty(mEmail)) {
            txtEmail.setError("Digite o e-mail");
            focusView = txtEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //mLoginStatusMessageView.setText("Entrando");
            //showProgress(true);
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null);
        }
    }

    public void logado(){
        Intent i = new Intent(this, MyActivity.class);
        startActivity(i);

        mAuthTask = null;
        mEmail = null;
        mPassword = null;
        txtEmail = null;
        txtSenha = null;
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {

            Usuario usuario = new Usuario();
            usuario.setEmail(mEmail);
            usuario.setSenha(mPassword);

            return usuario.login(getApplicationContext());
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);
            if (success) {
                logado();
            }
        }

        @Override
        protected void onCancelled() {
            //showProgress(false);
        }
    }

}