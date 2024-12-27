package com.example.data.service;

import com.example.data.entity.Sample01;
import com.example.data.entity.Sample02;
import com.example.data.repository.Sample01Repository;
import com.example.data.repository.Sample02Repository;
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
public class CsvFileInsertService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired private Sample01Repository sample01Repository;
    @Autowired private Sample02Repository sample02Repository;

    public void csvFileToMySQL(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);

            try (
                    Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()) ) {
                int count = 0;
                for (CSVRecord record: csvParser) {

                    String writer = record.get("writer");
                    String title = record.get("title");

                    String _views = record.get("views");
                    int views = Integer.parseInt(_views);
                    String _likes = record.get("likes");
                    int likes = Integer.parseInt(_likes);
                    String _comments = record.get("comments");
                    int comments = Integer.parseInt(_comments);

                    String content = record.get("content");

                    Sample02 smp = new Sample02();
                    smp.setWriter(writer);
                    smp.setTitle(title);
                    smp.setViews(views);
                    smp.setLikes(likes);
                    smp.setComments(comments);
                    smp.setContent(content);

                    sample02Repository.save(smp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
