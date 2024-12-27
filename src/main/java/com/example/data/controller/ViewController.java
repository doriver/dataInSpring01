package com.example.data.controller;

import com.example.data.entity.Sample01;
import com.example.data.entity.Sample02;
import com.example.data.repository.AaRepository;
import com.example.data.repository.Sample01Repository;
import com.example.data.repository.Sample02Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired private Sample01Repository sample01Repository;
    @Autowired private Sample02Repository sample02Repository;

    @RequestMapping("/hh")
    public String aa(Model model) {
        List<Sample01> samList = sample01Repository.findAll();
        model.addAttribute("samList", samList);
        return "hhh";
    }

    @RequestMapping("/hh22")
    public String aa22(Model model) {
        List<Sample02> samList = sample02Repository.findAll();
        model.addAttribute("samList", samList);
        return "hhh";
    }
}
