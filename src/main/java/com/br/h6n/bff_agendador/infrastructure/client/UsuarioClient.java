package com.br.h6n.bff_agendador.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.h6n.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.LoginDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.UsuarioDTOResponse;

@FeignClient(name = "usuarios", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuarios")
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping("/usuarios")
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/usuarios/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/usuarios/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios")
    UsuarioDTOResponse atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios/enderecos/{id}")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @PathVariable Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/usuarios/telefones/{id}")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @PathVariable Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/usuarios/enderecos")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader("Authorization") String token);

    @PostMapping("/usuarios/telefones")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader("Authorization") String token);
}
