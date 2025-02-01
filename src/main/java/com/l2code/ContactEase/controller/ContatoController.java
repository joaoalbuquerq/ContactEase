package com.l2code.ContactEase.controller;

import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService service){
        this.contatoService = service;
    }

    @PostMapping
    public ResponseEntity<ContatoRespostaDTO> cadastrar(@Valid @RequestBody ContatoCadastroDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<?> listarContatosAtivos(@RequestParam(value = "ativo", required = false) String ativo){
        return ResponseEntity.ok(contatoService.listarContatosAtivos(ativo));
    }
}
