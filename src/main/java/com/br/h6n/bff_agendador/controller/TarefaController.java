package com.br.h6n.bff_agendador.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.h6n.bff_agendador.business.TarefaService;
import com.br.h6n.bff_agendador.business.dto.in.TarefaDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.TarefaDTOResponse;
import com.br.h6n.bff_agendador.business.enums.StatusNotificacaoEnum;
import com.br.h6n.bff_agendador.infrastructure.security.SecurityConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEMA)
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefa", description = "Endpoint para criar uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> criarTarefa(@RequestBody TarefaDTORequest tarefaDTORequest, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(tarefaDTORequest, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por período", description = "Endpoint para buscar tarefas em um período específico")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
        @RequestHeader(value = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas por e-mail", description = "Endpoint para buscar tarefas por e-mail do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarTarefasPorEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tarefa", description = "Endpoint para deletar uma tarefa pelo ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id, @RequestHeader(value = "Authorization", required = false) String token) {
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Alterar status da tarefa", description = "Endpoint para alterar o status de notificação de uma tarefa pelo ID")
    @ApiResponse(responseCode = "200", description = "Status de notificação alterado com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> alterarStatusNotificacao(@PathVariable String id, @RequestParam StatusNotificacaoEnum status, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(id, status, token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar tarefa", description = "Endpoint para alterar uma tarefa pelo ID")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> atualizarTarefa(@PathVariable String id, @RequestBody TarefaDTORequest tarefaDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.updateTarefa(id, tarefaDTO, token));
    }

}
