package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dtos.EstudanteDTO;
import com.example.dtos.RankingDTO;
import com.example.dtos.TurmaDTO;

import br.edu.iftm.app_ensino_matematica_backend_resposta.model.Rodada;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final RodadaService rodadaService;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String USUARIO_SERVICE_URL = "http://usuario";

    public List<RankingDTO> calcularRankingGeral(){
        List<Rodada> melhoresRodadas = rodadaService.getMelhoresRodadasParaRanking();

        // FILTRAR rodadas com idAluno nulo antes do agrupamento
        Map<UUID, Integer> pontuacoesPorAluno = melhoresRodadas.stream()
            .filter(rodada -> rodada.getIdAluno() != null) // ← ADICIONAR ESTE FILTRO
            .collect(Collectors.groupingBy(
                Rodada::getIdAluno,
                Collectors.summingInt(Rodada::getPontuacao)
            ));
        
        // Criar ranking com dados enriquecidos
        AtomicInteger posicao = new AtomicInteger(1);
        return pontuacoesPorAluno.entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
            .map(entry -> {
                RankingDTO ranking = RankingDTO.builder()
                    .idAluno(entry.getKey().toString())
                    .pontuacao(entry.getValue())
                    .posicao(posicao.getAndIncrement())
                    .build();
                
                // Enriquecer com dados do microsserviço de usuário
                enriquecerComDadosUsuario(ranking);
                
                return ranking;
            })
            .collect(Collectors.toList());
    }
    
    private void enriquecerComDadosUsuario(RankingDTO ranking) {
        try {
            // Buscar dados do aluno
            String alunoUrl = USUARIO_SERVICE_URL + "/api/v1/estudante/" + ranking.getIdAluno();
            EstudanteDTO estudante = restTemplate.getForObject(alunoUrl, EstudanteDTO.class);
            
            if (estudante != null) {
                ranking.setNomeAluno(estudante.getNome());
                ranking.setMatricula(estudante.getMatricula());
                ranking.setFotoUrl(estudante.getFotoUrl());
                ranking.setTurmaId(estudante.getTurmaId().toString());
                
                // Buscar nome da turma - CORRIGIR: faltava "/" antes de "api"
                String turmaUrl = USUARIO_SERVICE_URL + "/api/v1/turma/" + estudante.getTurmaId();
                TurmaDTO turma = restTemplate.getForObject(turmaUrl, TurmaDTO.class);
                
                if (turma != null) {
                    ranking.setNomeTurma(turma.getNomeTurma());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados do aluno " + ranking.getIdAluno() + ": " + e.getMessage());
        }
    }
}