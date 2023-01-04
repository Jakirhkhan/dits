package dits.gov.bd.auth.repository;


import dits.gov.bd.auth.entity.TaxPayer;
import dits.gov.bd.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.roles = :roles")
    List<TaxPayer> getAllTaxPayers(String roles);
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findById(int id);
    @Query("SELECT u FROM User u WHERE u.tin = :tin")
    User getTaxPayerByTin(String tin);
}