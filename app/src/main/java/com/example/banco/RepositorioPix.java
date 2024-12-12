package com.example.banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPix extends SQLiteOpenHelper {
    public RepositorioPix(@Nullable Context context) { super(context, "pix", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table pix" +
                "(id integer not null primary key," +
                "cpf text," + "telefone text)";
        sqLiteDatabase.execSQL(sql);
        Log.i("log", "Criado a tabela PIX");
    }

    public void adcionarChave (Pix pix){
        String sql = "insert into pix values(null,'" + pix.getCpf() + "','" + pix.getTelefone() + "')";
        Log.i("pix", "SQL insert pix:" + sql);
        super.getWritableDatabase().execSQL(sql);
    }

    @SuppressLint("Range")
    public List<Pix> listarChaves() {
        // Lista para armazenar as chaves
        List<Pix> listaPix = new ArrayList<>();

        // Consulta SQL para pegar todos os registros da tabela "pix"
        String sql = "SELECT * FROM pix";

        // Executando a consulta
        Cursor cursor = super.getReadableDatabase().rawQuery(sql, null);

        // Verificando se a consulta retornou algum resultado
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Criando um objeto Pix para cada linha retornada
                Pix pix = new Pix();

                // Obtendo os dados do cursor e setando no objeto Pix
                pix.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pix.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                pix.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));

                // Adicionando o objeto Pix à lista
                listaPix.add(pix);
            } while (cursor.moveToNext());

            cursor.close();
        }
        // Retornando a lista de Pix
        return listaPix;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}