package com.me.e_commerce_application.models.other_dependencies;

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
@Table(name = "user_favourite")
public class UserFavourite {
    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private String userId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
