package org.asalas.services;

import java.util.List;

import org.asalas.domain.Ingredient;
import org.asalas.domain.Unity;
import org.asalas.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngServiceImpl implements IngService {
		@Autowired
		private IngredientRepository repo;
		
		@Override
		public void save(Ingredient unit) {
			// TODO Auto-generated method stub
			repo.save(unit);
		}

		@Override
		public Ingredient findByName(String name) {
			// TODO Auto-generated method stub
			return repo.findByName(name);
		}
		
		@Override
		public Ingredient findById(Integer id) {
			return repo.findById2(id);	
		}
		
		@Override
		public List<Ingredient> listAll() {
			// TODO Auto-generated method stub
			return repo.findAll();
		}
		
		@Override
		public void delId(Integer id) {
			repo.deleteById(id);
		}
}
