package br.com.startrip.usuarios.factories;

import br.com.startrip.usuarios.controller.request.AtualizarUsuarioRequest;

import java.time.LocalDate;

public class AtualizarUsuarioRequestFactory {
    public static AtualizarUsuarioRequest criaRequest() {
        return AtualizarUsuarioRequest.builder()
                .nome("Usuario Alterado")
                .email("usuario@alterado.com.br")
                .senha("654321")
                .dataNascimento(LocalDate.of(1, 1, 1))
                .endereco(EnderecoFactory.criaEndereco())
                .build();
    }
}
