package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.model.enums.TipoProduto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BagAddDTO(
			
		@NotNull(message = "O volume não pode ser nulo.")
        @Positive(message = "O volume deve ser um número positivo.")
		Double volume,
		
		@NotNull(message = "A capacidade não pode ser negativa.")
        @Positive(message = "A capacidade deve ser um número positivo.")
		Double capacidade,
		
		@NotNull(message = "O código não pode estar nulo.")
		Integer codigo,
		
		@NotNull(message = "O produto não pode estar nulo.")
		TipoProduto produto
		
		) {

}
