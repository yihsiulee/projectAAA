package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Service.*;
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
import java.util.Set;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleFileService articleFileService;
    @Resource
    private MemberService memberService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ArticleReviewService articleReviewService;

    private  static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @RequestMapping(value = "")
    private String articleFuctionPage(){
        return "article";
    }




    //文章上傳頁面
    @RequestMapping(value = "/post")
    public String UploadArticlePage(Model model,
                                   Authentication authentication){
        if(memberService.getMemberInfo(authentication.getName()).getActivityParticipant_Author().isEmpty()){
            return "redirect:/article";
        }
        Set<Activity> activityList=memberService.getMemberInfo(authentication.getName()).getActivityParticipant_Author();
        activityList.forEach(i->System.out.println(i.getActivityName()));
        model.addAttribute("activityList",activityList);
        return "articlePost";
    }

    //文章上傳
    @PostMapping(value = "/post")
    public String UploadArticle(
            @RequestParam("activityId")Long activity_Id,
            @RequestParam("articleName")String articleName,
            @RequestParam("textNumber")Integer textNumber,
            @RequestParam("formulaNumber")Integer formulaNumber,
            @RequestParam("uploadFile") MultipartFile uploadFile,
            @RequestParam("articleValue")Double articleValue,
            Authentication auth
    ) {
        Article article=new Article();
        article.setPostTime(date.format(new Date()));
        article.setArticleName(articleName);
        article.setActivity(activityService.getActivityById(activity_Id));
        article.setFormulaNumber(formulaNumber);
        article.setTextNumber(textNumber);
        article.setArticleValue(articleValue);
        article.setAuthor(memberService.getMemberInfo(auth.getName()));
        if(!uploadFile.isEmpty()){
            article.setFileName(uploadFile.getOriginalFilename());
            //檔案上傳
            articleFileService.store(uploadFile);
        }
        articleService.save(article);
        activityService.getActivityById(activity_Id).getActivityParticipants_Author().remove(memberService.getMemberInfo(auth.getName()));
        return "redirect:/article";
    }

//    //文章連結
//    @GetMapping("/articleFile/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) {
//        System.out.println(filename);
//        org.springframework.core.io.Resource file = articleFileService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }


}
