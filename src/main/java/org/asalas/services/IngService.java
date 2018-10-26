package org.asalas.services;

import java.util.List;

import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.springframework.stereotype.Service;

@Service
public interface IngService {
	void save(Ingredient unit);

	Ingredient findByName(String name);
	Ingredient findById(Integer id);
    List<Ingredient> listAll();

	String delId(Integer id);
}
