package br.edu.iftm.app_ensino_matematica_backend_resposta.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;

public interface RodadaRepository extends MongoRepository<Rodada, UUID> {
    List<Rodada> findByIdCategoriaAndDificuldade(UUID id_categoria, int dificuldade);
}