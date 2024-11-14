package com.example.banco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SaqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saque);

    }

    public void confirmarSaque(View view) {

        EditText edtValorSaque = findViewById(R.id.edtValorSaque);

        String valorTexto = edtValorSaque.getText().toString();

        // Verifica se campo esta vazio
        if (valorTexto.isEmpty()) {
            Toast.makeText(this, "Digite um valor para depósito", Toast.LENGTH_SHORT).show();
            return;
        }
        // Verifica se o valor não é negativo
        double valorSaque = Double.parseDouble(valorTexto);
        if (valorSaque <= 0) {
            Toast.makeText(this, "Digite um valor positivo", Toast.LENGTH_SHORT).show();
            return;
        }
        // Verifica se o valorSaque não é menor qua o saldo em conta
        if (DadosCompartilhados.conta.saldo < valorSaque ) {
            Toast.makeText(this, "O Valor não disponivel em conta", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualiza o saldo diretamente na conta
        DadosCompartilhados.conta.saldo -= valorSaque;
        Toast.makeText(this, "Depósito realizado com sucesso", Toast.LENGTH_SHORT).show();


        // Chama a tela Principal
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}