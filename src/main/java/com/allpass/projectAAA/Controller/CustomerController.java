package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Do.Member;
import com.allpass.projectAAA.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class CustomerController {
    @Resource
    private MemberService memberService;
    @RequestMapping(value = "/regist")
    public String getAddCustomerView(){
        return "start";
    }
    @PostMapping(value = "/addCustomer",params = {"firstName","lastName"})
    public ModelAndView addCustomer(@RequestParam("firstName")String firtname, @RequestParam("lastName")String lastname, Member member){
//        customer.setId(CustomerNumberRandomUtil.randomMemberNumber());
        member.setName(firtname);
        member.setPassword(lastname);
        memberService.addMember(member);
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("user", firtname);
        return modelAndView;
    }
    //    @PostMapping(value = "/login",params = {"firstName","lastName"})
//    public ModelAndView login(@RequestParam("firstName")String firtname, @RequestParam("lastName")String lastname, Customer customer){
//        customerService.login(firtname,lastname);
//        ModelAndView modelAndView = new ModelAndView("hello");
//        modelAndView.addObject("user", lastname);
//        return modelAndView;
//    }
    @GetMapping("/findall")
    public ModelAndView  getall(){
        memberService.findAllUsername();

        ModelAndView modelAndView = new ModelAndView("hello");
        return modelAndView;
    }
}
