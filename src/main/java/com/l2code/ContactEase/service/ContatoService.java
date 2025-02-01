package com.l2code.ContactEase.service;

import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.exception.ContatoCadastradoException;
import com.l2code.ContactEase.mapper.ContatoMapper;
import com.l2code.ContactEase.repository.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContatoService{

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository){
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public ContatoRespostaDTO cadastrar(@Valid ContatoCadastroDTO dto) {

        if(contatoRepository.findByCelular(dto.celular()).isPresent())
            throw new ContatoCadastradoException();

        var contato = ContatoMapper.toEntity(dto);
        contatoRepository.save(contato);
        return ContatoMapper.toResposta(contato);
    }
}
