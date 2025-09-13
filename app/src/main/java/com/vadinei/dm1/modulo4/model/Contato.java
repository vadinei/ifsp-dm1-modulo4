package com.vadinei.dm1.modulo4.model;

public class Contato {
    private final int id;
    private final String nome;

    public Contato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
