package com.example.myapp;

import android.os.Bundle;
import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import android.content.Intent;

public class MyActivity extends AbstractActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // intent.putExtra(EXTRA_ID_PORTAL, id_portal);

        setContentView(R.layout.main);
    }

}
