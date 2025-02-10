package br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO;

import java.util.UUID;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaDTO {
    private UUID id_resposta;
    private UUID id_aluno;
    private UUID id_questao;
    private UUID alternativa_escolhida;
    private Boolean isCorrect;

    public static RespostaDTO convert(Resposta resposta) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId_resposta(resposta.getId_resposta());
        respostaDTO.setId_aluno(resposta.getId_aluno());
        respostaDTO.setId_questao(resposta.getId_questao());
        respostaDTO.setAlternativa_escolhida(resposta.getAlternativa_escolhida());
        respostaDTO.setIsCorrect(resposta.getIsCorrect());
        return respostaDTO;
    }
}
