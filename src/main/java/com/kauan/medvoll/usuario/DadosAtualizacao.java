package com.kauan.medvoll.usuario;

import com.kauan.medvoll.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}