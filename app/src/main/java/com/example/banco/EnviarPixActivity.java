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

public class EnviarPixActivity extends AppCompatActivity {
    RepositorioBanco repositorioBanco;
    Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enviar_pix);

    }

    public void confirmarPix(View view) {

        repositorioBanco = new RepositorioBanco(this);
        conta = repositorioBanco.getConta();

        EditText edtChavePix = findViewById(R.id.edtChavePix);
        EditText edtValorPix = findViewById(R.id.edtValorPix);
        String valorChaveTexto = edtValorPix.getText().toString();
        String valorPixTexto = edtValorPix.getText().toString();



        if (valorPixTexto.isEmpty() && valorChaveTexto.isEmpty()) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!valorPixTexto.matches("\\d+(\\.\\d+)?") || !valorChaveTexto.matches("\\d+(\\.\\d+)?")) {
            Toast.makeText(this, "Digite apenas numeros", Toast.LENGTH_SHORT).show();
            return;
        }

        ValidarCPF validadorCPF = new ValidarCPF();
        ValidarTelefone validadorTelefone = new ValidarTelefone();
        if (!validadorCPF.isCpfValido(valorChaveTexto)) {
            Toast.makeText(this, "Digite uma chave valida CPF ou Telefone", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorPixNum = Double.parseDouble(valorPixTexto);
        if (valorPixNum <= 0) {
            Toast.makeText(this, "Digite um valor maior que ZERO", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualiza o saldo diretamente na conta
        double novoSaldo = conta.saldo + valorPixNum;
        repositorioBanco.atualizarSaldo(novoSaldo);
        repositorioBanco.registrarTransacao("pix", valorPixNum, novoSaldo);

        Toast.makeText(this, "Depósito realizado com sucesso", Toast.LENGTH_SHORT).show();

        // Chama a tela Principal
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        finish();

    }
}