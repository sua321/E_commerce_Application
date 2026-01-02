package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.sub_dependencies.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {
}