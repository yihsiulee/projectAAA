package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Service.ActivityImageFileService;
import com.allpass.projectAAA.Service.ActivityService;
import com.allpass.projectAAA.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String activityListPage(Model model){

        List<Activity>activityList=activityService.getActivityList();
//        activityList.forEach(item->System.out.println(item.getActivityName()));
        List<String> img = new ArrayList<String>();
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
                        "serveFile", path).build().toString()));
        activity_Image.forEach(item->System.out.println(item));
        System.out.println(activityList.size());
        for(int i=0;i<activityList.size();i++){
            if(!activity_Image.get(i).equals("http://127.0.0.1:8080/activity/files/none"))
                activityList.get(i).setActivityImg(activity_Image.get(i));
            else
                continue;
        }
        model.addAttribute("activityLists",activityList);
        model.addAttribute("image",activity_Image);
        return "activityList";
    }

    //參加活動
    @GetMapping(value = "/attend")
    private String attendActivity(
            @RequestParam("id")Long id,
            Authentication authentication){
        String authIdCardNumber=authentication.getName();
        System.out.println(id);
        System.out.println(activityService.getActivityById(id).getActivityParticipants().isEmpty());
        if(activityService.getActivityById(id).getActivityParticipants().isEmpty()){
            Set<Member> activityParticipants = new HashSet<>();
            Set<Activity> activity = new HashSet<Activity>();
            activityParticipants.add(memberService.getMemberInfo(authIdCardNumber));
            activity.add(activityService.getActivityById(id));
            memberService.getMemberInfo(authIdCardNumber).setActivityParticipant(activity);
            activityService.getActivityById(id).setActivityParticipants(activityParticipants);
            System.out.println(activityService.getActivityById(id).getActivityParticipants().size());
        }else{
            System.out.println(activityService.getActivityById(id).getActivityParticipants().size()+"haha");
            activityService.getActivityById(id).getActivityParticipants().add(memberService.getMemberInfo(authentication.getName()));
            memberService.getMemberInfo(authIdCardNumber).getActivityParticipant().add(activityService.getActivityById(id));
        }
        activityService.getActivityById(id).getActivityParticipants().forEach(item->System.out.println(item.getName()));
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
    private ModelAndView saveActivity(
            @RequestParam("activityName")String activityName,
            @RequestParam("activityContent")String activityContent,
            @RequestParam("dateRange")String dateRange,
            @RequestParam("activityImg") MultipartFile activityImg,
            @RequestParam("articleNumber")Integer articleNumber,
            @RequestParam("participantNumber")Integer participantNumber,
            Authentication authentication
    ){
        Activity activity=new Activity();
        List<String> activityTime = new ArrayList<String>();
        for (String s : dateRange.split("-")) {
            activityTime.add(s);
        }
        activity.setActivityName(activityName);
        activity.setActivityContent(activityContent);
        activity.setActivityStart(activityTime.get(0));
        activity.setActivityEnd(activityTime.get(1));
        System.out.println(activityImg.getOriginalFilename());
        if(!activityImg.isEmpty()){
            activity.setActivityImg(activityImg.getOriginalFilename());
            //檔案上傳
            activityImageFileService.store(activityImg);
        }
        activity.setArticleNumber(articleNumber);
        activity.setLimitedParticipants(participantNumber);
        activity.setActivityFounder(memberService.getMemberInfo(authentication.getName()));
        activityService.save(activity);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    //圖片連結
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
//        System.out.println(img);
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
