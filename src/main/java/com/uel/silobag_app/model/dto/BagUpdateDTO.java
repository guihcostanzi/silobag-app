package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.model.enums.TipoProduto;

public record BagUpdateDTO(
		
		Double volume,
		
		TipoProduto produto,
		
		Long id
		
		) {
	
}
