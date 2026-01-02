package com.me.e_commerce_application.models.sub_dependencies;

import com.me.e_commerce_application.models.User;
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
@Table(name = "user_address")
public class Address {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private String id;
    //common attributes
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    // relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    @ToString.Exclude
    private User user;
}
