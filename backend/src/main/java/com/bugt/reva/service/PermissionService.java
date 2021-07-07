package com.bugt.reva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugt.reva.model.Permission;
import com.bugt.reva.repository.PermissionRepository;

@Service
public class PermissionService {
	
	@Autowired
	PermissionRepository permissionRepository;
	
	public Permission getRoleStatus(String roleName){
		return permissionRepository.findByRoleName(roleName);
	}
	
	public Permission save(Permission permission){
		return permissionRepository.save(permission);
	}
}
