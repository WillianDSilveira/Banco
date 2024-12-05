package com.example.banco;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ExtratoActivity extends AppCompatActivity {
    private RepositorioBanco repositorio;
    private ArrayAdapter<String> adapter;
    private ListView layoutExtrato;
    private EditText editTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        repositorio = new RepositorioBanco(this);
        layoutExtrato = findViewById(R.id.ListaExtrato);
        editTipo = findViewById(R.id.editTextTipo);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        layoutExtrato.setAdapter(adapter);

        buscar(null);
    }

    public void buscar(View view) {
        String tipo = editTipo.getText().toString().trim().toLowerCase();
        List<String> transacoesFiltradas;

        if (tipo.isEmpty()) {
            // Se o tipo estiver vazio, carregar todas as transações
            transacoesFiltradas = repositorio.listarTransacoes(null);
        } else if (!tipo.equals("deposito") && !tipo.equals("saque") && !tipo.equals("pix")) {
            Toast.makeText(this, "Digite o Tipo: 'Saque', 'Deposito' ou 'Pix' ", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Filtrar pelo tipo informado
            transacoesFiltradas = repositorio.listarTransacoes(tipo);
        }

        adapter.clear();
        adapter.addAll(transacoesFiltradas);
        adapter.notifyDataSetChanged();
    }
}
