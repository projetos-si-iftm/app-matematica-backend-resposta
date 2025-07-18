package br.edu.iftm.app_ensino_matematica_backend_resposta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.RankingDTO;

import br.edu.iftm.app_ensino_matematica_backend_resposta.service.RankingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report/ranking")
public class RankingController {
    
    private static final Logger log = LoggerFactory.getLogger(RankingController.class);
    private final RankingService rankingService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RankingDTO> getRankingGeral() {
        log.info("Calculando ranking geral");
        return rankingService.calcularRankingGeral();
    }
    
    // @GetMapping("/categoria")
    // @ResponseStatus(HttpStatus.OK)
    // public List<RankingDTO> getRankingPorCategoria(@RequestParam UUID idCategoria) {
    //     log.info("Calculando ranking para categoria: {}", idCategoria);
    //     return rankingService.calcularRankingPorCategoria(idCategoria);
    // }
    
    // @GetMapping("/turma")
    // @ResponseStatus(HttpStatus.OK)
    // public List<RankingDTO> getRankingPorTurma(@RequestParam String turmaId) {
    //     log.info("Calculando ranking para turma: {}", turmaId);
    //     return rankingService.calcularRankingPorTurma(turmaId);
    // }
}