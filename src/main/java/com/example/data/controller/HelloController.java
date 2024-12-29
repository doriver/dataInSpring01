package com.example.data.controller;

import com.example.data.entity.AAa;
import com.example.data.entity.Sample01;
import com.example.data.repository.AaRepository;
import com.example.data.repository.Sample01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @Autowired private AaRepository aaRepository;
    @Autowired private Sample01Repository sample01Repository;

    @RequestMapping("/rsaved")
    public List<Sample01> spr() {
        List<Sample01> sam = sample01Repository.findAll();
        return sam;
    }


    @RequestMapping("/c")
    public String cc() {
        AAa a = new AAa();
        a.setINt(9);
        a.setSTr("aannxx");
        a.setLdt(LocalDateTime.now());
        aaRepository.save(a);
        return "성공?";
    }

    /*
        Optional<T> 클래스를 사용해 NPE(NullPointerException)를 방지할 수 있도록 도와줌
        Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스
     */
    @RequestMapping("/r")
    public String rr() {
        // jpa 저 메소드 기본 리턴타입이 optional임
        Optional<AAa> a = aaRepository.findById(1L);
        System.out.println(a.get().getINt() + a.get().getSTr());
        return "성공?";
    }

    @RequestMapping("/hello")
    public String aa() {
        return "hhheeelloo";
    }
}
