package com.example.data.controller.csv;

import com.example.data.dto.CrwlDTO;
import com.example.data.service.csv.CsvFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CsvRead01Controller {

    @Autowired
    CsvFileReaderService csvFileReaderService;

    @RequestMapping("/cr/dto")
    List<CrwlDTO> aa123() {
        String csvPath = "classpath:static/crwl/okkyReal/okkyLifeStoryFirstPageUp500Es6.csv";
        List<CrwlDTO> viewData = csvFileReaderService.csvToDto(csvPath);

        return viewData;
    }


}
