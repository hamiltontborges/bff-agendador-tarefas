package com.br.h6n.bff_agendador.infrastructure.client.config;

import java.io.IOException;
import java.util.Objects;

import com.br.h6n.bff_agendador.infrastructure.exceptions.BusinessException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.ConflictException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.IllegalArgumentException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.UnauthorizedException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder{

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);
        String erro = "ERROR: ";
        
        switch (response.status()) {
            case 409:
                return new ConflictException(erro + mensagemErro);
            case 403:
                return new ResourceNotFoundException(erro + mensagemErro);
            case 401:
                return new UnauthorizedException(erro + mensagemErro);
            case 400:
                return new IllegalArgumentException(erro + mensagemErro);
            default:
                return new BusinessException(erro + mensagemErro);
        }
    }

    private String mensagemErro(Response response){
        try {
            if(Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } 
}
