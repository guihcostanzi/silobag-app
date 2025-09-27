package com.uel.silobag_app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.silobag_app.model.Bag;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {

	public Optional<Bag> findById(Long id);
	
	public Optional<Bag> findByUid(UUID uid);
	
	public Optional<Bag> findByCodigo(Integer codigo);
	
}
