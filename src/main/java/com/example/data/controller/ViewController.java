package com.example.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    @RequestMapping("/hh")
    public String aa(Model model) {

//        model.addAttribute("samList", samList);
        return "hhh";
    }

}
