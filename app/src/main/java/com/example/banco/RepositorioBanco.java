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
        super(context, "BancoApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlConta = "CREATE TABLE CONTA (id INTEGER PRIMARY KEY, saldo REAL)";
        String sqlTransacao = "CREATE TABLE TRANSACAO (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT, valor REAL, saldoAtual REAL)";
        String sqlPix = "CREATE TABLE PIX (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT, chave TEXT)";

        db.execSQL(sqlConta);
        db.execSQL(sqlTransacao);
        db.execSQL(sqlPix);

        db.execSQL("INSERT INTO CONTA (id, saldo) VALUES (1, 0)");
    }


    public Conta getConta() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM CONTA WHERE id = 1", null);
        if (cursor.moveToFirst()) {
            Conta conta = new Conta(cursor.getInt(0), cursor.getDouble(1));
            cursor.close();
            return conta;
        }
        cursor.close();
        return null;
    }

    public void atualizarSaldo(double novoSaldo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE CONTA SET saldo = ? WHERE id = 1", new Object[]{novoSaldo});
    }

    public void registrarTransacao(String tipo, double valor, double saldoAtual) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TRANSACAO (tipo, valor, saldoAtual) VALUES (?, ?, ?)",
                new Object[]{tipo, valor, saldoAtual});
    }

    public List<String> listarTransacoes(String tipo) {
        List<String> transacoes = new ArrayList<>();

        String query;
        String[] args = null;

        if (tipo == null || tipo.trim().isEmpty()) {

            query = "SELECT tipo, valor, saldoAtual FROM TRANSACAO";
        } else {

            query = "SELECT tipo, valor, saldoAtual FROM TRANSACAO WHERE tipo = ?";
            args = new String[]{tipo};
        }

        Cursor cursor = getReadableDatabase().rawQuery(query, args);

        while (cursor.moveToNext()) {
            String linha = "Tipo: " + cursor.getString(0) +
                    " | Valor: R$ " + cursor.getDouble(1) +
                    " | Saldo: R$ " + cursor.getDouble(2);
            transacoes.add(linha);
        }
        cursor.close();

        return transacoes;
    }

    public Conta getContaAtualizada() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT id, saldo FROM CONTA WHERE id = 1", null);
            if (cursor.moveToFirst()) {
                int idConta = cursor.getInt(0);
                double saldoAtual = cursor.getDouble(1);
                return new Conta(idConta, saldoAtual);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CONTA");
        db.execSQL("DROP TABLE IF EXISTS TRANSACAO");
        db.execSQL("DROP TABLE IF EXISTS PIX");
        onCreate(db);
    }
}
