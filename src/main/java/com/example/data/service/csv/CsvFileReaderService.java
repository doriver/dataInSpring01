package com.example.data.service.csv;

import com.example.data.dto.CrwlDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CsvFileReaderService {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<CrwlDTO> csvToDto(String csvPath) {

        List<CrwlDTO> dtoList = new ArrayList<>();
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

                CrwlDTO cd = CrwlDTO.builder()
                        .writer(user).title(title).createdAt(postCreatedAt)
                        .content(content).imgSrc(imgSrc).views(views).likes(likes)
                        .postReplyLists(postReplyLists).build();

                dtoList.add(cd);
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

                CrwlDTO cd = CrwlDTO.builder()
                        .writer(user).title(title).createdAt(postCreatedAt)
                        .content(content).imgSrc(imgSrc).views(views).likes(likes)
                        .postReplyLists(postReplyLists).build();

                dtoList.add(cd);
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

                CrwlDTO cd = CrwlDTO.builder()
                        .writer(user).title(title).createdAt(postCreatedAt)
                        .content(content).imgSrc(imgSrc).views(views).likes(likes)
                        .postReplyLists(postReplyLists).build();

                dtoList.add(cd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtoList;
    }

    // 작은따옴표를 큰따옴표로 변환 (Java에서는 JSON은 반드시 큰따옴표를 사용해야 함)
    // String jsonString = str01.replace("'", "\"");

}
