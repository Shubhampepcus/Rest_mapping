package com.example1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Employee_Table")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotEmpty(message = "name can not be blank")
  @Size(max = 10, min = 3, message = "Please Enter the name with proper lenghth")
  private String name;

  @NotEmpty(message = "email can not be blank") @Email
  private String email;
  @NotEmpty(message = "Phone can not be blank")
  @Size(max = 10, message = "Please Enter Valid length phone No")
  private String phone;


  @JoinColumn
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
  private Department department;


  @NotEmpty
  @JoinColumn(name = "employee_id")
  @OneToMany(cascade = CascadeType.ALL)
  private List<Address> addressList;

}
