package org.asalas.services;

import java.util.List;

import org.asalas.domain.Achat;
import org.asalas.domain.Ingredient;
import org.asalas.repo.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchatServiceImpl implements AchatService {
	@Autowired
	private AchatRepository repo;
	
	@Override
	public void save(Achat achat) 
	{
			repo.save(achat);
	}

	@Override
   public List<Achat> findAllByIngredent(Ingredient ing) {
		return repo.findByIngredent(ing);
		//repo.findByIngredentId(ing.getId());
	}
    
	@Override
	public Achat findById(Integer id)
	{
		return repo.findById2(id);
	}
    
	@Override
	public List<Achat> listAll(){
		return repo.findAll();
	}
}
