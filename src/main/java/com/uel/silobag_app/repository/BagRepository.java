package com.uel.silobag_app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uel.silobag_app.model.Bag;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {

	public Optional<Bag> findById(Long id);
	
	public Optional<Bag> findByUid(UUID uid);
	
	public Optional<Bag> findByCodigo(Integer codigo);
	
	@Query("SELECT b FROM Bag b WHERE " 
	        + "(LOWER(CAST(b.codigo AS string)) LIKE LOWER(CONCAT('%', :busca, '%'))) OR"
	        + "(LOWER(b.produto) LIKE LOWER(CONCAT('%', :busca, '%')))"
	)
	public  Optional<List<Bag>> findByFiltros(@Param("busca") String busca);
	
}
