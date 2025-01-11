package com.example.data.service.csv;

import com.example.data.entity.CrwlPost;
import com.example.data.entity.CrwlReply;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CsvFileReaderService {

    @Autowired
    private ResourceLoader resourceLoader;

    /*
        dataLoading브랜치에 csv읽어서 List<DTO객체>로 반환하는 메서드 있음
     */

    // 작은따옴표를 큰따옴표로 변환 (Java에서는 JSON은 반드시 큰따옴표를 사용해야 함)
    // String jsonString = str01.replace("'", "\"");

}
