package com.uel.silobag_app.model.dto;

import java.util.UUID;

import com.uel.silobag_app.model.Operacao;
import com.uel.silobag_app.model.enums.TipoOperacao;

public record OperacaoRequestDTO(
		
		String nome,
		
		TipoOperacao tipo,
		
		UUID uid
		
		) {
	
	
	public OperacaoRequestDTO{
		
	}
	
	public OperacaoRequestDTO(Operacao operacao) {
		this(
			operacao.getNome(),
			operacao.getTipo(),
			operacao.getUid()
		);
	}

}
