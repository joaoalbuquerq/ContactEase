package com.l2code.ContactEase.mapper;

import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.model.Contato;

import java.time.LocalDateTime;

public class ContatoMapper {

    public static Contato toEntity(ContatoCadastroDTO dto) {
        Contato contato = new Contato();

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());
        contato.setAtivo("S");
        contato.setFavorito("N");
        contato.setDataCadastro(LocalDateTime.now());
        contato.setUltimaAlteracao(LocalDateTime.now());

        return contato;
    }

    public static ContatoRespostaDTO toResposta(Contato contato){
        return new ContatoRespostaDTO(contato.getId(),contato.getNome(), contato.getEmail(), contato.getTelefone(), contato.getCelular());
    }

}
