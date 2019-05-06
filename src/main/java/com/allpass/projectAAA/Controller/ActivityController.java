package com.allpass.projectAAA.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
    @RequestMapping(value = "/hold")
    private String activityHoid(){ return "activityHold";}
    @PostMapping(value = "hold")
    private ModelAndView saveActivity(){


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
