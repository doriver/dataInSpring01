package com.example.data.controller;

import com.example.data.entity.Sample01;
import com.example.data.repository.Sample01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired private Sample01Repository sample01Repository;


    @RequestMapping("/hh")
    public String aa(Model model) {
        List<Sample01> samList = sample01Repository.findAll();
        model.addAttribute("samList", samList);
        return "hhh";
    }

}
