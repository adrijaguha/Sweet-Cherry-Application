package com.capgemini.sweetcherry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.sweetcherry.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}