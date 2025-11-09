package br.com.fiap.finclass_api.controller;

import br.com.fiap.finclass_api.dto.MetaRequest;
import br.com.fiap.finclass_api.dto.MetaResponse;
import br.com.fiap.finclass_api.service.MetaFinanceiraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metas")
public class MetaFinanceiraController {

    private final MetaFinanceiraService metaService;

    public MetaFinanceiraController(MetaFinanceiraService metaService) {
        this.metaService = metaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MetaResponse criar(@RequestBody @Valid MetaRequest request) {
        return metaService.criar(request);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<MetaResponse> listarPorUsuario(@PathVariable Long usuarioId) {
        return metaService.listarPorUsuario(usuarioId);
    }
}
