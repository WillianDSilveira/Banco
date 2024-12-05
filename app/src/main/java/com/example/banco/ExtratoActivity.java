package com.example.banco;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ExtratoActivity extends AppCompatActivity {
    RepositorioBanco repositorioBanco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
        setTitle("EXTRATO");

        repositorioBanco = new RepositorioBanco(this);
        ListView listView = findViewById(R.id.ListaExtrato);

        // Pegando a lista de transações do banco
        List<String> transacoes = repositorioBanco.listarTransacoes();

        // Adaptador para o ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, transacoes);

        listView.setAdapter(adapter);

    }


}