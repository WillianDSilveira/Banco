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

public class DepositoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deposito);

    }

    public void confirmarDeposito(View view) {
        EditText edtValorDeposito = findViewById(R.id.edtValorDeposito);

        String valorTexto = edtValorDeposito.getText().toString();

        if (valorTexto.isEmpty()) {
            Toast.makeText(this, "Digite um valor para depósito", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorDeposito = Double.parseDouble(valorTexto);
        if (valorDeposito <= 0) {
            Toast.makeText(this, "Digite um valor positivo", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualiza o saldo diretamente na conta
        DadosCompartilhados.conta.saldo += valorDeposito;
        Toast.makeText(this, "Depósito realizado com sucesso", Toast.LENGTH_SHORT).show();

        // Chama a tela Principal
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);

        // Fecha a DepositoActivity e retorna à MainActivity

    }
}