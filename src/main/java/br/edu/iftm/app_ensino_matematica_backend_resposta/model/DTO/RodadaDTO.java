package br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO;

import java.util.List;
import java.util.UUID;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RodadaDTO {
        private UUID id_rodada;
        private UUID idCategoria;
        private UUID idAluno;
        private int dificuldade;
        private List<Resposta> respostas;
        private int pontuacao;
        
        public static RodadaDTO convert(Rodada rodada) {
            RodadaDTO rodadaDTO = new RodadaDTO();
            rodadaDTO.setId_rodada(rodada.getId_rodada());
            rodadaDTO.setRespostas(rodada.getRespostas());
            rodadaDTO.setIdAluno(rodada.getIdAluno());
            rodadaDTO.setPontuacao(rodada.getPontuacao());
            rodadaDTO.setIdCategoria(rodada.getIdCategoria());
            rodadaDTO.setDificuldade(rodada.getDificuldade());
            return rodadaDTO;
        }
}


