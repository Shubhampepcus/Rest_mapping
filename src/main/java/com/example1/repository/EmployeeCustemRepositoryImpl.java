package com.example1.repository;

import com.example1.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeCustemRepositoryImpl implements EmployeeCustemRepository {
  @Autowired
  EntityManager entityManager;

  @Override
  public List<Employee> findByDepartment(String name, String department) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery cq = cb.createQuery(Employee.class);

    Root<Employee> employee = cq.from(Employee.class);

    Predicate firstNamePredicate = cb.equal(employee.get("name"), name);
    Predicate departmentPredicate = cb.equal(employee.get("department"), department);

    cq.where(firstNamePredicate, departmentPredicate);
    TypedQuery<Employee> query = entityManager.createQuery(cq);

    return query.getResultList();
  }
}
