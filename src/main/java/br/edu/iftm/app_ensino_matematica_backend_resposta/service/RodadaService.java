package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO.RodadaDTO;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.repository.RodadaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RodadaService {

    private final RodadaRepository rodadaRepository;
    private final RestTemplate restTemplate = new RestTemplate();

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
        return RodadaDTO.convert(rodada);
    }

    public Rodada getRodadaComMaiorPontuacao(UUID idAluno) {
        return rodadaRepository.findTopByIdAlunoOrderByPontuacaoDesc(idAluno).orElse(null);
    }
}
