package com.uel.silobag_app.model.dto;

import com.uel.silobag_app.model.Bag;
import com.uel.silobag_app.model.enums.TipoProduto;

public record BagRequestDTO(
		
		Double volume,
		
		Double capacidade,
		
		Integer codigo,
		
		TipoProduto produto
		
		) {
	
	
	// Construtor "Compacto" -- sem necessidade de escrever os .this para os atributos.
	public BagRequestDTO{
	}
	
	// Construtor usando Bag
    public BagRequestDTO(Bag bag) {
        this(
            bag.getVolume(), 
            bag.getCapacidade(), 
            bag.getCodigo(), 
            bag.getProduto()
        );
    }
	
}
