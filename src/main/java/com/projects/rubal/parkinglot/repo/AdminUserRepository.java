package com.projects.rubal.parkinglot.repo;

import com.projects.rubal.parkinglot.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,String> {
}
