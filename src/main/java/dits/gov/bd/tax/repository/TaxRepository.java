package dits.gov.bd.tax.repository;

import dits.gov.bd.tax.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {
    Tax findByTin(String tin);
    @Query("SELECT t FROM Tax t WHERE t.tin=:tin and t.incomeYear=:incomeYear")
    Tax findByTinAndIncomeYear(String tin, String incomeYear);

}
