package br.edu.iftm.app_ensino_matematica_backend_resposta.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {
    @Id
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID id_resposta;
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID id_questao;
    private Boolean isCorrect;
}
