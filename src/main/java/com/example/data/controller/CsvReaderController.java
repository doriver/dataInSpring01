package com.example.data.controller;

import com.example.data.service.CsvFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvReaderController {

    @Autowired
    CsvFileReaderService csvFileReaderService;

    @RequestMapping("/cr")
    String aa() {
        String csvPath = "classpath:static/data/okkyThree.csv";

        csvFileReaderService.csvFileReader(csvPath);
        return "성공?";
    }
}
