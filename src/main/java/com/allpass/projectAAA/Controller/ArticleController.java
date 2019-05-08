package com.allpass.projectAAA.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
//    @Resource
//    private articleService articleService;
    @RequestMapping(value = "/post")
    public String addArticle(){ return "articlePost";}
//    @PostMapping
//    public String getAddArticleView() { return "articleReview";}

}
