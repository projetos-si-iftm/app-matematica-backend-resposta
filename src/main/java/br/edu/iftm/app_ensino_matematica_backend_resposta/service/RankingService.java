package br.edu.iftm.app_ensino_matematica_backend_resposta.service;

import java.util.List;
import java.util.Map;
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

    private static final String USUARIO_SERVICE_URL = "http://localhost:8080";

    public List<RankingDTO> calcularRankingGeral(){
        List<Rodada> melhoresRodadas = rodadaService.getMelhoresRodadasParaRanking();

        // FILTRAR rodadas com idAluno nulo e agrupar por String (não UUID)
        Map<String, Integer> pontuacoesPorAluno = melhoresRodadas.stream()
            .filter(rodada -> rodada.getIdAluno() != null)
            .collect(Collectors.groupingBy(
                Rodada::getIdAluno,  // Agora retorna String
                Collectors.summingInt(Rodada::getPontuacao)
            ));
        
        // Criar ranking com dados enriquecidos
        AtomicInteger posicao = new AtomicInteger(1);
        return pontuacoesPorAluno.entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
            .map(entry -> {
                RankingDTO ranking = RankingDTO.builder()
                    .idAluno(entry.getKey())  // Já é String, não precisa de toString()
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
            // ← CORRIGIR: URL COMPLETA com path base do controller
            String alunoUrl = USUARIO_SERVICE_URL + "/api/v1/estudante/" + ranking.getIdAluno();
            
            System.out.println("=== DEBUG RANKING SERVICE ===");
            System.out.println("URL do estudante: " + alunoUrl);
            
            EstudanteDTO estudante = restTemplate.getForObject(alunoUrl, EstudanteDTO.class);
            
            System.out.println("Estudante retornado: " + estudante);
            
            if (estudante != null) {
                ranking.setNomeAluno(estudante.getNome());
                ranking.setTurmaId(estudante.getTurmaId().toString());
                
                // ← CORRIGIR: URL COMPLETA para turma
                String turmaUrl = USUARIO_SERVICE_URL + "/api/v1/turma/" + estudante.getTurmaId();
                System.out.println("URL da turma: " + turmaUrl);
                
                TurmaDTO turma = restTemplate.getForObject(turmaUrl, TurmaDTO.class);
                
                System.out.println("Turma retornada: " + turma);
                
                if (turma != null) {
                    ranking.setNomeTurma(turma.getNomeTurma());
                }
            } else {
                System.out.println("Estudante retornou NULL");
            }
            
            System.out.println("=== FIM DEBUG ===");
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados do aluno " + ranking.getIdAluno() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}