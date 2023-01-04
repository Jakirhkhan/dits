package dits.gov.bd.tax.controller;


import dits.gov.bd.common.exception.ResourceNotFoundException;
import dits.gov.bd.tax.entity.Tax;
import dits.gov.bd.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/v1")
public class TaxController {
    @Autowired
    TaxService taxService;
    @GetMapping("/taxes")
    public List<Tax> all(){
        return taxService.findAll();
    }
    @GetMapping("/taxes/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        Tax tax = taxService.getTById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxpayer with ID: "+ id + "is not found"));
        return new ResponseEntity<>(tax, HttpStatus.OK);
    }
    @PostMapping("/taxes")
    @PreAuthorize("hasRole('TAXPAYER') ")
    public ResponseEntity<?> create(@RequestBody Tax tax){
        System.out.println("TAXPAYER");
        return new ResponseEntity<>(taxService.save(tax), HttpStatus.CREATED);
    }
    @PutMapping("/taxes")
    public ResponseEntity<?> update(Tax taxTransaction, String id){
        Tax existingTaxTransaction = taxService.getTById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tax Transaction not found with id- "+id));
        return new ResponseEntity<>(taxService.save(existingTaxTransaction), HttpStatus.OK);
    }

}