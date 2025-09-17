package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.enums.TipoOperacao;

public record OperacaoAddDTO(
		
		String nome,
		
		TipoOperacao tipo
		
		) {

}
