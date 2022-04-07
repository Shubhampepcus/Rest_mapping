package com.example1.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "Address_Table")
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;

//    @ManyToOne
//    private Employee employee;

}
