package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.enums.TipoOperacao;

public record OperacaoUpdateDTO(
		
		String nome,
		
		TipoOperacao tipoOperacao,
		
		Long id
		) {


}
