package dits.gov.bd.tax.service;

import dits.gov.bd.tax.entity.Tax;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaxService {
    List<Tax> findAll();
    Tax getByTin(String tin);
    Optional<Tax> getTById(String id);
    Tax findByTinAndIncomeYear(String tin, String incomeYear);
    Tax save(Tax tax);
}
