package br.com.fiap.finclass_api.controller;

import br.com.fiap.finclass_api.dto.DespesaRequest;
import br.com.fiap.finclass_api.dto.DespesaResponse;
import br.com.fiap.finclass_api.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DespesaResponse criar(@RequestBody @Valid DespesaRequest request) {
        return despesaService.criar(request);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<DespesaResponse> listarPorUsuario(@PathVariable Long usuarioId) {
        return despesaService.listarPorUsuario(usuarioId);
    }
}
