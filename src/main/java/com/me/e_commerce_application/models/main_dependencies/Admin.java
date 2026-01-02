package com.me.e_commerce_application.models.main_dependencies;

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
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private String id;
    //common attributes
    @Column(name = "admin_pass")
    private String adminPass;
    // relationships
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
