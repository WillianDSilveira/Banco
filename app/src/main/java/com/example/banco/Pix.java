package com.example.banco;

public class Pix {
    private int id;
    private String cpf;
    private String telefone;

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Sobrescrevendo o método toString()
    @Override
    public String toString() {
        return "ID: " + id + " CPF: " + cpf + " / TEL: " + telefone + "";
    }
}
