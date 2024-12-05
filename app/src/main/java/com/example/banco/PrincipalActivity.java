package com.example.banco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrincipalActivity extends AppCompatActivity {


    private TextView  txtSaldo;
    RepositorioBanco repositorioBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        // Inicializa os componentes da interface de usuário

        txtSaldo = findViewById(R.id.txtSaldo);

        carregarInformacoesDaConta();

    }

    // Método para carregar as informações da conta na interface
    private void carregarInformacoesDaConta() {
        repositorioBanco = new RepositorioBanco(this);
        Conta conta =  repositorioBanco.buscarConta();
        txtSaldo.setText(String.format("Saldo: R$ %.2f", conta.saldo));
    }

    public void depositar(View view) {
        Intent intent = new Intent(this, DepositoActivity.class);
        startActivity(intent);
    }

    public void sacar(View view) {
        Intent intent = new Intent(this, SaqueActivity.class);
        startActivity(intent);
    }
}




