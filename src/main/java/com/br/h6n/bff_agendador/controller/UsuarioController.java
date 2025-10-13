package com.br.h6n.bff_agendador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.h6n.bff_agendador.business.UsuarioService;
import com.br.h6n.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.LoginDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.br.h6n.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.br.h6n.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.br.h6n.bff_agendador.business.dto.out.ViaCepDTOResponse;
import com.br.h6n.bff_agendador.infrastructure.security.SecurityConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
// @CrossOrigin(origins = {"http://localhost:4200"}) // Exemplo de uso do CORS para um controller
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEMA)
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    @PostMapping
    // @CrossOrigin(origins = {"http://localhost:4200"}) // Exemplo de uso do CORS para um método específico
    @Operation(summary = "Salvar usuário", description = "Endpoint para criar um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Erro na validação dos dados ou usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login do usuário", description = "Endpoint para realizar o login de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public String login(@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar usuário por email", description = "Endpoint para buscar um usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuário por email", description = "Endpoint para deletar um usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuário", description = "Endpoint para atualizar um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaUsuario(usuarioDTO, token));
    }

    @PutMapping("/enderecos/{id}")
    @Operation(summary = "Atualizar dados do endereço do usuário", description = "Endpoint para atualizar um endereço")
    @ApiResponse(responseCode = "200", description = "Endereço do usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefones/{id}")
    @Operation(summary = "Atualizar dados do telefone do usuário", description = "Endpoint para atualizar um telefone")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/enderecos")
    @Operation(summary = "Salvar endereço do usuário", description = "Endpoint para salvar um endereço")
    @ApiResponse(responseCode = "200", description = "Endereço do usuário salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(enderecoDTO, token));
    }

    @PostMapping("/telefones")
    @Operation(summary = "Salvar telefone do usuário", description = "Endpoint para salvar um telefone")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(telefoneDTO, token));
    }

    @GetMapping("/enderecos/cep/{cep}")
    @Operation(summary = "Buscar endereço por CEP", description = "Endpoint para buscar um endereço pelo CEP")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "CEP Inválido")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(usuarioService.buscarEnderecoCep(cep));
    }

}
