package com.ekalavya.employee.address.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekalavya.employee.address.entity.EmployeeAddressEntity;

@Repository
public interface EmployeeAddressRepository extends CrudRepository<EmployeeAddressEntity, Integer> {

	List<EmployeeAddressEntity> findByEmployeeId(Integer employeeId);

}
