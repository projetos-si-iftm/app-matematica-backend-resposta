// package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Resposta;
// import br.edu.iftm.app_ensino_matematica_backend_resposta.model.DTO.RespostaDTO;
// import br.edu.iftm.app_ensino_matematica_backend_resposta.repository.RespostaRepository;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class RespostaService {
//     private final RespostaRepository respostaRepository;

//     // public List<RespostaDTO> saveRespostas(List<RespostaDTO> respostaDTOs) {
//     //     List<Resposta> respostas = respostaDTOs.stream().map(respostaDTO -> {
//     //         Resposta resposta = new Resposta();
//     //         resposta.setId_resposta(UUID.randomUUID()); // Gera um UUID aleatório para id_resposta
//     //         resposta.setId_aluno(respostaDTO.getId_aluno());
//     //         resposta.setId_questao(respostaDTO.getId_questao());
//     //         resposta.setAlternativa_escolhida(respostaDTO.getAlternativa_escolhida());
//     //         resposta.setIsCorrect(respostaDTO.getIsCorrect());
//     //         return resposta;
//     //     }).collect(Collectors.toList());

//     //     List<Resposta> savedRespostas = respostaRepository.saveAll(respostas);
//     //     return savedRespostas.stream().map(RespostaDTO::convert).collect(Collectors.toList());
//     // }
