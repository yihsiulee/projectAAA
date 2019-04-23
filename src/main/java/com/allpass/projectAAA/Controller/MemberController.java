package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Do.Member;
import com.allpass.projectAAA.Security.MemberDetailsServiceImp;
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
    @Resource
    private  MemberDetailsServiceImp memberDetailsServiceImp;

    @RequestMapping(value = "/login")
    public String loginPage(){ return "memberLogin"; }


    @RequestMapping(value = "/register")
    public String registerPage(){
        return "memberRegister";
    }

    @PostMapping(value = "/register",params = {"name","idCardNumber","password1","password2","email","gender","birthday","phoneNumber","educational","study","special"})
    public ModelAndView register(@RequestParam("name")String name,
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
                                    Member member
                                   ) {
        boolean passwordVerification = MemberVerificationAndValidationUtil.MemberPasswordVerification(password1, password2);
        boolean idCardNumberVerification=memberService.verifyIdCardNumber(idCardNumber);

        if (idCardNumberVerification && passwordVerification && !password1.isEmpty()&& !password2.isEmpty()) {
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
            ModelAndView modelAndView = new ModelAndView("redirect:/member/login");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPage(){return "memberForgot";}

//    @PostMapping( value = "/forgotPassword")
@RequestMapping(value = "/memberInfo")
public ModelAndView InfoPage(){

    ModelAndView modelAndView = new ModelAndView("memberInfo");
    modelAndView.addObject("name",memberService.getMemberInfo("ddd"));
        return modelAndView;
    }






}
