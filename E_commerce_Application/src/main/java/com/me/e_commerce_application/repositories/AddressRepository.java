package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.sub_dependencies.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}