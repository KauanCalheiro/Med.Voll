package com.kauan.biblioteca.usuario;

import com.kauan.biblioteca.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}