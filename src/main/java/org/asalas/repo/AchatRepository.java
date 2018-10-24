package org.asalas.repo;

import java.util.List;

import org.asalas.domain.Achat;
import org.asalas.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatRepository extends JpaRepository<Achat, Integer>{
	
	@Query(value = "SELECT a from Achat a where a.ingredient = (:ing) ")
	public List<Achat> findByIngredent(@Param("ing") Ingredient ing);
	
	@Query(value = "SELECT a FROM Achat a WHERE a.id =(:id)")
	public Achat findById2(@Param("id") Integer id);

}
