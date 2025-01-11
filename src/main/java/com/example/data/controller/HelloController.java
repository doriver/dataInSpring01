package com.example.data.controller;

import com.example.data.dto.SampleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class HelloController {


    /*
        Optional<T> 클래스를 사용해 NPE(NullPointerException)를 방지할 수 있도록 도와줌
        Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스
     */


    @RequestMapping("/hello")
    public String aa() {
        return "hhheeelloo";
    }

    @RequestMapping("/test")
    public SampleDTO a123a() {

        List<Map<String, String>> llmm = new ArrayList<>();

        Map<String, String> m1 = new HashMap<>();
        m1.put("name", "강호동");
        m1.put("age", "23");

        Map<String, String> m2 = new HashMap<>();
        m2.put("name", "유재석");
        m2.put("age", "45");

        llmm.add(m1);
        llmm.add(m2);

        SampleDTO sdto = new SampleDTO();
        sdto.setIi(12);
        sdto.setSs("asdf");
        sdto.setLm(llmm);

        return sdto;
    }
}
