package com.joalbano.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joalbano.auth.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	@Query("select p from Permission p where p.description = :description")
	Permission findByDescription(@Param("description") String description);
}
