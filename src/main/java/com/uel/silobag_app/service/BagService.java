package com.uel.silobag_app.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uel.silobag_app.model.Bag;
import com.uel.silobag_app.model.dto.BagAddDTO;
import com.uel.silobag_app.model.dto.BagRequestDTO;
import com.uel.silobag_app.model.dto.BagUpdateDTO;
import com.uel.silobag_app.repository.BagRepository;

@Service
public class BagService {
	
	@Autowired
	private BagRepository bagRepository;
	
	public List<BagRequestDTO> listar(){
		// Retorna todo o banco de dados de bags
		
		List<Bag> listaBags = bagRepository.findAll();
		
		return listaBags
				.stream()
				.map(bag -> new BagRequestDTO(bag))
				.collect(Collectors.toList());
	}
	
	public BagRequestDTO buscar(Long id){
		return new BagRequestDTO(
				bagRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."))
		);
	}
	
	public Bag adicionar(BagAddDTO dadosCadastro) throws BadRequestException, SQLIntegrityConstraintViolationException {
		try{
			// Verificando se já existe uma bag com esse código cadastrado.
			if (bagRepository.findByCodigo(dadosCadastro.codigo()).isPresent())
				throw new SQLIntegrityConstraintViolationException("O código da bag já está cadastrado.");
			
			return bagRepository.save(new Bag(dadosCadastro));
		} catch(IllegalArgumentException e) {
			throw new BadRequestException("A Bag passada na requisição é inválida.");
		} 
	}
	
	public void remover(Long id) {
		Bag contatoRemocao = bagRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."));;
		bagRepository.delete(contatoRemocao);
	}
	
	public BagRequestDTO atualizar(BagUpdateDTO dadosAtualizados) {
		Bag bagAtualizacao = bagRepository.findById(dadosAtualizados.id())
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."));
		
		bagAtualizacao.setVolume(dadosAtualizados.volume());
		bagAtualizacao.setProduto(dadosAtualizados.produto());
		
		return new BagRequestDTO(bagRepository.save(bagAtualizacao));
	}
	
}
