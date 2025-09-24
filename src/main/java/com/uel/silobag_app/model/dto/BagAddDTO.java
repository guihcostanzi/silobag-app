package com.uel.silobag_app.model.dto;

import java.util.UUID;

import com.uel.silobag_app.model.enums.TipoProduto;

public record BagAddDTO(
			
		Double volume,
		
		Double capacidade,
		
		Integer codigo,
		
		TipoProduto produto,
		
		UUID operacaoUid
		
		) {

}
