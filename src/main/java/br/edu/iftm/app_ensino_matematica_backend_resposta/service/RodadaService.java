package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO.RodadaDTO;
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
        Rodada rodada = new Rodada();
        rodada.setId_rodada(UUID.randomUUID()); // Gera um UUID aleatório para id_rodada
        rodada.setIdCategoria(rodadaDTO.getIdCategoria());
        rodada.setIdAluno(rodadaDTO.getIdAluno());
        rodada.setDificuldade(rodadaDTO.getDificuldade());
        rodada.setPontuacao(rodadaDTO.getPontuacao());
        rodada.setRespostas(respostas); // Adiciona a lista de respostas
        rodada = rodadaRepository.save(rodada);
        return RodadaDTO.convert(rodada);
    }
}
