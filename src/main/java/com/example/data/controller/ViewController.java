package com.example.data.controller;

import com.example.data.entity.CrwlPost;
import com.example.data.repository.CrwlPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private CrwlPostRepository crwlPostRepository;

    @RequestMapping("/hh")
    public String aa(Model model) {
        List<CrwlPost> cpList = crwlPostRepository.findAll(); // 댓글까지 싹가져옴, 그냥 글만 가져오게도 할수 있겠지?
        model.addAttribute("cpList", cpList);
        return "hhh";
    }

}
