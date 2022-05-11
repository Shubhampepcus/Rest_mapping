package com.example1.specification;

import com.example1.entity.Address;
import com.example1.entity.Department;
import com.example1.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Component
public class EmployeeSpecification {

  public static Specification<Employee> hasFirstName(String firstname) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("name"), firstname);
    });
  }

  public static Specification<Employee> findByphone(String phone) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.like(root.get("email"), "%" + phone + "%");
    });
  }

  public static Specification<Employee> hasEmail(String email) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("email"), email);
    });
  }

  public static Specification<Employee> getEmployeeByCity(String city){
    return ((root, query, criteriaBuilder) -> {
      Join <Employee, Address> join = root.join("addressList", JoinType.INNER);
      return criteriaBuilder.equal(join.get("city"),city);
    });


  }

  public static Specification<Employee> getEmployeeDepartment(String department){
    return ((root, query, criteriaBuilder) -> {
      Join <Employee, Department> join = root.join("department", JoinType.INNER);
      return criteriaBuilder.equal(join.get("department"),department);
    });


  }
}