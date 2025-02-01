package com.l2code.ContactEase.service;

import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.exception.ContatoCadastradoException;
import com.l2code.ContactEase.mapper.ContatoMapper;
import com.l2code.ContactEase.model.Contato;
import com.l2code.ContactEase.repository.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ContatoRespostaDTO> listarContatosAtivos(String ativo) {

        List<Contato> contatos = contatoRepository.findByAtivoOptional(ativo);
        return contatos.stream()
                .map(ContatoMapper::toResposta)
                .collect(Collectors.toList());
    }

    public List<ContatoRespostaDTO> buscarContatos(String telefone, String celular, String email, Long id) {
        List<Contato> contatos = contatoRepository.buscarContatos(telefone, celular, email, id);
        return contatos.stream()
                .map(ContatoMapper::toResposta)
                .collect(Collectors.toList());
    }
}
