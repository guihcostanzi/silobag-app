package com.uel.silobag_app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.silobag_app.model.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
	
	public Optional<Operacao> findById(Long id);
	
	public Optional<Operacao> findByNome (String nome);
	
	public Optional<Operacao> findByUid(UUID uid);
}
