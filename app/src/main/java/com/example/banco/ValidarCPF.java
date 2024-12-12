package com.example.banco;

import android.util.Log;

public class ValidarCPF {

    // Método para validar CPF
    public boolean isCpfValido(String cpf) {
        // Remover caracteres não numéricos (pontuação, espaços, etc.)
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem exatamente 11 dígitos
        if (cpf.length() != 11) {
            Log.d("CPF", "CPF inválido: não tem 11 dígitos.");
            return false;
        }

        // Não pode ser uma sequência de números repetidos
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            Log.d("CPF", "CPF inválido: sequência repetitiva.");
            return false;
        }

        // Calcular o primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
        }
        int digito1 = soma1 % 11;
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - digito1;
        }

        // Calcular o segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
        }
        int digito2 = soma2 % 11;
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - digito2;
        }

        // Verificar se os dígitos calculados são iguais aos dígitos informados
        if (digito1 == Integer.parseInt(cpf.substring(9, 10)) && digito2 == Integer.parseInt(cpf.substring(10, 11))) {
            Log.d("CPF", "CPF válido.");
            return true;  // CPF válido
        } else {
            Log.d("CPF", "CPF inválido: dígitos verificadores não coincidem.");
            return false;  // CPF inválido
        }
    }
}