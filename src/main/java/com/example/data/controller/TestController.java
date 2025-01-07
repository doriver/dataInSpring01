package com.example.data.controller;

import com.example.data.dto.SampleDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/test")
    public SampleDTO aa() {

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
