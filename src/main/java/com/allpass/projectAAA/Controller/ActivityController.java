package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Mail.EmailService;
import com.allpass.projectAAA.Mail.Mail;
import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Service.*;
import com.allpass.projectAAA.util.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Resource
    private MemberService memberService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityImageFileService activityImageFileService;
    @Resource
    private ArticleReviewService articleReviewService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleFileService articleFileService;
    @Resource
    private EmailService emailService;

    @Autowired
    public ActivityController(ActivityImageFileService activityImageFileService) {
        this.activityImageFileService = activityImageFileService;
    }
    //活動功能頁面
    @RequestMapping(value = "")
    private String activityFuctionPage(
            Model model
    ){
        return "activity";}

    //查看能參加活動
    @RequestMapping(value = "/list")
    private String activityListPage(Model model){

        List<Activity>activityList=activityService.getActivityList();
//        activityList.forEach(item->System.out.println(item.getActivityName()));
        List<String> img = new ArrayList<String>();
        List<String> activity_Study=new ArrayList<>();
        List<String> activity_Image = new ArrayList<String>();
        for(Activity image:activityList) {
                img.add(image.getActivityImg());
        }
        img.forEach(i->System.out.println(i));

        //System.out.println(img);

//            for(String image:img){
//            activity_Image.add(storageService.loadActivityImage(img).map(
//                    path -> MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
//                            "serveFile", path.getFileName().toString()).build().toString()));
//                .collect(Collectors.toList());
//        }

        activityImageFileService.loadActivityImage(img).forEach(
                path -> activity_Image.add(MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
                        "serveImageFile", path).build().toString()));
        activity_Image.forEach(item->System.out.println(item));
        System.out.println(activityList.size());
        for(int i=0;i<activityList.size();i++){
            if(!activity_Image.get(i).equals("http://127.0.0.1:8080/activity/activityImage/none")){
                Study study= Study.getStudy(activityList.get(i).getActivityStudy());
                activity_Study.add(study.getStudyName());
                activityList.get(i).setActivityImg(activity_Image.get(i));

            }
            else
                continue;
        }
        model.addAttribute("activity_Study",activity_Study);
        model.addAttribute("activityLists",activityList);
        return "activityList";
    }

    //參加活動
    @GetMapping(value = "/reviewerAttend")
    private String reviewerAttend(
            @RequestParam("id")Long id,
            Authentication authentication){
        Member activityParticipant=memberService.getMemberInfo(authentication.getName());
//        System.out.println(activityService.getActivityById(id).getActivityParticipants_Reviewer().size());
        Activity activityUpdate= activityService.getActivityById(id);
        activityUpdate.getActivityParticipants_Reviewer().add(activityParticipant);
        activityService.update(activityUpdate);
        activityService.getActivityById(id).getActivityParticipants_Reviewer().forEach(item->System.out.println(item.getName()));

//        }
        return "redirect:/activity";
    }
    @GetMapping(value = "/authorAttend")
    private String authorAttend(
            @RequestParam("id")Long id,
            Authentication authentication){
        Member activityParticipant=memberService.getMemberInfo(authentication.getName());
        System.out.println(activityService.getActivityById(id).getActivityParticipants_Author().size());
        Activity activityUpdate= activityService.getActivityById(id);
        activityUpdate.getActivityParticipants_Author().add(activityParticipant);
        activityService.update(activityUpdate);
        activityService.getActivityById(id).getActivityParticipants_Author().forEach(item->System.out.println(item.getName()));
//        }
        return "redirect:/activity";
    }

    //舉辦活動表單
    @RequestMapping(value = "/hold")
    private String activityHoid(){
        return "activityHold";
    }
