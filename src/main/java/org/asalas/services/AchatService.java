package org.asalas.services;

import java.util.List;

import org.asalas.domain.Achat;
import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.springframework.stereotype.Service;

@Service
public interface AchatService {
	void save(Achat achat);

    List<Achat> findAllByIngredent(Ingredient ing);
    Achat findById(Integer id);
    List<Achat> listAll();

	void delId(Integer id);
}

