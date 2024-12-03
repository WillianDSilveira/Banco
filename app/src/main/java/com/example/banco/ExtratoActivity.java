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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_extrato);
        setTitle("EXTRATO");


        repositorioBanco = new RepositorioBanco(this);

        ListView listView = findViewById(R.id.listaExtrato);

        // pegando a lista do banco
        List<Conta> listaDB = repositorioBanco.listarExtrato();
        String[] dados = new String[listaDB.size()];

        for (int i = 0; i < listaDB.size(); i++) {
            Conta conta = listaDB.get(i);
            dados[i] = conta.conta + " - " + conta.saldo;
            // dados[i] = DadosCompartilhados.lista.get(i).nome;
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        dados);

        listView.setAdapter(adapter);

    }


}