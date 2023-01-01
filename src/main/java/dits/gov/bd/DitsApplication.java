package dits.gov.bd;

import dits.gov.bd.auth.entity.ERole;
import dits.gov.bd.auth.entity.Role;
import dits.gov.bd.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DitsApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(DitsApplication.class, args);
		System.out.println("Hello Auth!");
	}

	/* Add some rows into roles collection
	 * before assigning any role to user. */
	@Override
	public void run(String... args) throws Exception {
		try {
			if (roleRepository.findAll().isEmpty()) {
				Role role = new Role();
				role.setName(ERole.ROLE_EMPLOYEE.name());
				roleRepository.save(role);
				Role role2 = new Role();
				role2.setName(ERole.ROLE_ADMIN.name());
				roleRepository.save(role2);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
