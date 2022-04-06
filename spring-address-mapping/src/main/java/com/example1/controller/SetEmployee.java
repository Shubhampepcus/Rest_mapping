package com.example1.controller;

import com.example1.entity.Address;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class SetEmployee {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private List <Address> addressList;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public List <Address> getAddressList(){
        return addressList;
    }

    public void setAddressList(List <Address> addressList){
        this.addressList = addressList;
    }
}
