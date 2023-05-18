package com.kauan.biblioteca.usuario.cliente;

public record ListagemDadosClientes(Long id, String nome, String email, String cpf) {

    public ListagemDadosClientes(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf());
    }

}
