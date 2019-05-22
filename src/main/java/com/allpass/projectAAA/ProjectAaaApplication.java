package com.allpass.projectAAA;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
//@EnableConfigurationProperties({ActivityImageFileProperties.class, ArticleUploadFileProperties.class})
@SpringBootApplication
public class ProjectAaaApplication {

    @RequestMapping("/aa")
	public String aa(){
		return "hello spring boost";
	}

	//	使用modle來定義參數的傳遞 傳到html
	@RequestMapping("/test") //網頁從這個地方進入這個方法（網址）
	public String test(Model model) {
		model.addAttribute("loginName", "admin");
		model.addAttribute("loginId", "27");
		return "test";//放檔案的名字
	}

	@RequestMapping("/")
	public String home(){ return "index"; }
	@RequestMapping("/login")
	public String login(){
		return "memberLogin";
	}
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	@RequestMapping("/memberData")
	public String memberData(){
		return "memberData";
	}
	@RequestMapping("/post")
	public String post(){
		return "post";
	}
	@RequestMapping("/result")
	public String result(){
		return "result";
	}
//	@RequestMapping("/article")
//	public String article(){
//		return "article";
//	}
	@RequestMapping("/history")
	public String history(){
		return "history";
	}
	@RequestMapping("/QA")
	public String QA(){return "qa";}


	public static void main(String[] args) {
		DatabaseServer.startH2Server();
		SpringApplication.run(ProjectAaaApplication.class, args);
	}
}


