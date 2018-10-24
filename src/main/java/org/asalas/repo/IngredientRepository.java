package org.asalas.repo;

import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	Ingredient findByName( String name);
	
	@Query(value = "SELECT i FROM Ingredient i WHERE i.id =(:id)")
	Ingredient findById2(@Param("id") Integer id);
}
