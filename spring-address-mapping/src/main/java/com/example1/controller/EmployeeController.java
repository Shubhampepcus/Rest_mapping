package com.example1.controller;

import com.example1.Dto.Request;
import com.example1.entity.Address;
import com.example1.entity.Employee;
import com.example1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example1.repository.AddressRepository;
import com.example1.repository.EmployeeRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/employees/")
    public Employee saveEmployee(@RequestBody Request request){

       // System.out.println(request.getEmployee().getAddressList());

        List<Address> addressList = new ArrayList <>();
        request.getEmployee().getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());

       // addressRepository.saveAll(addressList);

        Employee employee = request.getEmployee();
        //Employee employee = new Employee();
        employee.setAddressList(addressList);

        return employeeRepository.save(employee);


    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployee(){
        SetEmployee employDetails = new SetEmployee();
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee: employees
             ) {
            employee.setId(employee.getId());
            employDetails.setEmail(employee.getEmail());
            employDetails.setPhone(employee.getPhone());
            employDetails.setName(employee.getName());
            employDetails.setAddressList(employee.getAddressList());
        }

        return employees;

    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id){
        return employeeService.findById(id);
    }


    @PatchMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Request request,@PathVariable Integer id ){
        Employee employee = request.getEmployee();
        return employeeService.updateEmployee(id,request.getEmployee());

    }

    @DeleteMapping("/employees")
    public Employee deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/sorts/{field}")
    public List<Employee> sortEmployee(@PathVariable String field){
        List<Employee> sortedEmployee = employeeService.sortEmployee(field);
        return sortedEmployee;
    }

    @GetMapping("/employees/filters/{name}")
    public List<Employee> filetrEmployee(@PathVariable String name){
        List<Employee> filetredEmployee = employeeService.filterEmployee(name);
        return filetredEmployee;
    }

    @GetMapping("/employees/filterByName/{name}")
    public List<Employee> filterByName(@PathVariable String name){
        return employeeRepository.findByname(name);
    }

    @GetMapping("/employees/filterByEmail/{email}")
    public List<Employee> filterByEmail(@PathVariable String name){
        return employeeRepository.findByemail(name);
    }




}
