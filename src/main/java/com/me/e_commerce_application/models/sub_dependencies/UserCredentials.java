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
@Table(name = "user_credentials")
public class UserCredentials {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private String id;
    //common attributes
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    // relationships

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    @MapsId
    @ToString.Exclude
    private User user;

}
