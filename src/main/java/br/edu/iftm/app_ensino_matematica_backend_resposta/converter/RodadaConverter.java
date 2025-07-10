package br.edu.iftm.app_ensino_matematica_backend_resposta.converter;
import java.util.stream.Collectors;

import com.example.dtos.*;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
public class RodadaConverter {
     public static RodadaDTO convert(Rodada rodada) {
        RodadaDTO rodadaDTO = new RodadaDTO();
        rodadaDTO.setId_rodada(rodada.getId_rodada());
        rodadaDTO.setIdCategoria(rodada.getIdCategoria());
        rodadaDTO.setIdAluno(rodada.getIdAluno());
        rodadaDTO.setDificuldade(rodada.getDificuldade());
        rodadaDTO.setPontuacao(rodada.getPontuacao());
        
        // Converter List<Resposta> para List<RespostaDTO>
        rodadaDTO.setRespostas(rodada.getRespostas().stream()
            .map(RespostaConverter::convert)
            .collect(Collectors.toList()));
        
        return rodadaDTO;
    }

    public static Rodada convertToEntity(RodadaDTO rodadaDTO) {
        Rodada rodada = new Rodada();
        rodada.setId_rodada(rodadaDTO.getId_rodada());
        rodada.setIdCategoria(rodadaDTO.getIdCategoria());
        rodada.setIdAluno(rodadaDTO.getIdAluno());
        rodada.setDificuldade(rodadaDTO.getDificuldade());
        rodada.setPontuacao(rodadaDTO.getPontuacao());
        
        // Converter List<RespostaDTO> para List<Resposta>
        rodada.setRespostas(rodadaDTO.getRespostas().stream()
            .map(RespostaConverter::convertToEntity)
            .collect(Collectors.toList()));
        
        return rodada;
    }
}
