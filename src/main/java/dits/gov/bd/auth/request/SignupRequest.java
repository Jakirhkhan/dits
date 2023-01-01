package dits.gov.bd.auth.request;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {
    private String username;

    private String email;

    private Set<String> roles;

    private String password;
}
