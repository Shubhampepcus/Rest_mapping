package com.example1.service;

import com.example1.entity.Address;
import com.example1.entity.Employee;
import com.example1.repository.AddressRepository;
import com.example1.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    public Employee saveEmployee(Employee employee){
        List <Address> addressList = new ArrayList <>();
        employee.getAddressList().stream().map(a -> addressList.add(a)).collect(Collectors.toList());


         //employee=request.getEmployee();
        employee.setAddressList(addressList);

        //return employeeRepository.save(employee);
        return employeeRepository.save(employee);
    }

    public Employee findById(Integer id){
        List<Address> addressList = new ArrayList <>();
        Employee employee = new Employee();
        Employee employeeRetunred=employeeRepository.findById(id).orElse(null);
        employee.setName(employeeRetunred.getName());
        employee.setPhone(employeeRetunred.getPhone());
        employee.setEmail(employeeRetunred.getEmail());
        employeeRetunred.getAddressList().stream().map(address -> addressList.add(address)).collect(Collectors.toList());
        employee.setAddressList(addressList);
        return employee;


    }

    public Employee updateEmployee(Employee employee){
        Employee existigEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        existigEmployee.setName(employee.getName());
        existigEmployee.setEmail(employee.getEmail());
        existigEmployee.setPhone(employee.getPhone());
        existigEmployee.setAddressList(employee.getAddressList());
        return existigEmployee;

    }

    public Employee deleteEmployee(Integer id){
       Employee employee = findById(id);
       employeeRepository.deleteById(employee.getId());
       return employee;

    }

    public List<Employee> sortEmployee(String field){
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

}
