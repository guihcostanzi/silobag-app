package com.uel.silobag_app.model.dto;

import java.util.UUID;

import com.uel.silobag_app.model.enums.TipoProduto;

public record BagUpdateDTO(
		
		Double volume,
		
		TipoProduto produto,
		
		UUID uid
		
		) {
	
}