//    params = {"activityName", "activityContent", "activityStart", "activityEnd", "activityImg", "articleNumber", "participantNumber"}
    //舉辦活動
    @PostMapping(value = "/hold")
    private String saveActivity(
            @RequestParam("activityName")String activityName,
            @RequestParam("activityContent")String activityContent,
            @RequestParam("dateRange")String dateRange,
            @RequestParam("activityImg") MultipartFile activityImg,
            @RequestParam("articleNumber")Integer articleNumber,
            @RequestParam("participantNumber")Integer participantNumber,
            @RequestParam("activityStudy")Integer ActivityStudy,
            Authentication authentication
    ){
        Activity activity=new Activity();
        activity.setActivityName(activityName);
        activity.setActivityContent(activityContent);
        activity.setActivityTime(dateRange);
        activity.setActivityStudy(ActivityStudy);
//        Set<Member> activityParticipants = new HashSet<>();
//        activityParticipants.add(memberService.getMemberInfo(authentication.getName()));
//        activity.setActivityParticipants(activityParticipants);
//        System.out.println(activityImg.getOriginalFilename());
        if(!activityImg.isEmpty()){
            activity.setActivityImg(activityImg.getOriginalFilename());
            //檔案上傳
            activityImageFileService.store(activityImg);
        }
        activity.setArticleNumber(articleNumber);
        activity.setLimitedParticipants(participantNumber);
        activity.setActivityOrganizer(memberService.getMemberInfo(authentication.getName()));
        activityService.save(activity);

        return "redirect:/activity";
    }

    //圖片連結
    @GetMapping("/activityImage/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveImageFile(@PathVariable String filename) {
        System.out.println(filename);
        org.springframework.core.io.Resource file = activityImageFileService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    //文章連結
    @GetMapping("/articleUpload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveArticleFile(@PathVariable String filename) throws UnsupportedEncodingException {
        System.out.println(filename);
        org.springframework.core.io.Resource file = articleFileService.loadAsResource(filename);
        String fileName= URLEncoder.encode(file.getFilename(),"UTF-16");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(file);
    }

    @RequestMapping(value = "/management")
    private String activityManagementPage(
            Authentication auth,
            Model model
    ){
        Long memberId = null;
        List<String> articleFileName=new ArrayList<>();
        if(auth.isAuthenticated()){
            memberId=memberService.getMemberInfo(auth.getName()).getId();
        }
        if(memberId == null) {
            return "redirect:/activity";
        }else{
            List<Activity> activityManagementList=activityService.getActivityInfoByActivityFounder(memberService.getMemberInfo(auth.getName()));


            for(int activityAmount=0;activityAmount<activityManagementList.size();activityAmount++){
                for(int articleAmount=0;articleAmount<activityManagementList.get(activityAmount).getArticle().size();articleAmount++){

                    List<ArticleReview> articleReviewList = new ArrayList<>();
                    String articleState = "";
                    String articleFile;
                    String articleURL;
                    List<Article> articleList=articleService.getArticleByActivity(activityManagementList.get(activityAmount));

                    if(!articleList.get(articleAmount).getArticleReviews().isEmpty()) {
                        articleList.get(articleAmount).getArticleReviews().forEach(i -> System.out.println(i.getId()));
                        articleList.get(articleAmount).getArticleReviews().forEach(i -> articleReviewList.add(i));
                        articleState=articleList.get(articleAmount).getArticleState();
                    }

                    articleFileName.add(articleList.get(articleAmount).getFileName());
                    articleURL=articleFileService.loadArticle(articleList.get(articleAmount).getFileName());
                    articleFile=MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
                            "serveArticleFile", articleURL).build().toString();
                    articleList.get(articleAmount).setUploadFile(articleFile);
                    System.out.println(articleFile);

                    if(!articleReviewList.isEmpty() && articleState.equals("reviewing")){
                        boolean isArticleReviewComplete=false;
                        boolean last=true;
                        for(ArticleReview a:articleReviewList) {

                            if(a.getReviewComplete() && last){
                                isArticleReviewComplete=true;
                                last=true;
                            }else {
                                last=false;
                                isArticleReviewComplete=false;
                            }
                        }
                        if(isArticleReviewComplete==true){
                            articleList.get(articleAmount).setArticleState("reviewFinish");
                        }else {
                            articleList.get(articleAmount).setArticleState("reviewing");
                        }

                    }
                }

            }


            model.addAttribute("activityManagementList",activityManagementList);
            model.addAttribute("articleFileName",articleFileName);

            return "activityManagement";
        }



    }
    @GetMapping(value = "/assign")
    private String assignMissionPage(
            @RequestParam("articleId")Long articleId,
            @RequestParam("activityId")Long activityId,
            Model model
    ){
            Activity activity=activityService.getActivityById(activityId);
            model.addAttribute("articleId",articleId);
            model.addAttribute("activity",activity);
            return "activityManagement";
    }

    @PostMapping(value = "/assign")
    private String assignMission(
            @RequestParam("articleId")Long articleId,
            @RequestParam("activityId")Long activityId,
            @RequestParam("reviewMember")String reviewMemberList
    ) throws IOException, MessagingException {
        List<String> reviewMember=new ArrayList<>();
        System.out.println(reviewMemberList);

        for(String a: reviewMemberList.split(",")){
            reviewMember.add(a);
        }
        if(reviewMember.size()<3){
            return "redirect:/activity/management";
        }
        Activity updateActivity=activityService.getActivityById(activityId);
        for (int i=0;i<reviewMember.size();i++){
            ArticleReview articleReview=new ArticleReview();
            System.out.println(reviewMember.get(i));
            Member invitingParticipants=memberService.getMemberInfo(reviewMember.get(i));
            articleReview.setMember(invitingParticipants);
//            updateActivity.getActivityParticipants_Reviewer().remove(memberService.getMemberInfo(reviewMember.get(i)));
//            activityService.save(updateActivity);
            articleReview.setArticle(articleService.getArticleById(articleId));
            articleReviewService.save(articleReview);
            articleService.getArticleById(articleId).setArticleState("assign");
            Mail mail = new Mail();
            mail.setFrom("no-reply@memorynotfound.com");
            mail.setTo(invitingParticipants.getEmail());
            mail.setSubject("<PaperReview!>您收到一篇審文邀請");

            Map<String, Object> model = new HashMap<>();
            model.put("name", invitingParticipants.getName());
            model.put("location", "Taipei");
            model.put("signature", "PaperReview");

            model.put("activityName",articleReview.getArticle().getActivity().getActivityName());
            model.put("activityHold",articleReview.getArticle().getActivity().getActivityOrganizer().getName());
            model.put("articleName",articleReview.getArticle().getArticleName());
            model.put("articleValue",articleReview.getArticle().getArticleValue());
            model.put("member_id",null);
//            model.put("articleReviewId",articleReview.getId());



            mail.setModel(model);

            emailService.sendSimpleMessage(mail);
        }

        return "redirect:/activity/management";
    }

}
