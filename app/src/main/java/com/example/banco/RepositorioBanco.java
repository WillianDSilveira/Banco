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
        String sql = "CREATE TABLE CONTA(id INTEGER NOT NULL PRIMARY KEY, saldo REAL)";
        sqLiteDatabase.execSQL(sql);
        Log.i("LOG", "Criado com sucesso a tabela LOG");

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

    public void atualizarSaldo(double novoSaldo){
        String sql = "UPDATE CONTA SET saldo = " + novoSaldo + " WHERE id = 1";
        Log.i("log", "SQL insert log: "+ sql);
        super.getWritableDatabase().execSQL(sql);
        Log.i("LOG", "SALVO COM SUCESSO");
    }


    public List<Conta> listarExtrato(){
        ArrayList<Conta> lista = new ArrayList<>();

        String sql = "select * from CONTA";
        Log.i("LOG", "SQL para listar extrato: " + sql);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();


        for(int i=0; i < cursor.getCount(); i++){
            Conta conta = new Conta();
            conta.id = cursor.getInt(0); // coluna 0
            conta.saldo = cursor.getDouble(1); // coluna 2
            lista.add(conta);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;

    }

    public double obterSaldo() {
        // Retorna o saldo atual da conta
        String sql = "SELECT saldo FROM CONTA WHERE id = 1";
        Log.i("LOG", "SQL para obter saldo: " + sql);
        Cursor cursor = super.getReadableDatabase().rawQuery(sql, null);
        double saldo = 0.0;

        if (cursor.moveToFirst()) {
            saldo = cursor.getDouble(0);
        }
        cursor.close();
        Log.i("LOG", "Saldo obtido: " + saldo);
        return saldo;
    }

    public Conta buscarConta() {
        Conta conta = null;
        String sql = "SELECT * FROM CONTA WHERE id = 1";
        Log.i("LOG", "SQL para buscar conta: " + sql);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            // Se encontrar a conta, preenche a instância de Conta
            conta = new Conta();
            conta.id = cursor.getInt(0);   // coluna 0: ID
            conta.saldo = cursor.getDouble(1); // coluna 1: Saldo
        }
        cursor.close();
        return conta;
    }

    public void registrarTransacao(String tipo, double valor, double saldoAtual) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TRANSACAO (tipo, valor, saldoAtual) VALUES (?, ?, ?)",
                new Object[]{tipo, valor, saldoAtual});
    }

    public List<String> listarTransacoes() {
        List<String> transacoes = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT tipo, valor, saldoAtual FROM TRANSACAO", null);
        while (cursor.moveToNext()) {
            String linha = "Tipo: " + cursor.getString(0) +
                    " | Valor: R$ " + cursor.getDouble(1) +
                    " | Saldo: R$ " + cursor.getDouble(2);
            transacoes.add(linha);
        }
        cursor.close();
        return transacoes;
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
