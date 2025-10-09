package com.br.h6n.bff_agendador.business;

import org.springframework.stereotype.Service;

import com.br.h6n.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.LoginDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.br.h6n.bff_agendador.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaUsuario(UsuarioDTORequest usuarioDTO, String token) {
        return usuarioClient.atualizaUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }
}
