package com.br.h6n.bff_agendador.infrastructure.client;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.h6n.bff_agendador.business.dto.in.TarefaDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.TarefaDTOResponse;
import com.br.h6n.bff_agendador.business.enums.StatusNotificacaoEnum;


@FeignClient(name = "tarefas", url = "${agendador-tarefas.url}")
public interface TarefaClient {

    @PostMapping("/tarefas")
    TarefaDTOResponse criarTarefa(@RequestBody TarefaDTORequest tarefaDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/tarefas/eventos")
    List<TarefaDTOResponse> buscarTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
        @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping("/tarefas/{id}")
    void deletarTarefa(@PathVariable String id, @RequestHeader("Authorization") String token);

    @PatchMapping("/tarefas/{id}")
    TarefaDTOResponse alterarStatusNotificacao(@PathVariable
            String id, @RequestParam
                    StatusNotificacaoEnum status, @RequestHeader(value = "Authorization")
                            String token);

    @PutMapping("/tarefas/{id}")
    TarefaDTOResponse atualizarTarefa(@PathVariable String id, @RequestBody TarefaDTORequest tarefaDTO, @RequestHeader("Authorization") String token);

}
