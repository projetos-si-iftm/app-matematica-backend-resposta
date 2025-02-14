package br.edu.iftm.app_ensino_matematica_backend_resposta.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO.RodadaDTO;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO.RodadaRequest;
import br.edu.iftm.app_ensino_matematica_backend_resposta.service.RodadaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report/round")
public class RodadaController {
    private final RodadaService rodadaService;

    @PostMapping
    public RodadaDTO saveRodada(@RequestBody RodadaRequest rodadaRequest) {
        return rodadaService.saveRodada(rodadaRequest.getRodada(), rodadaRequest.getRespostas());
    }

    @GetMapping("/{id_rodada}")
    public RodadaDTO getRodadaById(@PathVariable UUID id_rodada) {
        return RodadaDTO.convert(rodadaService.getRodadaById(id_rodada));
    }

    @GetMapping("/search")
    public List<Rodada> getRodadaByIdCategoriaAndDificuldade(@RequestParam UUID idCategoria, @RequestParam int dificuldade) {
        return rodadaService.getRodadaByIdCategoriaAndDificuldade(idCategoria, dificuldade);
    }

    @GetMapping("/searchByCategoriaAndAluno")
    public List<Rodada> getRodadaByIdCategoriaAndIdAluno(@RequestParam UUID idCategoria, @RequestParam UUID idAluno) {
        return rodadaService.getRodadaByIdCategoriaAndIdAluno(idCategoria, idAluno);
    }
}
