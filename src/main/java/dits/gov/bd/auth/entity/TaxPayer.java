package dits.gov.bd.auth.entity;

import dits.gov.bd.auth.enumeration.Gender;
import dits.gov.bd.auth.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaxPayer extends BaseEntity{
    private String name;
    private String nid;
    private String tin;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private UserStatus userStatus;
    private String zone;
    private String circle;
    private String email;
    private Set<Role> roles = new HashSet<>();

    public TaxPayer(int id, Date createdOn, Date updatedOn, String createdBy, String updatedBy, String name, String nid, String tin, Gender gender, UserStatus userStatus, String zone, String circle, String email, Set<Role> roles) {
        super(id, createdOn, updatedOn, createdBy, updatedBy);
        this.name = name;
        this.nid = nid;
        this.tin = tin;
        this.gender = gender;
        this.userStatus = userStatus;
        this.zone = zone;
        this.circle = circle;
        this.email = email;
        this.roles = roles;
    }
}
