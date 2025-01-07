package com.example.data.service.csv;

import com.example.data.entity.CrwlPost;
import com.example.data.entity.CrwlReply;

import com.example.data.entity.es.Category;
import com.example.data.entity.es.PostEs;
import com.example.data.entity.es.ReplyEs;
import com.example.data.repository.CrwlPostRepository;
import com.example.data.repository.PostEsRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CsvFileInsertService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired private CrwlPostRepository crwlPostRepository;
    @Autowired private PostEsRepository postEsRepository;

    public void csvFileToElasticsearch(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);
            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            List<CSVRecord> csvRowList = csvParser.getRecords();
            int csvRowSize = csvRowList.size(); // csv row개수 맞음

            for (int i = 0; i < csvRowSize; i++) {
                try {
                    CSVRecord record = csvRowList.get(i);

                    String writer = record.get("writer");
                    String title = record.get("title");
                    String content = record.get("content");

                    String _view = record.get("viewCount");
                    int viewCount = Integer.parseInt(_view);
                    String _like = record.get("likeCount");
                    int likeCount = Integer.parseInt(_like);

                    LocalDateTime createdAt = LocalDateTime.parse(record.get("createdAt"));

                    String jsonStr = record.get("postReplyLists");
                    ObjectMapper objectMapper = new ObjectMapper(); // jackson databind
                    Random rd = new Random();

                    List<ReplyEs> replies = new ArrayList<>();

                    try {
                        // json문자열을 java타입으로 파징
                        List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});
                        LocalDateTime replyCreatedAt = createdAt;
                        for (Map<String,Object> postReply :postReplyLists) {
                            // 댓글 생성 시간 임의로 정하는거
                            replyCreatedAt = replyCreatedAt.plusHours(rd.nextInt(7)).plusMinutes(rd.nextInt(60));

                            ReplyEs crwlReply = ReplyEs.builder()
                                    .nickname((String)postReply.get("replyWriter"))
                                    .content((String)postReply.get("replyText"))
                                    .createdAt(replyCreatedAt)
                                    .build();

                            replies.add(crwlReply);
                        }
                    // 댓글 관련 try문
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    PostEs postEs = PostEs.builder()
                            .nickname(writer).title(title).content(content).category(Category.GENERAL)
                            .viewCount(viewCount).likeCount(likeCount).replies(replies).createdAt(createdAt)
                            .build();
                    postEsRepository.save(postEs);
                // for문에 있는 try문
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (i % 100 == 0) {
                    System.out.println("  =====   =====   " + i + "  =====   =====   ");
                }


            } // for문 끝
        // 매서드 처음 try문
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void csvToCpToMySQL(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);
            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            // csv파일을 파징함
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            int count = 1;
            for (CSVRecord record: csvParser) {
                try {
                    // csv에서 각 row마다 @Transactional걸어서 insert함
                    saveTransactionCsvToCpToMySQL(record);

                    if (count % 100 == 0) {
                        System.out.println(count);
                    }
                    count = count + 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveTransactionCsvToCpToMySQL(CSVRecord record) {
        String _desc = record.get("desc");
        int desc = Integer.parseInt(_desc);

        String user = record.get("writer");
        String title = record.get("title");
        LocalDateTime postCreatedAt = LocalDateTime.parse(record.get("createdAt"));
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
                                                        // json문자열을 java타입으로 파징
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
    }

    // 마지막 페이지 넣었던거
    @Transactional
    public void okkyLastPageToMySQL(String csvPath) {
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
                    String content = record.get("content");

                    String imgSrc = record.get("imgSrc");

                    String _views = record.get("viewCount");
                    int views = Integer.parseInt(_views);

                    String _likes = record.get("likeCount");
                    int likes = Integer.parseInt(_likes);

                    LocalDateTime dateTime = LocalDateTime.of(2000, 1, 10, 0, 0 + count*3);

                    CrwlPost cp = CrwlPost.builder().descOrg(desc)
                            .userNickname(user).title(title)
                            .content(content).imageSrc(imgSrc).viewCount(views).likeCount(likes)
                            .createdAt(dateTime)
                            .build();

                    String jsonStr = record.get("postReplyLists");
                    ObjectMapper objectMapper = new ObjectMapper(); // jackson databind
                    try {
                        List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});
                        for (Map<String,Object> postReply :postReplyLists) {
                            CrwlReply crwlReply = CrwlReply.builder()
                                    .userNickname((String)postReply.get("replyWriter")).content((String)postReply.get("replyText"))
                                    .createdAt(dateTime.plusMinutes(2L))
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

}
