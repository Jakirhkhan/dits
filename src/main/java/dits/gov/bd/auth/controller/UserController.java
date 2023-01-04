package dits.gov.bd.auth.controller;


import dits.gov.bd.auth.entity.TaxPayer;
import dits.gov.bd.auth.entity.User;
import dits.gov.bd.auth.response.MessageResponse;
import dits.gov.bd.auth.service.TaxPayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private TaxPayerService taxPayerService;
    @GetMapping("/all")
    public MessageResponse allAccess() {
        return new MessageResponse("Public ");
    }
    @GetMapping("/taxpayers")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TAXPAYER')")
    public List<TaxPayer> getAllTaxPayers() {
        return taxPayerService.getAllTaxPayers();
    }

    @GetMapping("/taxpayers/{id}")
    @PreAuthorize("hasRole('TAXPAYER') ")
    public TaxPayer getTaxPayer(@PathVariable int id) {
        return taxPayerService.getTaxPayer(id);
    }
    @GetMapping("/user-by-tin/{tin}")
    //@PreAuthorize("hasRole('TAXPAYER') ")
    public User getUserByTin(@PathVariable String tin) {
        return taxPayerService.getTaxPayerByTin(tin);
    }

    @GetMapping("/taxpayer-by-tin/{tin}")
    @PreAuthorize("hasRole('TAXPAYER') ")
    public User getTaxPayerByTin(@PathVariable String tin) {
        return taxPayerService.getTaxPayerByTin(tin);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse adminAccess() {
        return new MessageResponse("Admin zone");
    }
}
