package com.br.h6n.bff_agendador.business;

import org.springframework.stereotype.Service;

import com.br.h6n.bff_agendador.business.dto.out.TarefaDTOResponse;
import com.br.h6n.bff_agendador.infrastructure.client.EmailClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviarEmail(TarefaDTOResponse tarefaDTO) {
        emailClient.enviarEmail(tarefaDTO);
    }
}
