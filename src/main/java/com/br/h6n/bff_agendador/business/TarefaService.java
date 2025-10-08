package com.br.h6n.bff_agendador.business;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.h6n.bff_agendador.business.dto.in.TarefaDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.TarefaDTOResponse;
import com.br.h6n.bff_agendador.infrastructure.client.TarefaClient;
import com.br.h6n.bff_agendador.infrastructure.client.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa(TarefaDTORequest tarefaDTO, String token) {
        return tarefaClient.criarTarefa(tarefaDTO, token);
    }

   public List<TarefaDTOResponse> buscaTarefasAgendasPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String token) {
       return tarefaClient.buscarTarefasPorPeriodo(inicio, fim, token);
   }

   public List<TarefaDTOResponse> buscaTarefasPorEmail(String token) {
       return tarefaClient.buscarTarefasPorEmail(token);
   }

   public void deletaTarefaPorId(String id, String token) {
       tarefaClient.deletarTarefa(id, token);
   }

   public TarefaDTOResponse alteraStatus(String id, StatusNotificacaoEnum status, String token) {
        return tarefaClient.alterarStatusNotificacao(id, status, token);
   }

   public TarefaDTOResponse updateTarefa(String id, TarefaDTORequest tarefaDTO, String token) {
       return tarefaClient.atualizarTarefa(id, tarefaDTO, token);
   }
}
