package br.edu.iftm.app_ensino_matematica_backend_resposta.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;

@Repository
public interface RodadaRepository extends MongoRepository<Rodada, UUID> {

    List<Rodada> findByIdCategoriaAndDificuldade(UUID idCategoria, int dificuldade);

    List<Rodada> findByIdCategoriaAndIdAluno(UUID idCategoria, String idAluno);

    Optional<Rodada> findTopByIdAlunoOrderByPontuacaoDesc(String idAluno);

    @Query("{ 'idAluno': ?0, 'idCategoria': ?1, 'dificuldade': ?2 }")
    Optional<Rodada> findTopByIdAlunoAndIdCategoriaAndDificuldadeOrderByPontuacaoDesc(String idAluno, UUID idCategoria, int dificuldade);
}
