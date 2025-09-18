package com.uel.silobag_app.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uel.silobag_app.model.Operacao;
import com.uel.silobag_app.model.dto.OperacaoAddDTO;
import com.uel.silobag_app.model.dto.OperacaoRequestDTO;
import com.uel.silobag_app.model.dto.OperacaoUpdateDTO;
import com.uel.silobag_app.service.OperacaoService;

@RestController
@RequestMapping("/operacao")
@CrossOrigin("*")
public class OperacaoController {
	
	@Autowired
	private OperacaoService operacaoService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<OperacaoRequestDTO>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(operacaoService.listar());
	}
	
	@GetMapping("/buscar/{uid}")
	public ResponseEntity<OperacaoRequestDTO> buscar(@PathVariable UUID uid){
		return ResponseEntity.status(HttpStatus.OK).body(operacaoService.buscar(uid));
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Operacao> adicionar(@RequestBody OperacaoAddDTO o) throws BadRequestException, SQLException {
		return ResponseEntity.status(HttpStatus.CREATED).body(operacaoService.adicionar(o));
	}
	
	@DeleteMapping("/remover/{uid}")
	public ResponseEntity<Void> remover(@PathVariable UUID uid) {
		operacaoService.remover(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<OperacaoRequestDTO> atualizar(@RequestBody OperacaoUpdateDTO dados) {
		return ResponseEntity.status(HttpStatus.OK).body(operacaoService.atualizar(dados));
	}

}
