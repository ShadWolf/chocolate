package org.asalas.services;

import java.util.List;
import java.util.Optional;
import org.asalas.domain.Unity;
import org.springframework.stereotype.Service;

@Service
public interface UnityService {
    void save(Unity unit);

    Unity findByName(String name);
    Unity findById(Integer id);
    List<Unity> listAll();

	void delId(Integer id);
}
