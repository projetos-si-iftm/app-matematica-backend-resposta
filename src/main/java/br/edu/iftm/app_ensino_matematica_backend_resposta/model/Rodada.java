package br.edu.iftm.app_ensino_matematica_backend_resposta.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rodada")
public class Rodada {
    @Id
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID id_rodada;
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID idCategoria;
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID idAluno;
    private int dificuldade;
    private List<Resposta> respostas;
    private int pontuacao;

    }