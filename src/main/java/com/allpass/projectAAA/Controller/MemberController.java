package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Model.Role;
import com.allpass.projectAAA.Security.MemberDetailsServiceImp;
import com.allpass.projectAAA.Service.MemberService;
import com.allpass.projectAAA.util.MemberIdRandomUtil;
import com.allpass.projectAAA.util.MemberVerificationAndValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @Resource
    private MemberDetailsServiceImp memberDetailsServiceImp;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "memberLogin";
    }


    @RequestMapping(value = "/register")
    public String registerPage() {
        return "memberRegister";
    }

    @PostMapping(value = "/register", params = {"name", "idCardNumber", "password1", "password2", "email", "gender", "birthday", "phoneNumber", "educational", "study", "special"})
    public ModelAndView register(@RequestParam("name") String name,
                                 @RequestParam("idCardNumber") String idCardNumber,
                                 @RequestParam("password1") String password1,
                                 @RequestParam("password2") String password2,
                                 @RequestParam("email") String email,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("gender") Integer gender,
                                 @RequestParam("birthday") String birthday,
                                 @RequestParam("educational") Integer educational,
                                 @RequestParam("study") Integer study,
                                 @RequestParam("special") String special
    ) {
        boolean passwordVerification = MemberVerificationAndValidationUtil.MemberPasswordVerification(password1, password2);
        boolean idCardNumberVerification = memberService.verifyIdCardNumber(idCardNumber);

        if (idCardNumberVerification && passwordVerification) {
            Member member = new Member();
            member.setId(MemberIdRandomUtil.randomMemberNumber());
            member.setName(name);
            member.setIdCardNumber(idCardNumber);
            member.setPassword(passwordEncoder.encode(password1));
            member.setRoles(Arrays.asList(new Role("ROLE_USER")));
            member.setEmail(email);
            member.setPhoneNumber(phoneNumber);
            member.setGender(gender);
            member.setBirthday(birthday);
            member.setEducational(educational);
            member.setStudy(study);
            member.setSpecial(special);
            memberService.save(member);
            ModelAndView modelAndView = new ModelAndView("redirect:/member/login");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPage() {
        return "memberForgot";
    }

//    @PostMapping( value = "/forgotPassword")
//@RequestMapping(value = "/memberInfo")
//public ModelAndView InfoPage(){
//
//    ModelAndView modelAndView = new ModelAndView("memberInfo");
//    modelAndView.addObject("name",memberService.getMemberInfo(44444455555555555));
//        return modelAndView;
//    }

}