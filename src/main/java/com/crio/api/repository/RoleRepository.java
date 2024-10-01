package com.crio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.api.domain.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
