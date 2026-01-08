package com.me.e_commerce_application.models;

import com.me.e_commerce_application.models.sub_dependencies.Address;
import com.me.e_commerce_application.models.sub_dependencies.PhoneNumber;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class Users {
            @Id
            @GeneratedValue(strategy = GenerationType.UUID )
            @Column(name = "id")
            @EqualsAndHashCode.Include
            private String id;
            //common attributes
            @Column(name = "user_name")
            private String userName;
            @Column(name = "full_name")
            private String fullName;
            @Column(name = "dob")
            private String dob;
            @Column(name = "user_type")
            private String userType;
            // relationships
            @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE} , orphanRemoval = true)
            @ToString.Exclude
            private List<Address> addresses = new ArrayList<>();
            @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
            @ToString.Exclude
            private List<PhoneNumber> phoneNumbers = new ArrayList<>();

}
