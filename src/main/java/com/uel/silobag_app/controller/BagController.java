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

import com.uel.silobag_app.model.Bag;
import com.uel.silobag_app.model.dto.BagAddDTO;
import com.uel.silobag_app.model.dto.BagRequestDTO;
import com.uel.silobag_app.model.dto.BagUpdateDTO;
import com.uel.silobag_app.service.BagService;

@RestController
@RequestMapping("/bag")
@CrossOrigin("*")
public class BagController {
	
	@Autowired
	private BagService bagService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<BagRequestDTO>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(bagService.listar());
	}
	
	@GetMapping("/buscar/{uid}")
	public ResponseEntity<BagRequestDTO> buscar(@PathVariable UUID uid){
		return ResponseEntity.status(HttpStatus.OK).body(bagService.buscar(uid));
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Bag> adicionar(@RequestBody BagAddDTO b) throws BadRequestException, SQLException {
		return ResponseEntity.status(HttpStatus.CREATED).body(bagService.adicionar(b));
	}
	
	@DeleteMapping("/remover/{uid}")
	public ResponseEntity<Void> remover(@PathVariable UUID uid) {
		bagService.remover(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<BagRequestDTO> atualizar(@RequestBody BagUpdateDTO dados) {
		return ResponseEntity.status(HttpStatus.OK).body(bagService.atualizar(dados));
	}

}
