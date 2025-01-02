package com.example.data.controller.csv;

import com.example.data.service.CsvFileInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ci")
public class CsvInsert01Controller {

    @Autowired
    CsvFileInsertService csvFileInsertService;

    @RequestMapping("/cp")
    String cicp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryFirstPageUp.csv";
        csvFileInsertService.csvToCpToMySQL(csvPath);
        return "cp성공";
    }

    @RequestMapping("/last")
    String cisp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryLastPage.csv";
        csvFileInsertService.okkyLastPageToMySQL(csvPath);
        return "성공";
    }
}
