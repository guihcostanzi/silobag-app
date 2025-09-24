package com.uel.silobag_app.model.dto;

import java.util.UUID;

import com.uel.silobag_app.model.enums.TipoOperacao;

public record OperacaoUpdateDTO(
		
		String nome,
		
		TipoOperacao tipoOperacao,
		
		UUID uid
		) {


}
