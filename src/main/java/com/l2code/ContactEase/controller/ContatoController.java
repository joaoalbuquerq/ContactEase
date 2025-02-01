package com.l2code.ContactEase.controller;

import com.l2code.ContactEase.dto.ContatoAtualizacaoDTO;
import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar")
    public ResponseEntity<List<ContatoRespostaDTO>> buscarContatos(
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String celular,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long id) {
        return ResponseEntity.ok(contatoService.buscarContatos(telefone, celular, email, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoRespostaDTO> atualizarContato(@PathVariable Long id, @Valid @RequestBody ContatoAtualizacaoDTO dto) {
        return ResponseEntity.ok(contatoService.atualizarContato(id, dto));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarContato(@PathVariable Long id) {
        contatoService.inativarContato(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/favoritar")
    public ResponseEntity<Void> favoritarContato(@PathVariable Long id) {
        contatoService.favoritarContato(id);
        return ResponseEntity.noContent().build();
    }
}
