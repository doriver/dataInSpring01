package com.example.data.controller.csv;

import com.example.data.service.csv.CsvFileInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ci")
public class CsvInsert01Controller {

    @Autowired
    private CsvFileInsertService csvFileInsertService;

    @RequestMapping("/es/p")
    String ci123cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryFirstPage200.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }


    @RequestMapping("/ms/cp")
    String cicp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryFirstPageUp5002.csv";
        csvFileInsertService.csvToCpToMySQL(csvPath);
        return "cp성공";
    }

    @RequestMapping("/ms/last")
    String cisp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryLastPage.csv";
        csvFileInsertService.okkyLastPageToMySQL(csvPath);
        return "성공";
    }
}
