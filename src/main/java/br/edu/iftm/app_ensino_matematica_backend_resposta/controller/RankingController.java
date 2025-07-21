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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Ranking", description = "Operações relacionadas ao ranking dos estudantes")
@RequestMapping("/relatorio/ranking")
public class RankingController {

    private static final Logger log = LoggerFactory.getLogger(RankingController.class);
    private final RankingService rankingService;

    @Operation(summary = "Calcula o ranking geral dos estudantes",
            description = "Retorna uma lista de RankingDTO com a posição, ID do aluno e pontuação", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ranking calculado com sucesso"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Ranking não encontrado"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente"),})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RankingDTO> getRankingGeral() {
        log.info("Calculando ranking geral");
        return rankingService.calcularRankingGeral();
    }

}
