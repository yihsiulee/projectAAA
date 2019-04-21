package com.allpass.projectAAA.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    @Resource
//    private articleService articleService;
    @RequestMapping(value = "/post")
    public ModelAndView addArticle(){

    }
    @PostMapping
    public String getAddArticleView() { return "articleReview";}

}
