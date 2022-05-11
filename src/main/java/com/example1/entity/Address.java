package com.example1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "Address_Table")
public class Address {
  @Id
  private Integer id;
  @NotEmpty(message = "Street1 can not be blank")
  private String street1;

  private String street2;

  @NotEmpty(message = "City can not be blank")
  private String city;

  @NotEmpty(message = "State can not be blank")
  private String state;

  @NotEmpty(message = "Country can not be blank")
  private String country;

//  @ManyToOne
//  @JsonBackReference
//  private Employee employee;

}
