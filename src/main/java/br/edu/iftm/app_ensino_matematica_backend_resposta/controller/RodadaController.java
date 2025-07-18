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

import com.example.dtos.*;

import br.edu.iftm.app_ensino_matematica_backend_resposta.converter.RespostaConverter;
import br.edu.iftm.app_ensino_matematica_backend_resposta.converter.RodadaConverter;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.service.RodadaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report/round")
public class RodadaController {

    private static final Logger log = LoggerFactory.getLogger(RodadaController.class);
    private final RodadaService rodadaService;

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

    @GetMapping("/{id_rodada}")
    @ResponseStatus(HttpStatus.OK)
    public RodadaDTO getRodadaById(@PathVariable UUID id_rodada) {
        Rodada rodada = rodadaService.getRodadaById(id_rodada);
        return RodadaConverter.convert(rodada);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<RodadaDTO> getRodadaByIdCategoriaAndDificuldade(@RequestParam UUID idCategoria, @RequestParam int dificuldade) {
        List<Rodada> rodadas = rodadaService.getRodadaByIdCategoriaAndDificuldade(idCategoria, dificuldade);
        return rodadas.stream()
                .map(RodadaConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchByCategoriaAndAluno")
    @ResponseStatus(HttpStatus.OK)
    public List<RodadaDTO> getRodadaByIdCategoriaAndIdAluno(@RequestParam UUID idCategoria, @RequestParam UUID idAluno) {
        List<Rodada> rodadas = rodadaService.getRodadaByIdCategoriaAndIdAluno(idCategoria, idAluno);
        return rodadas.stream()
                .map(RodadaConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/highestScore/categoria")
    @ResponseStatus(HttpStatus.OK)
    public RodadaDTO getRodadaComMaiorPontuacaoPorCategoria(
            @RequestParam UUID idAluno,
            @RequestParam UUID idCategoria,
            @RequestParam int dificuldade) {

        Rodada rodada = rodadaService.getRodadaComMaiorPontuacaoPorCategoriaEDificuldade(idAluno, idCategoria, dificuldade);
        return rodada != null ? RodadaConverter.convert(rodada) : null;
    }
}
