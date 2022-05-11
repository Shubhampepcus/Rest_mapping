package com.example1.service;

import com.example1.Dto.Request;
import com.example1.entity.Address;
import com.example1.entity.Department;
import com.example1.entity.Employee;
import com.example1.repository.AddressRepository;
import com.example1.repository.DepartmentRepository;
import com.example1.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.Valid;

import static com.example1.specification.EmployeeSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  EntityManager entityManager;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private DepartmentRepository departmentRepository;

  public List<Employee> findAllEmployee() {
    return employeeRepository.findAll();
  }


  public Employee saveEmployee(Request request) {
//    List<Address> addressList = new ArrayList<>();
//    request.getEmployee().getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
//    Employee employee = request.getEmployee();
//    employee = employeeRepository.save(employee);
//    for (Address address : addressList) {
//      addressRepository.save(address);
//    }
//    return employee;

    Department department = request.getEmployee().getDepartment();
     List<Address> addressList = new ArrayList<>();
     request.getEmployee().getAddressList().stream().map(a ->
     addressList.add(a)).collect(Collectors.toList());
     Employee employee = request.getEmployee();
     department.setEmployee(employee);
     employee.setDepartment(department);

     employee.setAddressList(addressList);
     return employeeRepository.save(employee);
  }

  public Employee findById(Integer id) {
    return employeeRepository.findByid(id);

  }

  public Employee updateEmployee(Integer id, Employee employee) {
    Employee existingEmployee = employeeRepository.findById(id).orElse(null);
    List<Address> addressList = new ArrayList<>();
    employee.getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());
    existingEmployee.setAddressList(addressList);
    existingEmployee.setName(employee.getName());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setPhone(employee.getPhone());

    return employeeRepository.save(existingEmployee);

  }

  public Employee deleteEmployee(Integer id) {
    Employee employee = findById(id);
    employeeRepository.deleteById(employee.getId());
    return employee;

  }

  public List<Employee> sortEmployee(String field) {
    return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
  }

  public List<Employee> filterEmployee(String name) {
    List<Employee> filteredEmployee = new ArrayList<>();
    List<Employee> employees = employeeRepository.findAll();
    for (Employee employee : employees) {

      if (employee.getName().equalsIgnoreCase(name) || employee.getEmail().equalsIgnoreCase(name)) {
        filteredEmployee.add(employee);

      }
      for (Address address : employee.getAddressList()) {
        if (address.getCity().equalsIgnoreCase(name) || address.getCountry().equalsIgnoreCase(name)
            || address.getState().equalsIgnoreCase(name)) {
          filteredEmployee.add(employee);
        }
      }

    }

    return filteredEmployee;
  }

  public List<Employee> findByDepartment(String department) {
    return employeeRepository.findBydepartment(department);
  }

  public List<Employee> getEmployeeByDepartment(String name, String department) {
    return employeeRepository.findByDepartment(name, department);
  }

  public List<Employee> findByFirstnameAndEmail(String name, String email) {

    return employeeRepository.findAll(where(hasFirstName(name).and(hasEmail(email))));
  }

  public List<Employee> findByPhone(String phone) {
    return employeeRepository.findAll(findByphone(phone));

  }

  public Page<Employee> findEmployeeWithPagination(int offset, int pageSize){
    return employeeRepository.findAll(PageRequest.of(offset,pageSize));
  }

  public Employee saveEmployeFromCsv(){
    String line = "";
    Employee employee = new Employee();
    BufferedReader fileReader = null;
    try {
      fileReader = new BufferedReader(new FileReader("C:\\Users\\Pepcus.User\\IdeaProjects\\spring-address-mapping\\Employee.csv"));

    while ((line = fileReader.readLine())!=null){
      String[] data = line.split(",");

      Department department = new Department();
      Address address = new Address();
      List<Address> addressList = new ArrayList <>();


      employee.setEmail(data[0]);
      employee.setName(data[1]);
      employee.setPhone(data[2]);
      department.setDepartment(data[3]);
      address.setId(Integer.parseInt(data[4]));
      address.setCity(data[5]);
      address.setCountry(data[6]);
      address.setState(data[7]);
      address.setStreet1(data[8]);
      address.setStreet2(data[9]);
      addressList.add(address);

      employee.setAddressList(addressList);
      department.setEmployee(employee);
      employee.setDepartment(department);




    } } catch (Exception e) {
      e.printStackTrace();
    }
    return employeeRepository.save(employee);
  }
}

