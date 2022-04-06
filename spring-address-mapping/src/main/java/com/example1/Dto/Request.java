package com.example1.Dto;

import com.example1.entity.Address;
import com.example1.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Request {
    private Employee employee;
    private Address address;
}
