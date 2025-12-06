package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.ERole;
import com.app.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	 Optional<Role> findByName(ERole name);
}
