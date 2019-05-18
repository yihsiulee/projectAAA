package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Service.ArticleReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/articleReview")
public class ArticleReviewController {

    @Resource
    ArticleReviewService articleReviewService;

    @RequestMapping("/list")
    private String articleReviewListPage(){
        return "articleReviewList";
    }


    @RequestMapping("/post")
    private String saveArticleReviewPage(){
        return "articleFeedback";
    }
    @PostMapping("/post")
    private String saveArticleReview(){
        ArticleReview articleReview=new ArticleReview();
        articleReviewService.save(articleReview);
        return "redirect:/";
    }

}
