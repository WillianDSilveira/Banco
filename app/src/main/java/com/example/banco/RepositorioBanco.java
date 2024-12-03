package com.example.banco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RepositorioBanco extends SQLiteOpenHelper {


    public RepositorioBanco(@Nullable Context context) {
        super(context, "log", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE CONTA(id INTEGER NOT NULL PRIMARY KEY, numeroConta REAL, saldo REAL)";
        sqLiteDatabase.execSQL(sql);
        Log.i("LOG", "Criado com sucesso a tabela LOG");

    }

    public List<Conta> listarExtrato(){
        ArrayList<Conta> lista = new ArrayList<>();
        String sql = "select * from Conta";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        for(int i=0; i < cursor.getCount(); i++){
            Conta conta = new Conta();
            conta.id = cursor.getInt(0); // coluna 0
            conta.numeroConta = cursor.getDouble(1); // coluna 1
            conta.saldo = cursor.getDouble(2); // coluna 2
            lista.add(conta);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
