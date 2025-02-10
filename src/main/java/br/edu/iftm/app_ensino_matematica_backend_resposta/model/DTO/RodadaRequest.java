package br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO;

import java.util.List;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RodadaRequest {
    private RodadaDTO rodada;
    private List<Resposta> respostas;
}