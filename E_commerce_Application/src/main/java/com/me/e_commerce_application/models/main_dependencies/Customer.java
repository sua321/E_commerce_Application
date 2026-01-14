package com.me.e_commerce_application.models.main_dependencies;

import com.me.e_commerce_application.models.Users;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private String id;
    //common attributes
    // relationships
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Users users;
}
