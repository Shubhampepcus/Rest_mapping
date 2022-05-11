package com.example1.repository;

import com.example1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository <Address, Integer> {
}
