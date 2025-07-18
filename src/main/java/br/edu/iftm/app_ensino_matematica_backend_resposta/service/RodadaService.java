package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dtos.RodadaDTO;

import br.edu.iftm.app_ensino_matematica_backend_resposta.converter.RodadaConverter;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.repository.RodadaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RodadaService {

    private final RodadaRepository rodadaRepository;

    public Rodada getRodadaById(UUID id_rodada) {
        return rodadaRepository.findById(id_rodada).orElse(null);
    }

    public List<Rodada> getRodadaByIdCategoriaAndDificuldade(UUID idCategoria, int dificuldade) {
        return rodadaRepository.findByIdCategoriaAndDificuldade(idCategoria, dificuldade);
    }

    public List<Rodada> getRodadaByIdCategoriaAndIdAluno(UUID idCategoria, UUID idAluno) {
        return rodadaRepository.findByIdCategoriaAndIdAluno(idCategoria, idAluno);
    }

    public RodadaDTO saveRodada(RodadaDTO rodadaDTO, List<Resposta> respostas) {
        // Busca a rodada com maior pontuação do aluno
        Rodada rodadaAnterior = rodadaRepository.findTopByIdAlunoOrderByPontuacaoDesc(rodadaDTO.getIdAluno()).orElse(null);

        // Se existe uma rodada anterior e a pontuação atual não é maior, não salva
        if (rodadaAnterior != null && rodadaDTO.getPontuacao() <= rodadaAnterior.getPontuacao()) {
            System.out.println("rodada anterior maior que atual"); // Ou lance uma exceção, ou retorne uma mensagem de erro, conforme sua regra de negócio
        }

        Rodada rodada = new Rodada();
        rodada.setId_rodada(UUID.randomUUID());
        rodada.setIdCategoria(rodadaDTO.getIdCategoria());
        rodada.setIdAluno(rodadaDTO.getIdAluno());
        rodada.setDificuldade(rodadaDTO.getDificuldade());
        rodada.setPontuacao(rodadaDTO.getPontuacao());
        rodada.setRespostas(respostas);
        rodada = rodadaRepository.save(rodada);

        // if (rodadaAnterior != null && rodadaAnterior.getPontuacao() < 60 && rodada.getPontuacao() >= 60 || rodadaAnterior == null && rodada.getPontuacao() > 60) {
        //     String url = "http://localhost:3004/manage/question";
        //     restTemplate.postForObject(url, null, String.class);
        // }
        // Usando o novo converter
        return RodadaConverter.convert(rodada);
    }

    public Rodada getRodadaComMaiorPontuacaoPorCategoriaEDificuldade(UUID idAluno, UUID idCategoria, int dificuldade) {
        return rodadaRepository.findTopByIdAlunoAndIdCategoriaAndDificuldadeOrderByPontuacaoDesc(idAluno, idCategoria, dificuldade).orElse(null);
    }

// Método para buscar melhores rodadas de todos os alunos (para ranking)
    public List<Rodada> getMelhoresRodadasParaRanking() {
        List<Rodada> todasRodadas = rodadaRepository.findAll();

        // Agrupar por aluno + categoria + dificuldade e pegar só a maior pontuação
        Map<String, Rodada> melhoresRodadas = todasRodadas.stream()
                .collect(Collectors.toMap(
                        rodada -> rodada.getIdAluno() + "_" + rodada.getIdCategoria() + "_" + rodada.getDificuldade(),
                        rodada -> rodada,
                        (existente, nova) -> existente.getPontuacao() > nova.getPontuacao() ? existente : nova
                ));

        return new ArrayList<>(melhoresRodadas.values());
    }

}
