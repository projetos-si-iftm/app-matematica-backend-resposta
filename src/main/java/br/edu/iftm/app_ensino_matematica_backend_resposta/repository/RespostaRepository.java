package br.edu.iftm.app_ensino_matematica_backend_resposta.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;

public interface RespostaRepository extends MongoRepository<Resposta, UUID> {
    
}
