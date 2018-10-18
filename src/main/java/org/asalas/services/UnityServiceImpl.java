package org.asalas.services;

import java.util.List;
import java.util.Optional;

import org.asalas.domain.Unity;
import org.asalas.repo.UnityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnityServiceImpl implements UnityService {
	@Autowired 
	private UnityRepository repo;
	
	@Override
	public void save(Unity unit) {
		// TODO Auto-generated method stub
		repo.save(unit);
	}

	@Override
	public Unity findByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}
	
	@Override
	public Unity findById(Integer id) {
		return repo.findById2(id);	
	}
	
	@Override
	public List<Unity> listAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
