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
import java.util.Random;

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

                    String _desc = record.get("desc");
                    int desc = Integer.parseInt(_desc);

                    String user = record.get("writer");
                    String title = record.get("title");
                    LocalDateTime postCreatedAt = LocalDateTime.parse(record.get("createdAt"));
//                    LocalDateTime dateTime = LocalDateTime.of(2000, 1, 10, 0, 0 + count*3);
//                    LocalDateTime LDT = LocalDateTime.now();
//                    LocalDateTime createAt = LDT.minusDays(Integer.parseInt(dayAgo));
                    String content = record.get("content");

                    String imgSrc = record.get("imgSrc");

                    String _views = record.get("viewCount");
                    int views = Integer.parseInt(_views);

                    String _likes = record.get("likeCount");
                    int likes = Integer.parseInt(_likes);


                    CrwlPost cp = CrwlPost.builder().descOrg(desc)
                            .userNickname(user).title(title).createdAt(postCreatedAt)
                            .content(content).imageSrc(imgSrc).viewCount(views).likeCount(likes)
                            .build();

                    String jsonStr = record.get("postReplyLists");
                    ObjectMapper objectMapper = new ObjectMapper(); // jackson databind
                    Random rd = new Random();
                    try {
                        List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});
                        LocalDateTime replyCreatedAt = postCreatedAt;
                        for (Map<String,Object> postReply :postReplyLists) {
                            // 댓글 생성 시간 임의로 정하는거
                            replyCreatedAt = replyCreatedAt.plusHours(rd.nextInt(7)).plusMinutes(rd.nextInt(60));

                            CrwlReply crwlReply = CrwlReply.builder()
                                    .userNickname((String)postReply.get("replyWriter")).content((String)postReply.get("replyText"))
                                    .createdAt(replyCreatedAt)
                                    .build();
                            cp.addreply(crwlReply);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    crwlPostRepository.save(cp);

                    count = count + 1;
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
