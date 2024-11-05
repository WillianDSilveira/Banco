package com.example.banco;

public class Conta {
    public String nomeTitular;
    public String numeroDaConta;
    public double saldo;

    public Conta(String nomeTitular, String numeroDaConta, double saldo) {
        this.nomeTitular = nomeTitular;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
    }
}
