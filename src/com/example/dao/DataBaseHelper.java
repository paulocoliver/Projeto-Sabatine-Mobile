package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

	private static final String BANCO_DADOS = "datausuario.db";
	private static int   VERSAO = 1; 

	public static final String DATABASE_CREATEUSUARIO = "CREATE TABLE IF NOT EXISTS usuario(_id integer primary key, nome text, email text, senha text);";
		
	public DataBaseHelper(Context context) {
		super(context, BANCO_DADOS, null , VERSAO);
		Log.i("DataBaseHelper","Construtor");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATEUSUARIO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}

}
