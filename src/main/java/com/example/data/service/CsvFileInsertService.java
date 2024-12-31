package com.example.data.service;

import com.example.data.entity.CrwlPost;
import com.example.data.entity.CrwlReply;
import com.example.data.entity.Sample01;
import com.example.data.repository.CrwlPostRepository;
import com.example.data.repository.Sample01Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CsvFileInsertService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired private Sample01Repository sample01Repository;

    @Autowired private CrwlPostRepository crwlPostRepository;

    @Transactional
    public void csvToCpToMySQL(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);

            try (
                    Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()) ) {
                int count = 0;
                for (CSVRecord record: csvParser) {

                    String category = record.get("category");
                    String user = record.get("user");
                    String title = record.get("title");
                    String content = record.get("content");

                    String _views = record.get("viewCount");
                    int views = Integer.parseInt(_views);

                    String _likes = record.get("likeCount");
                    int likes = Integer.parseInt(_likes);

                    String dayAgo = record.get("createAt");

                    LocalDateTime LDT = LocalDateTime.now();
                    LocalDateTime createAt = LDT.minusDays(Integer.parseInt(dayAgo));

                    CrwlPost cp = CrwlPost.builder()
                            .userNickname(user).title(title)
                            .content(content).viewCount(views).likeCount(likes)
                            .createdAt(createAt)
                            .build();

                    String jsonStr = record.get("postReplyLists");
                    ObjectMapper objectMapper = new ObjectMapper(); // jackson databind
                    try {
                        List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});
                        for (Map<String,Object> postReply :postReplyLists) {
                            CrwlReply crwlReply = CrwlReply.builder()
                                    .userNickname((String)postReply.get("user")).content((String)postReply.get("content"))
                                    .createdAt(LDT.minusDays(Integer.parseInt((String) postReply.get("createAt"))))
                                    .build();
                            cp.addreply(crwlReply);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    


                    crwlPostRepository.save(cp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

                    Sample01 smp = new Sample01();
                    smp.setWriter(writer);
                    smp.setTitle(title);
                    smp.setViews(views);
                    smp.setLikes(likes);
                    smp.setComments(comments);
                    smp.setContent(content);

                    sample01Repository.save(smp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
