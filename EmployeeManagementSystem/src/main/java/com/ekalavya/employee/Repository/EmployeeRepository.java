package com.ekalavya.employee.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ekalavya.employee.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
	
	List<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<EmployeeEntity> findByFirstNameOrLastNameOrderByFirstName(String firstName, String lastName); 
	
	@Transactional
	@Modifying
	@Query(value="update EMPLOYEE set STATE = :state where EMPLOYEE_ID = :empId ",nativeQuery = true)
	void updateStateForEmpId(@Param("empId") int empId, @Param("state") String state);
	
}
