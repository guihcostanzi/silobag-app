package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.enums.TipoOperacao;
import com.uel.silobag_app.model.Operacao;

public record OperacaoRequestDTO(
		
		String nome,
		
		TipoOperacao tipo
		
		) {
	
	
	public OperacaoRequestDTO{
		
	}
	
	public OperacaoRequestDTO(Operacao operacao) {
		this(
			operacao.getNome(),
			operacao.getTipo()
		);
	}

}
