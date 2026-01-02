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
@Table(name ="user_phone_number")
public class PhoneNumber {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private String id;
    //common attributes
    @Column(name="phone_number")
    private String number;
    // relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    private User user;
}
