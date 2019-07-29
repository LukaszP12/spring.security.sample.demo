package com.spring.security.sample.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

// jest to klasa, która świadczy usługi restowe
@RestController
public class QuotationApi {

    private List<Quotation> quotations;

    public QuotationApi() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("To, że milczy nie znaczy, że nie ma nic do powiedzenia","Jonathan Caroll"));
        quotations.add(new Quotation("Lepiej zaliczać się do niektórzych, niż do wszystkich","Andrzej Sapkowski"));
    }

    @GetMapping("/api")
    public List<Quotation> getQuotations(){
        return quotations;
    }

    @PostMapping("/api/")
    public boolean addQuotations(@RequestBody Quotation quotation){
        return quotations.add(quotation);
    }

    @DeleteMapping("/api")
    public void deleteQuotation(@RequestParam int index){
         quotations.remove(index);
    }

}
