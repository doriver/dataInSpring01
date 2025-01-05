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


    public void csvFileReader(String csvPath) {
        try {
            Resource resource = resourceLoader.getResource(csvPath);
            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            List<CSVRecord> csvRowList = csvParser.getRecords();
            int csvRowSize = csvRowList.size(); // csv row개수 맞음

            for (int i = 0; i < 3; i++) {
                CSVRecord record = csvRowList.get(i);

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

                String jsonStr = record.get("postReplyLists");
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});

                System.out.println("   ########    ########     ########  " + i + "  ########    ########   ######## ");
                System.out.println(desc);
                System.out.println("       =========     ==== 글쓴이 =====    ==========   ");
                System.out.println(user);
                System.out.println("       =========     ==== 제목 =====    ==========   ");
                System.out.println(title);
                System.out.println("       =========     ==== 작성시간 =====    ==========   ");
                System.out.println(postCreatedAt);
                System.out.println("       =========     ====  내용 =====    ==========   ");
                System.out.println(content);
                System.out.println("       =========     ====  이미지 링크 =====    ==========   ");
                System.out.println(imgSrc);
                System.out.println("       =========     ====  조회수 =====    ==========   ");
                System.out.println(views);
                System.out.println("       =========     ====  좋아요 수 =====    ==========   ");
                System.out.println(likes);
                System.out.println("       =========     ====  댓글들 =====    ==========   ");
                for (Map<String,Object> postReply :postReplyLists) {
                    System.out.println("       ====   댓글작성자   ====   ");
                    System.out.println(postReply.get("replyWriter"));
                    System.out.println("       ====   댓글내용   ====   ");
                    System.out.println(postReply.get("replyText"));
                }
            }


            for (int i = csvRowSize/2; i < csvRowSize/2 + 3; i++) {
                CSVRecord record = csvRowList.get(i);

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

                String jsonStr = record.get("postReplyLists");
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});

                System.out.println("   ########    ########     ########  " + i + "  ########    ########   ######## ");
                System.out.println(desc);
                System.out.println("       =========     ==== 글쓴이 =====    ==========   ");
                System.out.println(user);
                System.out.println("       =========     ==== 제목 =====    ==========   ");
                System.out.println(title);
                System.out.println("       =========     ==== 작성시간 =====    ==========   ");
                System.out.println(postCreatedAt);
                System.out.println("       =========     ====  내용 =====    ==========   ");
                System.out.println(content);
                System.out.println("       =========     ====  이미지 링크 =====    ==========   ");
                System.out.println(imgSrc);
                System.out.println("       =========     ====  조회수 =====    ==========   ");
                System.out.println(views);
                System.out.println("       =========     ====  좋아요 수 =====    ==========   ");
                System.out.println(likes);
                System.out.println("       =========     ====  댓글들 =====    ==========   ");
                for (Map<String,Object> postReply :postReplyLists) {
                    System.out.println("       ====   댓글작성자   ====   ");
                    System.out.println(postReply.get("replyWriter"));
                    System.out.println("       ====   댓글내용   ====   ");
                    System.out.println(postReply.get("replyText"));
                }
            }

            for (int i = csvRowSize-1; i > csvRowSize-4; i--) {
                CSVRecord record = csvRowList.get(i);

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

                String jsonStr = record.get("postReplyLists");
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String,Object>> postReplyLists = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String,Object>>>() {});

                System.out.println("   ########    ########     ########  " + i + "  ########    ########   ######## ");
                System.out.println(desc);
                System.out.println("       =========     ==== 글쓴이 =====    ==========   ");
                System.out.println(user);
                System.out.println("       =========     ==== 제목 =====    ==========   ");
                System.out.println(title);
                System.out.println("       =========     ==== 작성시간 =====    ==========   ");
                System.out.println(postCreatedAt);
                System.out.println("       =========     ====  내용 =====    ==========   ");
                System.out.println(content);
                System.out.println("       =========     ====  이미지 링크 =====    ==========   ");
                System.out.println(imgSrc);
                System.out.println("       =========     ====  조회수 =====    ==========   ");
                System.out.println(views);
                System.out.println("       =========     ====  좋아요 수 =====    ==========   ");
                System.out.println(likes);
                System.out.println("       =========     ====  댓글들 =====    ==========   ");
                for (Map<String,Object> postReply :postReplyLists) {
                    System.out.println("       ====   댓글작성자   ====   ");
                    System.out.println(postReply.get("replyWriter"));
                    System.out.println("       ====   댓글내용   ====   ");
                    System.out.println(postReply.get("replyText"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 작은따옴표를 큰따옴표로 변환 (Java에서는 JSON은 반드시 큰따옴표를 사용해야 함)
    // String jsonString = str01.replace("'", "\"");

}
