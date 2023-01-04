package dits.gov.bd.tax.service.impl;

import dits.gov.bd.auth.entity.TaxPayer;
import dits.gov.bd.auth.entity.User;
import dits.gov.bd.auth.enumeration.Gender;
import dits.gov.bd.common.exception.ResourceNotFoundException;
import dits.gov.bd.externalapi.ExternalApiCall;
import dits.gov.bd.tax.entity.Tax;
import dits.gov.bd.tax.repository.TaxRepository;
import dits.gov.bd.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepository;
    @Override
    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

    @Override
    public Tax getByTin(String tin) {
        return taxRepository.findByTin(tin);
    }

    @Override
    public Optional<Tax> getTById(String id) {
        return Optional.empty();
    }

    @Override
    public Tax findByTinAndIncomeYear(String tin, String incomeYear) {
        return taxRepository.findByTinAndIncomeYear(tin, incomeYear);
    }

    @Override
    public Tax save(Tax tax) {
        Tax existingTax = taxRepository.findByTinAndIncomeYear(tax.getTin(), tax.getIncomeYear());
        if(existingTax!=null){
            throw new ResourceNotFoundException("Tax already calculated for TIN-"+tax.getTin()+" of the Income Year-"+tax.getIncomeYear());
        }
        String uri = "http://localhost:8081/api/v1/user-by-tin/"+tax.getTin();
        User taxpayer = (User) ExternalApiCall.getRemoveObject(uri);
        if(taxpayer==null){
            throw new ResourceNotFoundException("Tax Payer not found for TIN-"+tax.getTin());
        }
        tax.setTotalTax(calculateTax(taxpayer.getGender(),tax.getSalary()));
        return taxRepository.save(tax);
    }
    public double calculateTax(Gender gender, double salary) {
        double tax = 0;
        System.out.println(gender);
        double initialSlab = (gender.equals(Gender.M))?300000:350000;
        if(salary>initialSlab+1300000) {
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += 400000*0.15;
            tax += 500000*0.2;
            tax += (salary - (initialSlab+1300000))*0.25;
        }else if(salary > (initialSlab+800000) && salary <= (initialSlab+1300000)){
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += 400000*0.15;
            tax += (salary - (initialSlab+800000))*0.2;
        }else if(salary > (initialSlab+400000) && salary <= (initialSlab+800000)){
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += (salary - (initialSlab+400000))*0.15;
        }else if(salary > (initialSlab+100000) && salary <= (initialSlab+400000)){
            tax += 100000*0.05;
            tax += (salary - (initialSlab+100000))*0.1;
        }else if(salary > initialSlab && salary <= (initialSlab+100000)){
            tax += (salary - initialSlab)*0.05;
        }
        return tax;
    }
}
