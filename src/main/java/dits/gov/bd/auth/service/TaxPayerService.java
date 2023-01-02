package dits.gov.bd.auth.service;

import dits.gov.bd.auth.entity.Role;
import dits.gov.bd.auth.entity.TaxPayer;
import dits.gov.bd.auth.entity.User;
import dits.gov.bd.auth.enumeration.Gender;
import dits.gov.bd.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaxPayerService {
    @Autowired
    private UserRepository userRepository;

    public List<TaxPayer> getAllTaxPayers() {
        List<User> users = userRepository.findAll();
        List<TaxPayer> taxPayers = new ArrayList<>();
        for (User user: users){
            if (user!=null) {
                taxPayers.add(setTaxpayer(user));
            }
        }
        return taxPayers;
    }

    public TaxPayer getTaxPayer(int id) {
        return setTaxpayer(userRepository.findById(id));
    }

    private TaxPayer setTaxpayer(User user){
        TaxPayer taxPayer = new TaxPayer(
                user.getId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getCreatedBy(),
                user.getUpdatedBy(),
                user.getFirstName() + " " + user.getLastName(),
                user.getNid(),
                user.getTin(),
                user.getGender(),
                user.getUserStatus(),
                user.getZone(),
                user.getCircle(),
                user.getEmail(),
                user.getRoles()
        );
        return taxPayer;
    }
}
