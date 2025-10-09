package com.br.h6n.bff_agendador.business;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.h6n.bff_agendador.business.dto.in.LoginDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.TarefaDTOResponse;
import com.br.h6n.bff_agendador.business.enums.StatusNotificacaoEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefaDTOResponse> tarefas = tarefaService.buscaTarefasAgendasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas: {}", tarefas);
        tarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("Tarefa {} enviada. Email enviado para o usuario {}", tarefa.getId(), tarefa.getEmailUsuario());
            tarefaService.alteraStatus(tarefa.getId(), StatusNotificacaoEnum.NOTIFICADO, token);
        });
        log.info("Busca de tarefas e notificações finalizadas");
    }

    public String login(LoginDTORequest loginDTORequest){
        return usuarioService.loginUsuario(loginDTORequest);
    }

    public LoginDTORequest converterParaRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
