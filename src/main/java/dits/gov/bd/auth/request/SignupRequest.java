package dits.gov.bd.auth.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dits.gov.bd.auth.enumeration.Gender;
import dits.gov.bd.auth.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String nid;
    private String tin;
    private Gender gender;
    private UserStatus userStatus;
    private String zone;
    private String circle;
    private String email;
    private Set<String> roles;
    private Date createdOn;
    private Date updatedOn;
    private String createdBy;
    private String updatedBy;
}
