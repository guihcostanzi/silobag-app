package com.uel.silobag_app.model.dto;

import java.util.UUID;

import com.uel.silobag_app.model.enums.TipoProduto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BagUpdateDTO(
		
		@Positive(message = "Não pode haver um volume negativo.")
		Double volume,
		
		@NotNull(message = "O tipo de produto não pode estar vazio.")
		TipoProduto produto,
		
		@NotNull(message = "O uid precisa ser informado para editar a bag.")
		UUID uid
		
		) {
	
}
