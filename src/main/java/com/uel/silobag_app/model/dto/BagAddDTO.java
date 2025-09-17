package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.model.enums.TipoProduto;

public record BagAddDTO(
			
		Double volume,
		
		Double capacidade,
		
		Integer codigo,
		
		TipoProduto produto,
		
		Long operacaoId
		
		) {

}
