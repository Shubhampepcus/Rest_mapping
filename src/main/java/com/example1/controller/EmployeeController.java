package com.example1.controller;

import com.example1.Dto.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example1.entity.Address;
import com.example1.entity.Employee;
import com.example1.service.EmployeeService;
import com.example1.repository.AddressRepository;
import com.example1.repository.EmployeeRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static com.example1.specification.EmployeeSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private ExceptionController exceptionController;

  //Add new Employee to record
  @PostMapping("/employees")
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Request request) {
    System.out.println("Controller");
    return new ResponseEntity<Employee>(employeeService.saveEmployee(request), HttpStatus.CREATED);
  }

  //Get All employee list
  @GetMapping("/employees")
  public List<Employee> findAllEmployee() {
    return employeeService.findAllEmployee();
  }

  //Search employee by Id
  @GetMapping("/employees/search/{id}")
  public Employee findById(@PathVariable int id) {
    return employeeService.findById(id);
  }

  //Update Employee data
  @PutMapping("/employees/{id}")
  public Employee updateEmployee(@RequestBody Request request, @PathVariable Integer id) {
    Employee employee = request.getEmployee();
    return employeeService.updateEmployee(id, request.getEmployee());

  }

  //delete Employee By id
  @DeleteMapping("/employees")
  public Employee deleteEmployee(@PathVariable int id) {
    return employeeService.deleteEmployee(id);
  }

  //Sort Employee by providing Field name
  @GetMapping("/employees/sorts")
  public List<Employee> sortEmployee(@RequestParam String field) {
    return employeeService.sortEmployee(field);
  }

  //Filetr Employee By a Field Name
  @GetMapping("/employees/filters")
  public List<Employee> filetrEmployee(@RequestParam String name) {
    return employeeService.filterEmployee(name);
  }

  //Search Employee by name
  @GetMapping("/employees/findByName")
  public List<Employee> filterByName(@RequestParam String name) {
    return employeeRepository.findByname(name);
  }

  //Filtering EMployee list by Email
  @GetMapping("/employees/filterByEmail")
  public List<Employee> findByemail(@RequestParam String email) {
    return employeeRepository.findByemail(email);
  }

//  //Finding Employee By department Using custom Query in Employee Repository
//  @GetMapping("/employees/findByDepartment")
//  public List<Employee> findByDepartmet(@RequestParam String department) {
//    return employeeService.findByDepartment(department);
//  }

//  //Finding Employee by Name and Department Using Criteria
//  @GetMapping("/employees/{name}/{department}")
//  public List<Employee> getEmployeeByDepartment(@PathVariable String name, @PathVariable String department) {
//    return employeeService.getEmployeeByDepartment(name, department);
//  }

  //Find Employee by Name and Email using Specification And query
  @GetMapping("/employees/specification/{name}/{email}")
  public List<Employee> findByFirstnameAndEmail(@PathVariable("name") String name,
      @PathVariable("email") String email) {

    return employeeService.findByFirstnameAndEmail(name, email);
  }

  //finds Employee by phone using Specification
  @GetMapping("/employees/{phone}")
  public List<Employee> findByPhone(@PathVariable String phone) {
    return employeeService.findByPhone(phone);

  }

  //Getting employee list using pagonation by providing page size and offset size
  @GetMapping("/employees/pagination/{offset}/{pageSize}")
  public Page<Employee> findEployeeByPagination(@PathVariable int offset, @PathVariable int pageSize){
    return employeeService.findEmployeeWithPagination(offset, pageSize);
  }

  //find employees by city
  @GetMapping("/employees/getEmployyebByCity/{city}")
  public List<Employee> findEployeeByPagination(@PathVariable String city){
    return employeeRepository.findAll(where(getEmployeeByCity(city)));
  }

  //get employees by Depertment using specification
  @GetMapping("/employees/getByDepartment/{department}")
  public List<Employee> getEmployeeByDepartment(@PathVariable String department){
    return employeeRepository.findAll(where(getEmployeeDepartment(department)));
    }

  //Save Employee from csv file input
  @PostMapping("employees/saveByCsv")
  public Employee saveEmployeeByCsv(){
    return employeeService.saveEmployeFromCsv();
  }
}

