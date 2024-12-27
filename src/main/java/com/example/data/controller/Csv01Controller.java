package com.example.data.controller;

import com.example.data.service.CsvFileInsertService;
import com.example.data.service.CsvFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Csv01Controller {

    @Autowired
    CsvFileReaderService csvFileReaderService;
    @Autowired
    CsvFileInsertService csvFileInsertService;

    @RequestMapping("/ci")
    String ci() {
        String csvPath = "classpath:static/data/okkySample01.csv";
        csvFileInsertService.csvFileToMySQL(csvPath);
        return "성공";
    }

    @RequestMapping("/cr")
    String aa() {
        String csvPath = "classpath:static/data/okkyThree.csv";

        csvFileReaderService.csvFileReader(csvPath);
        return "성공?";
    }
}
