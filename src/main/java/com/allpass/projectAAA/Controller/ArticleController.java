package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Service.ArticleFileService;
import com.allpass.projectAAA.Service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleFileService articleFileService;


    @RequestMapping(value = "/post")
    public String addArticle(Model model){
        model.addAttribute("activityName");
        return "articlePost";}
    @PostMapping(value = "/post")
    public String getAddArticleView(
            @RequestParam("articleName")String articleName,
            @RequestParam("textNumber")Integer textNumber,
            @RequestParam("formulaNumber")Integer formulaNumber,
            @RequestParam("uploadFile") MultipartFile uploadFile

    ) {
        Article article=new Article();
        article.setArticleName(articleName);
        article.setFormulaNumber(formulaNumber);
        article.setTextNumber(textNumber);
        article.setUploadFile(uploadFile.getOriginalFilename());
        articleService.save(article);




        return "redirect:/";
    }

}
