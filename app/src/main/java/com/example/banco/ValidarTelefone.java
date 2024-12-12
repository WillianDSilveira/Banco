package com.example.banco;

public class ValidarTelefone {

    // Método para validar o telefone
    public boolean isTelefoneValido(String telefone) {
        // Remover caracteres não numéricos (como espaços, parênteses, traços)
        telefone = telefone.replaceAll("[^0-9]", "");

        // Verificar se o telefone tem 10 ou 11 dígitos
        if (telefone.length() != 10 && telefone.length() != 11) {
            return false;
        }

        // Se o número de telefone for celular (com 11 dígitos), o segundo dígito deve ser 9
        if (telefone.length() == 11 && telefone.charAt(2) != '9') {
            return false;
        }

        // Se o número de telefone for fixo (com 10 dígitos), o segundo dígito não pode ser 9
        if (telefone.length() == 10 && telefone.charAt(2) == '9') {
            return false;
        }

        return true;
    }
}