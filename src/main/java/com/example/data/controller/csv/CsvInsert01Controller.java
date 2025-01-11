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

    @RequestMapping("/es/200")
    String ci12003cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory200Es.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }

    @RequestMapping("/es/487")
    String ci148723cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory487Es.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5001")
    String ci1500123cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es01.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5002")
    String ci150023cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es02.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5003")
    String ci125003cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es03.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5004")
    String ci5004123cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es04.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5005")
    String ci150523cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es05.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5006")
    String ci1506523cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es06.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }
    @RequestMapping("/es/5007")
    String ci15057723cp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStory500Es07.csv";
        csvFileInsertService.csvFileToElasticsearch(csvPath);
        return "es성공";
    }


    @RequestMapping("/ms/cp")
    String cicp() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryFirstPageUp5002.csv";
        csvFileInsertService.csvToCpToMySQL(csvPath);
        return "cp성공";
    }

}
