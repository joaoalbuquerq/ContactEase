package com.l2code.ContactEase;

import com.l2code.ContactEase.dto.ContatoAtualizacaoDTO;
import com.l2code.ContactEase.dto.ContatoCadastroDTO;
import com.l2code.ContactEase.dto.ContatoRespostaDTO;
import com.l2code.ContactEase.exception.ContatoCadastradoException;
import com.l2code.ContactEase.exception.ContatoNaoEncontradoException;
import com.l2code.ContactEase.model.Contato;
import com.l2code.ContactEase.repository.ContatoRepository;
import com.l2code.ContactEase.service.ContatoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ContactEaseApplicationTests {

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private ContatoRepository contatoRepository;

	private ContatoCadastroDTO contatoCadastroDTO;

	@BeforeEach
	void setUp() {
		// Criação de um DTO válido para cadastro
		contatoCadastroDTO = new ContatoCadastroDTO("João", "joao@gmail.com", "11987654321", "1187654321");
	}

	@Test
	void testCadastrarContato_Sucesso() {
		// Executa o método de cadastro
		ContatoRespostaDTO contatoRespostaDTO = contatoService.cadastrar(contatoCadastroDTO);

		// Verifica se o contato foi salvo corretamente
		assertNotNull(contatoRespostaDTO);
		assertEquals(contatoCadastroDTO.nome(), contatoRespostaDTO.nome());
		assertTrue(contatoRepository.existsById(contatoRespostaDTO.id()));
	}

	@Test
	void testCadastrarContato_ContatoJaCadastrado() {
		contatoService.cadastrar(contatoCadastroDTO);
		ContatoCadastroDTO dtoDuplicado = new ContatoCadastroDTO("João", "joao2@gmail.com", "11987654321", "11987654321");
		assertThrows(ContatoCadastradoException.class, () -> contatoService.cadastrar(dtoDuplicado));
	}

	@Test
	void testAtualizarContato_Sucesso() {
		ContatoRespostaDTO contatoRespostaDTO = contatoService.cadastrar(contatoCadastroDTO);
		ContatoAtualizacaoDTO contatoAtualizacaoDTO = new ContatoAtualizacaoDTO("João Atualizado", "joao_atualizado@gmail.com", "11987654321", null);
		ContatoRespostaDTO contatoAtualizado = contatoService.atualizarContato(contatoRespostaDTO.id(), contatoAtualizacaoDTO);

		assertEquals(contatoAtualizacaoDTO.nome(), contatoAtualizado.nome());
		assertEquals(contatoAtualizacaoDTO.email(), contatoAtualizado.email());
	}

	@Test
	void testAtualizarContato_ContatoNaoEncontrado() {
		ContatoAtualizacaoDTO contatoAtualizacaoDTO = new ContatoAtualizacaoDTO("Contato Inexistente", "inexistente@gmail.com", "11987654321", null);
		assertThrows(ContatoNaoEncontradoException.class, () -> contatoService.atualizarContato(999L, contatoAtualizacaoDTO));
	}

	@Test
	void testListarContatosAtivos() {
		contatoService.cadastrar(contatoCadastroDTO);
		ContatoCadastroDTO contatoCadastroDTO2 = new ContatoCadastroDTO("Maria", "maria@gmail.com", "11987654322", "1187654322");
		contatoService.cadastrar(contatoCadastroDTO2);

		List<ContatoRespostaDTO> contatos = contatoService.listarContatosAtivos("S");

		assertNotNull(contatos);
		assertEquals(2, contatos.size());
	}

	@Test
	void testInativarContato() {
		ContatoRespostaDTO contatoRespostaDTO = contatoService.cadastrar(contatoCadastroDTO);
		contatoService.inativarContato(contatoRespostaDTO.id());

		Contato contato = contatoRepository.findById(contatoRespostaDTO.id()).orElseThrow();
		assertEquals("N", contato.getAtivo());
	}

	@Test
	void testFavoritarContato() {
		ContatoRespostaDTO contatoRespostaDTO = contatoService.cadastrar(contatoCadastroDTO);
		contatoService.favoritarContato(contatoRespostaDTO.id());

		Contato contato = contatoRepository.findById(contatoRespostaDTO.id()).orElseThrow();
		assertEquals("S", contato.getFavorito());
	}

}
