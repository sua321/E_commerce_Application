package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.main_dependencies.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, String> {
}