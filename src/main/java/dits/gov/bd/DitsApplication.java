package dits.gov.bd;

import dits.gov.bd.auth.enumeration.ERole;
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
				Role roleAdmin = new Role();
				roleAdmin.setName(ERole.ROLE_ADMIN.name());
				roleRepository.save(roleAdmin);
				Role roleUser = new Role();
				roleUser.setName(ERole.ROLE_USER.name());
				roleRepository.save(roleUser);
				Role roleTaxPayer = new Role();
				roleTaxPayer.setName(ERole.ROLE_TAXPAYER.name());
				roleRepository.save(roleTaxPayer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
