package com.example1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "Department_Table")
public class Department {
  @Id
  @GeneratedValue
  private int id;

 // @NotEmpty(message = "department is must")
  private String department;


  @JoinColumn(name = "Employee_id")
  @OneToOne(cascade = CascadeType.ALL)
  @JsonBackReference
  private Employee employee;

}
