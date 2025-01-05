package com.example.data.controller;

import com.example.data.entity.es.HumanEs;
import com.example.data.repository.HumanEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {
    @Autowired
    private HumanEsRepository humanEsRepository;

    @RequestMapping("/es/save")
    public HumanEs spr() {
        HumanEs h = HumanEs.builder()
                .nickname("강호동").age(12)
                .build();
        return humanEsRepository.save(h);
    }
    @RequestMapping("/es/read")
    public HumanEs saspr() {
        return humanEsRepository.findByNickname("강호동");
    }

    /*
        Optional<T> 클래스를 사용해 NPE(NullPointerException)를 방지할 수 있도록 도와줌
        Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스
     */

    @RequestMapping("/hello")
    public String aa() {
        return "hhheeelloo";
    }
}
