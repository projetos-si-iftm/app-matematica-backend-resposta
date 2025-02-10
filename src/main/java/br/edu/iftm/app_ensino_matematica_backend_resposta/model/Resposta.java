package br.edu.iftm.app_ensino_matematica_backend_resposta.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resposta")
public class Resposta {
    @Id
    private UUID id_resposta;
    private UUID id_aluno;
    private UUID id_questao;
    private UUID alternativa_escolhida;
    private Boolean isCorrect;
}
