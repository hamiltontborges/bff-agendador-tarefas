package com.br.h6n.bff_agendador.business.dto.in;

import java.time.LocalDateTime;

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
public class TarefaDTORequest {
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataEvento;
}
