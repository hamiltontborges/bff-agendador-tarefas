package com.br.h6n.bff_agendador.infrastructure.client.config;

import com.br.h6n.bff_agendador.infrastructure.exceptions.BusinessException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.ConflictException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.br.h6n.bff_agendador.infrastructure.exceptions.UnauthorizedException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder{

    @Override
    public Exception decode(String s, Response response) {
        
        switch (response.status()) {
            case 409:
                return new ConflictException("ERROR atributo já existente");
            case 403:
                return new ResourceNotFoundException("ERROR atributo não encontrado");
            case 401:
                return new UnauthorizedException("ERROR acesso não autorizado");
            default:
                return new BusinessException("ERROR interno do servidor");
        }
    }

}
