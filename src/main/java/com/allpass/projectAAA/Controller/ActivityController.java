package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Service.ActivityImageFileService;
import com.allpass.projectAAA.Service.ActivityService;
import com.allpass.projectAAA.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Resource
    private MemberService memberService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityImageFileService activityImageFileService;

    @Autowired
    public ActivityController(ActivityImageFileService activityImageFileService) {
        this.activityImageFileService = activityImageFileService;
    }
    //活動功能頁面
    @RequestMapping(value = "")
    private String activityPage(){return "activity";}
    //查看能參加活動
    @RequestMapping(value = "/list")
    private String activityListPage(ModelAndView modelAndView){
        modelAndView.setViewName("activityList");
        List<Activity>activityList=activityService.getActivityList();
        modelAndView.addObject("activityList",activityList);
        return "activityList";
    }
    //參加活動
    @PostMapping(value = "/attend")
    private String attendActivity(
            @RequestParam("activity_Id")Long activity_Id,
            Authentication authentication){
        return "redirect:/activity";
    }
    //舉辦活動表單
    @RequestMapping(value = "/hold")
    private String activityHoid(){ return "activityHold";}
//    params = {"activityName", "activityContent", "activityStart", "activityEnd", "activityImg", "articleNumber", "participantNumber"}
    //舉辦活動
    @PostMapping(value = "/hold")
    private ModelAndView saveActivity(
            @RequestParam("activityName")String activityName,
            @RequestParam("activityContent")String activityContent,
            @RequestParam("activityStart")String activityStart,
            @RequestParam("activityEnd")String activityEnd,
            @RequestParam("activityImg") MultipartFile activityImg,
            @RequestParam("articleNumber")Integer articleNumber,
            @RequestParam("participantNumber")Integer participantNumber,
            Authentication authentication
    ){
        Activity activity=new Activity();
        activity.setActivityName(activityName);
        activity.setActivityContent(activityContent);
        activity.setActivityStart(activityStart);
        activity.setActivityEnd(activityEnd);
        System.out.println(activityImg.getOriginalFilename());
        activity.setActivityImg(activityImg.getOriginalFilename());
        //檔案上傳
        activityImageFileService.store(activityImg);
        activity.setArticleNumber(articleNumber);
        activity.setLimitedParticipants(participantNumber);
        activity.setActivityFounder(memberService.getMemberInfo(authentication.getName()));
        activityService.save(activity);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    //
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) {
        System.out.println(filename);
        org.springframework.core.io.Resource file = activityImageFileService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    //
    @RequestMapping(value = "/info")
    private ModelAndView info( Authentication authentication){
        List<Activity> activity=activityService.getActivityInfoByActivityFounder(memberService.getMemberInfo(authentication.getName()));
        List<String> img = new ArrayList<String>();
        for(Activity image:activity){
            img.add(image.getActivityImg());
        }
        System.out.println(img);
//        Path file = storageService.load(img);
        List<String> activity_Image = new ArrayList<String>();
//            for(String image:img){
//            activity_Image.add(storageService.loadActivityImage(img).map(
//                    path -> MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
//                            "serveFile", path.getFileName().toString()).build().toString()));
//                .collect(Collectors.toList());
//        }
        activityImageFileService.loadActivityImage(img).forEach(
                path -> activity_Image.add(MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
                        "serveFile", path).build().toString()));

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("activityManagement");
        modelAndView.addObject("image",activity_Image);
       System.out.println(activity_Image);
        return modelAndView;
    }


}
