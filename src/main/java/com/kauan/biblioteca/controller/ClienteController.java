package com.kauan.biblioteca.controller;


import com.kauan.biblioteca.usuario.DadosAtualizacao;
import com.kauan.biblioteca.usuario.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody DadosCadastroCliente dados){
        repository.save(new Cliente(dados));
    }

    @GetMapping
    public Page<ListagemDadosClientes> lista(@PageableDefault(sort = "nome") Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(ListagemDadosClientes::new);
    }

    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizacao dadosAtualizacao){
        var cliente = repository.getReferenceById(dadosAtualizacao.id());
        cliente.atualizarInformacoes(dadosAtualizacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        cliente.exclui();
    }
}
