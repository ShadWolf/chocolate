package org.asalas.repo;

import org.asalas.domain.Unity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends  JpaRepository<Unity, Integer> {
	
	Unity findByName( String name);
	
	@Query(value = "SELECT u FROM Unity u WHERE u.id =(:id)")
	Unity findById2(@Param("id") Integer id);
	 
	 /* example 
	    @Query("SELECT con FROM Contact con  WHERE con.phoneType=(:pType) AND con.lastName= (:lName)")
19
    List<Contact> findByLastNameAndPhoneType(@Param("pType") PhoneType pType, @Param("lName") String lName);
	  * */
}
