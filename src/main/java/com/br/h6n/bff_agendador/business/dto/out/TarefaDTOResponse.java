package com.br.h6n.bff_agendador.business.dto.out;

import java.time.LocalDateTime;

import com.br.h6n.bff_agendador.infrastructure.client.enums.StatusNotificacaoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTOResponse {
    private String id;
    private String nomeTarefa;
    private String descricao;
    private String emailUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
