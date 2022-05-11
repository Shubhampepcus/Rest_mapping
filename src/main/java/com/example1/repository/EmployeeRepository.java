package com.example1.repository;

import com.example1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository
    extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>, EmployeeCustemRepository {

  public List<Employee> findByname(String name);

  public List<Employee> findByemail(String email);

  public List<Employee> findBydepartment(String department);

  public Employee findByid(Integer id);

  // @Query(value = "SELECT e.name, a.city FROM Employee AS e JOIN Address AS a
  // ON WHERE a.city = ?1",nativeQuery =true )
  // public List<Employee> findEMPByCity(String city);

}
