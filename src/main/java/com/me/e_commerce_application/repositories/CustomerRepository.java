package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.main_dependencies.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}