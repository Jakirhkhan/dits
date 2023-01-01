package dits.gov.bd.auth.entity;

import dits.gov.bd.auth.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String nid;
    private String tin;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String userStatus;
    private String zone;
    private String circle;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username,
                String password,
                String email,
                Set<Role> roles,
                String nid,
                String tin,
                String firstName,
                String lastName,
                String zone,
                String circle,
                Gender gender,
                String userStatus,
                Date createdOn,
                Date updatedOn
    ) {
        super(createdOn,updatedOn);
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.tin=tin;
        this.nid=nid;
        this.userStatus=userStatus;
        this.zone=zone;
        this.circle=circle;
    }
}