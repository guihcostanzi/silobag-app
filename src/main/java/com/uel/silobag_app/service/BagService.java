package com.uel.silobag_app.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
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
	
	public BagRequestDTO buscar(UUID uid){
		return new BagRequestDTO(
				bagRepository.findByUid(uid)
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."))
		);
	}
	
	public List<BagRequestDTO> buscarPorFiltros(String busca) {
				
		List<Bag> l = bagRepository.findByFiltros(busca)
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."));
		
		return l.stream()
		.map(bag -> new BagRequestDTO(bag))
		.collect(Collectors.toList());
	}
	
	public Bag adicionar(BagAddDTO dadosCadastro) throws BadRequestException, SQLIntegrityConstraintViolationException {
		try{
			
			if(dadosCadastro.volume() > dadosCadastro.capacidade()) 
				throw new BadRequestException("Capacidade da bag excedida.");
	
			
			// Verificando se já existe uma bag com esse código cadastrado.
			if (bagRepository.findByCodigo(dadosCadastro.codigo()).isPresent())
				throw new SQLIntegrityConstraintViolationException("O código da bag já está cadastrado.");
			
			// Criando a bag
			Bag bag = new Bag(dadosCadastro);
			
			return bagRepository.save(bag);
		} catch(IllegalArgumentException e) {
			throw new BadRequestException("A Bag passada na requisição é inválida.");
		} 
	}
	
	public void remover(UUID uid) {
		Bag contatoRemocao = bagRepository.findByUid(uid)
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."));;
		bagRepository.delete(contatoRemocao);
	}
	
	public BagRequestDTO atualizar(BagUpdateDTO dadosAtualizados) throws BadRequestException {
		Bag bagAtualizacao = bagRepository.findByUid(dadosAtualizados.uid())
				.orElseThrow(() -> new NoSuchElementException("Bag não encontrada."));
		
		// Verificando se o volume está dentro do limite
		if(dadosAtualizados.volume() > bagAtualizacao.getCapacidade()) 
			throw new BadRequestException("Capacidade da bag excedida.");
		
		bagAtualizacao.setVolume(dadosAtualizados.volume());
		bagAtualizacao.setProduto(dadosAtualizados.produto());
		
		return new BagRequestDTO(bagRepository.save(bagAtualizacao));
	}
	
}
