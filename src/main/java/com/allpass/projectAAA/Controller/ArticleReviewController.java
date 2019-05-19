package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Service.ArticleFileService;
import com.allpass.projectAAA.Service.ArticleReviewService;
import com.allpass.projectAAA.Service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/articleReview")
public class ArticleReviewController {

    @Resource
    private ArticleReviewService articleReviewService;
    @Resource
    private MemberService memberService;
    @Resource
    private ArticleFileService articleFileService;

    private  static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    @RequestMapping(value = "list")
    private String articleReviewListPage(
            Authentication auth,
            Model model
    ){
        List<ArticleReview> articleReviewList=articleReviewService.getArticleReviewListByMember(memberService.getMemberInfo(auth.getName()));
        articleReviewList.forEach(i->System.out.println(i.getArticle().getArticleName()));
        model.addAttribute("articleReviewList",articleReviewList);
        return "articleReviewList";
    }


    @GetMapping("/post")
    private String saveArticleReviewPage(
            @RequestParam("articleReviewId")Long articleReviewId,
            Model model
    ){
        ArticleReview articleReview=articleReviewService.getArticleReviewById(articleReviewId);
        String articleFile;
        String articleFileName;
        String articleURL;
        articleFileName=articleReview.getArticle().getFileName();
        articleURL=articleFileService.loadArticle(articleReview.getArticle().getUploadFile());
        articleFile=MvcUriComponentsBuilder.fromMethodName(ArticleReviewController.class,
                "serveFile", articleURL).build().toString();
        articleReview.getArticle().setUploadFile(articleFile);

        model.addAttribute("articleReview",articleReview);
        return "articleReviewPost";
    }
    @PostMapping("/post")
    private String saveArticleReview(
            @RequestParam("articleReviewId")Long articleReviewId,
            @RequestParam("reviewText")String reviewText
    ){
        ArticleReview articleReview=articleReviewService.getArticleReviewById(articleReviewId);
        articleReview.setReviewText(reviewText);
        articleReview.setReviewTime(date.format(new Date()));
        articleReviewService.save(articleReview);
        return "redirect:/";
    }

    //文章連結
    @GetMapping("/articleUpload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) throws UnsupportedEncodingException {
        System.out.println(filename);
        org.springframework.core.io.Resource file = articleFileService.loadAsResource(filename);
        String fileName=URLEncoder.encode(file.getFilename(),"UTF-16");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(file);
    }

}
