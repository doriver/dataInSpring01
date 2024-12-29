package com.example.data.service;

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
import java.util.List;
import java.util.Map;

@Service
public class CsvFileReaderService {

    @Autowired
    private ResourceLoader resourceLoader;

    /*
    문자열 [{'user': 'allinux', 'content': '어디로 이직할 계획인데요? 계획도 모르는데 조언도 불가능하겠지요.', 'createAt': '6일전'}]
    를 List<Map<String, Object>> 로 받기
     */
    public void csvFileReader12(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);

            try (
                    Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()) ) {

                for (CSVRecord record: csvParser) {

                    String str01 = record.get("postReplyLists");
//                    System.out.println(str01);
                    // 작은따옴표를 큰따옴표로 변환 (Java에서는 JSON은 반드시 큰따옴표를 사용해야 함)
//                    String jsonString = str01.replace("'", "\"");

                    // Jackson의 ObjectMapper는 JSON 데이터를 자바 객체로 변환하는 데 널리 사용
                    ObjectMapper objectMapper = new ObjectMapper(); // jackson databind
                    try {
                        List<Map<String,Object>> postReplyLists = objectMapper.readValue(str01, new TypeReference<List<Map<String,Object>>>() {});
                                                                                              // TypeReference<List<Map<String, Object>>>를 사용하여 제네릭 타입을 지정
                        System.out.println(postReplyLists);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void csvFileReader(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);

            try (
                    Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()) ) {
                int count = 0;
                for (CSVRecord record: csvParser) {
                    String category = record.get("category");
                    String tag = record.get("tag");
                    String user = record.get("user");
                    String title = record.get("title");
                    String content = record.get("content");
                    String imgSrc = record.get("imgSrc");
                    String createAt = record.get("createAt");
                    String viewCount = record.get("viewCount");
                    String likeCount = record.get("likeCount");
                    String postReplyLists = record.get("postReplyLists");

                    System.out.println("       =========     ===========    ==========   ");
                    System.out.println(category);
                    System.out.println(tag);
                    System.out.println(user);
                    System.out.println(title);
                    System.out.println(content);
                    System.out.println(imgSrc);
                    System.out.println(createAt);
                    System.out.println(viewCount);
                    System.out.println(likeCount);
                    System.out.println(postReplyLists);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
