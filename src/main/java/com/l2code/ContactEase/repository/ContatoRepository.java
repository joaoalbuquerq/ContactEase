package com.l2code.ContactEase.repository;

import com.l2code.ContactEase.model.Contato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByCelular(String celular);

    List<Contato> findByAtivo(String ativo);

    @Query("SELECT c FROM Contato c WHERE (:ativo IS NULL OR c.ativo = :ativo)")
    List<Contato> findByAtivoOptional(@Param("ativo") String ativo);
}
