package com.kauan.medvoll.usuario.medico;

import com.kauan.medvoll.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//@NotBlank ⇒ Não deixa o campo ser vazio e nem null. *OBS: Só pode ser usado por Strings
//@NotNull ⇒ Não deixa o campo ser null (pode ser anulado por " "(espaço))

public record DadosCadastroMedico(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        String telefone,

        @NotNull
        Boolean ativo,

        @NotNull
        Especialidade especialidade, //Não pode ser usado @NotBlank por ser um enum e não uma String

        @NotNull
        @Valid //Pede para ser feita a validação dessa outra classe junto
        DadosEndereco endereco) {

}
