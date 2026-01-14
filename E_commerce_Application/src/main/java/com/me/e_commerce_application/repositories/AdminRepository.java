package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.main_dependencies.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}