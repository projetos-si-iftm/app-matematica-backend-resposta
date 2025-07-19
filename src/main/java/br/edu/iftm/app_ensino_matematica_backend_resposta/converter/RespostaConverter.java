package br.edu.iftm.app_ensino_matematica_backend_resposta.converter;
import java.util.UUID;

import com.example.dtos.RespostaDTO;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
public class RespostaConverter {
    
    public static RespostaDTO convert(Resposta resposta) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId_resposta(resposta.getId_resposta());
        respostaDTO.setId_questao(resposta.getId_questao());
        respostaDTO.setIsCorrect(resposta.getIsCorrect());
        return respostaDTO;
    }

    public static Resposta convertToEntity(RespostaDTO respostaDTO) {
        Resposta resposta = new Resposta();
        resposta.setId_resposta(UUID.randomUUID());
        resposta.setId_questao(respostaDTO.getId_questao());
        resposta.setIsCorrect(respostaDTO.getIsCorrect());
        return resposta;
    }
}
