package org.asalas.services;

import java.util.List;

import org.asalas.domain.Role;
import org.asalas.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public void saveOrUpdate(Role role) {
		roleRepo.save(role);
	}
	
	@Override
	public List<Role> listAll(){
		return roleRepo.findAll();
	}
	
}
