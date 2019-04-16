package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Do.Member;
import com.allpass.projectAAA.Service.MemberService;
import com.allpass.projectAAA.util.MemberIdRandomUtil;
import com.allpass.projectAAA.util.MemberVerificationAndValidationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @RequestMapping(value = "/regist")
    public String getAddCustomerView(){
        return "createaccount";
    }
    @PostMapping(value = "/addMember",params = {"name","idCardNumber","password1","password2","email","phone","gender","birthday","educational","study","special"})
    public ModelAndView addCustomer(@RequestParam("name")String name,
                                    @RequestParam("idCardNumber")String idCardNumber,
                                    @RequestParam("password1")String password1,
                                    @RequestParam("password2")String password2,
                                    @RequestParam("email")String email,
                                    @RequestParam("phoneNumber")String phoneNumber,
                                    @RequestParam("gender")Integer gender,
                                    @RequestParam("birthday")String birthday,
                                    @RequestParam("educational")Integer educational,
                                    @RequestParam("study")Integer study,
                                    @RequestParam("special")String special,
                                    Member member) {
        boolean passwordVerfication = MemberVerificationAndValidationUtil.MemberPasswordVerification(password1, password2);
        if (passwordVerfication) {
            member.setId(MemberIdRandomUtil.randomMemberNumber());
            member.setName(name);
            member.setIdCardNumber(idCardNumber);
            member.setPassword(password1);
            member.setEmail(email);

            memberService.addMember(member);
            ModelAndView modelAndView = new ModelAndView("login");
//        modelAndView.addObject("user", firtname);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }

    }
    //    @PostMapping(value = "/login",params = {"firstName","lastName"})
//    public ModelAndView login(@RequestParam("firstName")String firtname, @RequestParam("lastName")String lastname, Customer customer){
//        customerService.login(firtname,lastname);
//        ModelAndView modelAndView = new ModelAndView("hello");
//        modelAndView.addObject("user", lastname);
//        return modelAndView;
//    }
//    @GetMapping("/findall")
//    public ModelAndView  getall(){
//        memberService.findAllUsername();
//
//        ModelAndView modelAndView = new ModelAndView("hello");
//        return modelAndView;
//    }
}
