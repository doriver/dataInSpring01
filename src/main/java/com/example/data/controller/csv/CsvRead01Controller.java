package com.example.data.controller.csv;

import com.example.data.service.csv.CsvFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvRead01Controller {

    @Autowired
    CsvFileReaderService csvFileReaderService;

}
