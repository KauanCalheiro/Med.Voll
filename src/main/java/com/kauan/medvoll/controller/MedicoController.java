package com.kauan.medvoll.controller;


import com.kauan.medvoll.usuario.DadosAtualizacao;
import com.kauan.medvoll.usuario.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired //Indica que a propria aplicação ira preencher o valor
    private MedicoRepository repository;

    @PostMapping //"Posta" uma informação
    @Transactional // Executa uma transação (insert/alter/post)
    public void cadastra(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<ListagemDadosMedicos> lista (@PageableDefault(sort = {"nome"}) Pageable pageable) {

        /*
        Parâmetros passados por url:
            - Ordenando: ...?sort=campoParaOrdenar.
                ex: http://localhost:8080/medicos?sort=nome
            - Limitando: ...?size=qtdeAmostragemPorPagina (padrão = 20)
                ex: http://localhost:8080/medicos?size=2
            - NumPagina: ...?page=paginaDestino (começa contagem do 0)
                ex: http://localhost:8080/medicos?page=1
            -Misturando Parâmetros: ...?parâmetro1=valor&parâmetro2=valor
                ex: http://localhost:8080/medicos?size=2&page=1
         */

        return repository.findAllByAtivoTrue(pageable).map(ListagemDadosMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualiza (@RequestBody @Valid DadosAtualizacao dadosAtualizacao){
        var medico = repository.getReferenceById(dadosAtualizacao.id());
        medico.atualizarInformacoes(dadosAtualizacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta (@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.exclui();
    }
}
