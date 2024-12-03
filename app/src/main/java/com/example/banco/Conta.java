package com.example.banco;

public class Conta {
    public int id;
    public double numeroConta;
    public double saldo;

    public Conta() {
    }
    public Conta(int id, double numeroConta, double saldo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }
}
