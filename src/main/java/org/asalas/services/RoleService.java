package org.asalas.services;

import java.util.List;

import org.asalas.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
	void saveOrUpdate(Role role);
	
	List<Role> listAll();
	
	
}
