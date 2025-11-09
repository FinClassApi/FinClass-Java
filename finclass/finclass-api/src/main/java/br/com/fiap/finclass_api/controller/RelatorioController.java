package br.com.fiap.finclass_api.controller;

import br.com.fiap.finclass_api.dto.ResumoFinanceiroResponse;
import br.com.fiap.finclass_api.service.RelatorioService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/resumo/{usuarioId}")
    public EntityModel<ResumoFinanceiroResponse> resumo(@PathVariable Long usuarioId) {
        ResumoFinanceiroResponse resumo = relatorioService.gerarResumo(usuarioId);

        EntityModel<ResumoFinanceiroResponse> model = EntityModel.of(resumo);
        Link self = linkTo(methodOn(RelatorioController.class).resumo(usuarioId)).withSelfRel();
        Link despesas = linkTo(methodOn(DespesaController.class).listarPorUsuario(usuarioId)).withRel("despesas");
        Link metas = linkTo(methodOn(MetaFinanceiraController.class).listarPorUsuario(usuarioId)).withRel("metas");

        model.add(self, despesas, metas);
        return model;
    }
}
