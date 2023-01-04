package dits.gov.bd.auth.controller;


import dits.gov.bd.auth.enumeration.ERole;
import dits.gov.bd.auth.entity.User;
import dits.gov.bd.auth.entity.Role;
import dits.gov.bd.auth.repository.RoleRepository;
import dits.gov.bd.auth.repository.UserRepository;
import dits.gov.bd.auth.request.LoginRequest;
import dits.gov.bd.auth.request.SignupRequest;
import dits.gov.bd.auth.response.JwtResponse;
import dits.gov.bd.auth.response.MessageResponse;
import dits.gov.bd.auth.service.UserDetailsImpl;
import dits.gov.bd.auth.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new taxpayer account
        User user = new User(
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                null,
                signUpRequest.getNid(),
                signUpRequest.getTin(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getZone(),
                signUpRequest.getCircle(),
                signUpRequest.getGender(),
                signUpRequest.getUserStatus(),
                signUpRequest.getCreatedOn(),
                signUpRequest.getUpdatedOn()
        );
        //Set Roles
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role taxPayerRole = roleRepository.findByName(String.valueOf(ERole.ROLE_TAXPAYER))
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(taxPayerRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if (role.equals("user")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_USER.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role defaultRole = roleRepository.findByName(ERole.ROLE_TAXPAYER.name())
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(defaultRole);
                }

            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }

}
