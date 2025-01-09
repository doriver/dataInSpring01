package com.example.data.controller;

import com.example.data.entity.CrwlPost;
import com.example.data.entity.es.PostEs;
import com.example.data.repository.CrwlPostRepository;
import com.example.data.repository.PostEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private CrwlPostRepository crwlPostRepository;
    @Autowired private PostEsRepository postEsRepository;

    @RequestMapping("/es/title")
    public String aa12tt3es(Model model) {

        Pageable pageable = PageRequest.of(0, 10); // 첫 번째 페이지에서 10개 가져오기
        List<PostEs> pesList = postEsRepository.findByContent("코딩", pageable);

        model.addAttribute("pesList", pesList);
        return "esTitles";
    }

    @RequestMapping("/es/html")
    public String aa123es(Model model) {
        Optional<PostEs> oPes = postEsRepository.findById("9vLPSZQBWEAta9SJUoRi");
        PostEs pes = oPes.get();

        model.addAttribute("pes", pes);
        return "esView";
    }


    @RequestMapping("/es/hh")
    public String aaes(Model model) {

        Iterable<PostEs> pesList = postEsRepository.findAll();

        model.addAttribute("pesList", pesList);
        return "hhhss";
    }


    @RequestMapping("/hh")
    public String aa(Model model) {
        List<CrwlPost> cpList = crwlPostRepository.findAll(); // 댓글까지 싹가져옴, 그냥 글만 가져오게도 할수 있겠지?
        model.addAttribute("cpList", cpList);
        return "hhh";
    }

    @RequestMapping("/vv")
    public String vvaa() {

        return "viewTest";
    }
}
