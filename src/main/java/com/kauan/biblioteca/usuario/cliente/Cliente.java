package com.kauan.biblioteca.usuario.cliente;

import com.kauan.biblioteca.endereco.Endereco;
import com.kauan.biblioteca.usuario.DadosAtualizacao;
import com.kauan.biblioteca.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private boolean ativo;

    @Embedded
    Endereco endereco;


    public Cliente(DadosCadastroCliente dadosCadastroCliente){
        this.nome = dadosCadastroCliente.nome();
        this.email = dadosCadastroCliente.email();
        this.telefone = dadosCadastroCliente.telefone();
        this.cpf = dadosCadastroCliente.cpf();
        this.endereco = new Endereco(dadosCadastroCliente.endereco());
    }


    @Override
    public void atualizarInformacoes(DadosAtualizacao dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void exclui() {
        this.ativo = false;
    }
}
