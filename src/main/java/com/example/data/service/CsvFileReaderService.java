package com.example.data.service;

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

@Service
public class CsvFileReaderService {

    @Autowired
    private ResourceLoader resourceLoader;

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
