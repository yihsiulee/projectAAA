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

    @RequestMapping(value = "/login")
    public String loginPage(){ return "login";}

    @PostMapping(value="/memberVerification")


    @RequestMapping(value = "/regist")
    public String registPage(){
        return "createaccount";
    }

    @PostMapping(value = "/addMember",params = {"name","idCardNumber","password1","password2","email","gender","birthday","phoneNumber","educational","study","special"})
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
        if (passwordVerfication && !password1.isEmpty()&& !password2.isEmpty()) {
            member.setId(MemberIdRandomUtil.randomMemberNumber());
            member.setName(name);
            member.setIdCardNumber(idCardNumber);
            member.setPassword(password1);
            member.setEmail(email);
            member.setPhoneNumber(phoneNumber);
            member.setGender(gender);
            member.setBirthday(birthday);
            member.setEducational(educational);
            member.setStudy(study);
            member.setSpecial(special);
            memberService.addMember(member);
            ModelAndView modelAndView = new ModelAndView("login");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPage(){return "forgetpassword";}

//    @PostMapping( value = "/sendPassword")







}
