package com.bugt.reva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugt.reva.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	Permission findByRoleName(String roleName);
}
