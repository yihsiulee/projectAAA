package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Model.Member_Role;
import com.allpass.projectAAA.Security.MemberDetailsServiceImp;
import com.allpass.projectAAA.Service.MemberService;
import com.allpass.projectAAA.util.MemberVerificationAndValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    //直接輸入網址
    @RequestMapping(value = "/login")
    public String loginPage() {
        return "memberLogin";
    }


    @RequestMapping(value = "/register")
    public String registerPage() {
        return "memberRegister";
    }

    //有傳東西出去
    @PostMapping(value = "/register", params = {"name", "idCardNumber", "password1", "password2", "email", "gender", "birthday", "phoneNumber", "educational", "study", "special"})
    public ModelAndView saveMember(
            @RequestParam("name") String name,
            @RequestParam("idCardNumber") String idCardNumber,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("gender") Integer gender,
            @RequestParam("birthday") String birthday,
            @RequestParam("educational") Integer education,
            @RequestParam("study") Integer study,
            @RequestParam("special") String special
    ) {
        boolean passwordVerification = MemberVerificationAndValidationUtil.MemberPasswordVerification(password1, password2);
        boolean idCardNumberVerification = memberService.verifyIdCardNumber(idCardNumber);

        if (idCardNumberVerification && passwordVerification) {
            Member member = new Member();
//            member.setId(MemberIdRandomUtil.randomMemberNumber());
            member.setName(name);
            member.setIdCardNumber(idCardNumber.toUpperCase());
            member.setPassword(passwordEncoder.encode(password1));
            member.setRoles(Arrays.asList(new Member_Role("ROLE_USER")));
            member.setEmail(email);
            member.setPhoneNumber(phoneNumber);
            member.setGender(gender);
            member.setBirthday(birthday);
            member.setEducation(education);
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
@RequestMapping(value = "/Info")
public ModelAndView InfoPage(Authentication authentication){

    ModelAndView modelAndView = new ModelAndView("memberInfo");
    modelAndView.addObject("name",memberService.getMemberInfo(authentication.getName()).getName());

    return modelAndView;
    }

}