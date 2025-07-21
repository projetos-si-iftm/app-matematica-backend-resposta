package br.edu.iftm.app_ensino_matematica_backend_resposta.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.RodadaDTO;
import com.example.dtos.RodadaRequestDTO;

import br.edu.iftm.app_ensino_matematica_backend_resposta.converter.RespostaConverter;
import br.edu.iftm.app_ensino_matematica_backend_resposta.converter.RodadaConverter;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.service.RodadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rodada", description = "Operações relacionadas às rodadas do jogo")
@RequestMapping("/relatorio/rodada")
public class RodadaController {

    private static final Logger log = LoggerFactory.getLogger(RodadaController.class);
    private final RodadaService rodadaService;

    @Operation(summary = "Cria uma nova rodada",
            description = "Recebe uma RodadaRequestDTO e retorna a RodadaDTO criada", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Rodada criada com sucesso"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RodadaDTO saveRodada(@RequestBody RodadaRequestDTO rodadaRequest) {
        log.info("Recebendo requisição: {}", rodadaRequest);

        // Converter List<RespostaDTO> para List<Resposta>
        List<Resposta> respostas = rodadaRequest.getRespostas().stream()
                .map(RespostaConverter::convertToEntity)
                .collect(Collectors.toList());

        return rodadaService.saveRodada(rodadaRequest.getRodada(), respostas);
    }

    @Operation(summary = "Busca rodada por ID",
            description = "Recebe um ID de rodada e retorna a RodadaDTO correspondente", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rodada encontrada"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rodada não encontrada"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")})
    @GetMapping("/{id_rodada}")
    @ResponseStatus(HttpStatus.OK)
    public RodadaDTO getRodadaById(@PathVariable UUID id_rodada) {
        Rodada rodada = rodadaService.getRodadaById(id_rodada);
        return RodadaConverter.convert(rodada);
    }

    @Operation(summary = "Busca rodadas por ID de categoria e dificuldade",
            description = "Recebe um ID de categoria e dificuldade, retorna uma lista de RodadaDTO", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rodadas encontradas"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rodadas não encontradas"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")})
    @GetMapping("/busca")
    @ResponseStatus(HttpStatus.OK)
    public List<RodadaDTO> getRodadaByIdCategoriaAndDificuldade(@RequestParam UUID idCategoria, @RequestParam int dificuldade) {
        List<Rodada> rodadas = rodadaService.getRodadaByIdCategoriaAndDificuldade(idCategoria, dificuldade);
        return rodadas.stream()
                .map(RodadaConverter::convert)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Busca rodadas por ID de categoria e ID de aluno",
            description = "Recebe um ID de categoria e um ID de aluno, retorna uma lista de RodadaDTO", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rodadas encontradas"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rodadas não encontradas"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")})
    @GetMapping("/buscaPorCategoriaEAluno")
    @ResponseStatus(HttpStatus.OK)
    public List<RodadaDTO> getRodadaByIdCategoriaAndIdAluno(@RequestParam UUID idCategoria, @RequestParam String idAluno) {
        List<Rodada> rodadas = rodadaService.getRodadaByIdCategoriaAndIdAluno(idCategoria, idAluno);
        return rodadas.stream()
                .map(RodadaConverter::convert)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Busca rodada com maior pontuação por ID de aluno, ID de categoria e dificuldade",
            description = "Recebe um ID de aluno, ID de categoria e dificuldade, retorna a RodadaDTO com maior pontuação", responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Rodada encontrada"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Rodada não encontrada"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")})
    @GetMapping("/maiorPontuacao/categoria")
    @ResponseStatus(HttpStatus.OK)
    public RodadaDTO getRodadaComMaiorPontuacaoPorCategoria(
            @RequestParam String idAluno,
            @RequestParam UUID idCategoria,
            @RequestParam int dificuldade) {

        Rodada rodada = rodadaService.getRodadaComMaiorPontuacaoPorCategoriaEDificuldade(idAluno, idCategoria, dificuldade);
        return rodada != null ? RodadaConverter.convert(rodada) : null;
    }
}
