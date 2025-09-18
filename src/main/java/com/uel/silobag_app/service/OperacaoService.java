package com.uel.silobag_app.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uel.silobag_app.model.Operacao;
import com.uel.silobag_app.model.dto.OperacaoAddDTO;
import com.uel.silobag_app.model.dto.OperacaoRequestDTO;
import com.uel.silobag_app.model.dto.OperacaoUpdateDTO;
import com.uel.silobag_app.repository.OperacaoRepository;

@Service
public class OperacaoService {
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	public List<OperacaoRequestDTO> listar(){
		// Retorna todo o banco de dados de operacaos
		
		List<Operacao> listaOperacoes = operacaoRepository.findAll();
		
		return listaOperacoes
				.stream()
				.map(operacao -> new OperacaoRequestDTO(operacao))
				.collect(Collectors.toList());
	}
	
	public OperacaoRequestDTO buscar(UUID uid){
		return new OperacaoRequestDTO(
				operacaoRepository.findByUid(uid)
				.orElseThrow(() -> new NoSuchElementException("Operação não encontrada."))
		);
	}
	
	public Operacao adicionar(OperacaoAddDTO dadosCadastro) throws BadRequestException, SQLIntegrityConstraintViolationException {		
		
		// Verificando se o nome da operação já esta cadastrado
		if (operacaoRepository.findByNome(dadosCadastro.nome()).isPresent())
			throw new SQLIntegrityConstraintViolationException("O nome da operação já está cadastrado.");
		
		return operacaoRepository.save(new Operacao(dadosCadastro));
		
	}
	
	public void remover(UUID uid) {
		Operacao operacaoRemocao = operacaoRepository.findByUid(uid)
				.orElseThrow(() -> new NoSuchElementException("Operação não encontrada."));;
		operacaoRepository.delete(operacaoRemocao);
	}
	
	public OperacaoRequestDTO atualizar(OperacaoUpdateDTO dadosAtualizados) {
		Operacao operacaoAtualizacao = operacaoRepository.findByUid(dadosAtualizados.uid())
				.orElseThrow(() -> new NoSuchElementException("Operação não encontrada."));
		
		operacaoAtualizacao.setNome(dadosAtualizados.nome());
		operacaoAtualizacao.setTipo(dadosAtualizados.tipoOperacao());
		
		return new OperacaoRequestDTO(operacaoRepository.save(operacaoAtualizacao));
	}

}
