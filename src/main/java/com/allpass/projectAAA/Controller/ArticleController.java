package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Service.ArticleFileService;
import com.allpass.projectAAA.Service.ArticleService;
import com.allpass.projectAAA.Service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleFileService articleFileService;
    @Resource
    private MemberService memberService;

    private  static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    //文章上傳頁面
    @RequestMapping(value = "/post")
    public String addArticle(Model model){

        model.addAttribute("activityName");
        return "articlePost";
    }

    //文章上傳
    @PostMapping(value = "/post")
    public String getAddArticleView(
            @RequestParam("articleName")String articleName,
            @RequestParam("textNumber")Integer textNumber,
            @RequestParam("formulaNumber")Integer formulaNumber,
            @RequestParam("uploadFile") MultipartFile uploadFile,
            Authentication auth
    ) {
        Article article=new Article();
        article.setPostTime(date.format(new Date()));
        article.setArticleName(articleName);
        article.setFormulaNumber(formulaNumber);
        article.setTextNumber(textNumber);
        article.setAuthor(memberService.getMemberInfo(auth.getName()));
        article.setUploadFile(uploadFile.getOriginalFilename());
        articleService.save(article);




        return "redirect:/";
    }

}
