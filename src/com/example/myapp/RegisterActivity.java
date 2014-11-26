package com.example.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.example.model.Usuario;

/**
 * Created by paulo on 14/11/14.
 */
public class RegisterActivity extends AbstractLoginActivity {

    private UserRegisterTask mAuthTask = null;

    private String mNome = null;
    private String mEmail = null;
    private String mPassword= null;

    private EditText txtNome = null;
    private EditText txtEmail = null;
    private EditText txtSenha = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        txtNome = (EditText) findViewById(R.id.txt_categoria);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtSenha = (EditText) findViewById(R.id.txt_senha);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
         });
    }

    public void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        txtNome.setError(null);
        txtEmail.setError(null);
        txtSenha.setError(null);

        mNome = txtNome.getText().toString();
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
            txtEmail.setError("Digite o seu e-mail");
            focusView = txtEmail;
            cancel = true;
        }

        if (TextUtils.isEmpty(mNome)) {
            txtNome.setError("Digite o seu nome");
            focusView = txtNome;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            //mLoginStatusMessageView.setText("Entrando");
            //showProgress(true);
            mAuthTask = new UserRegisterTask();
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


    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        public UserRegisterTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Usuario usuario = new Usuario();
            usuario.setNome(mNome);
            usuario.setEmail(mEmail);
            usuario.setSenha(mPassword);

            return usuario.create(getApplicationContext());
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