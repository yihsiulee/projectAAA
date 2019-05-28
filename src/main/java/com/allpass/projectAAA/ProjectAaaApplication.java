package com.allpass.projectAAA;


import com.allpass.projectAAA.Controller.ActivityController;
import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Service.ActivityImageFileService;
import com.allpass.projectAAA.Service.ActivityService;
import com.allpass.projectAAA.Service.ArticleFileService;
import com.allpass.projectAAA.Service.MemberService;
import com.allpass.projectAAA.Web3jFunc.DeployERC20;
import com.allpass.projectAAA.Web3jFunc.ERC20Balance;
import com.allpass.projectAAA.util.Study;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.enclave.Constellation;
import org.web3j.quorum.enclave.protocol.EnclaveService;
import org.web3j.quorum.tx.QuorumTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
//@EnableConfigurationProperties({ActivityImageFileProperties.class, ArticleUploadFileProperties.class})
@SpringBootApplication
public class ProjectAaaApplication  {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ActivityImageFileService activityImageFileService;
    @Autowired
    private ActivityService activityService;

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

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
    public String home(
            Model model,
            Authentication auth
    ) throws Exception {
        if(auth!=null){
            Member member=memberService.getMemberInfo(auth.getName());

            BigInteger divide=new BigInteger("1000000000000000000");
            ERC20Balance erc20Balance=new ERC20Balance();
            Web3jService web3jService = new HttpService(RPC_URL);
            Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
            EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
            Constellation constellation = new Constellation(service, web3j);
            ContractGasProvider provider = new StaticGasProvider(
                    BigInteger.ZERO,
                    BigInteger.valueOf(1000000000L)
            );
            Credentials credentials = Credentials.create(member.getBlockchainPrivateKey());
            TransactionManager transactionManager = new QuorumTransactionManager(web3j,
                    credentials,
                    "",
                    Collections.emptyList(),
                    constellation,
                    TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                    50);
            double memberBalance=erc20Balance.mybalance(web3j,transactionManager,credentials.getAddress()).divide(divide).doubleValue();
            model.addAttribute("isAuth",true);
            model.addAttribute("tokenBalance",memberBalance);
            model.addAttribute("memberName",member.getName());
        }else{
            model.addAttribute("isAuth",false);
            model.addAttribute("tokenBalance","");
            model.addAttribute("memberName","");
        }


        List<Activity> activityList=new ArrayList<>();
        activityService.getActivityList().stream().filter(i->i.getLimitedParticipants()!=0 || i.getArticleNumber()!=0).forEach(a->activityList.add(a));
        List<String> img = new ArrayList<>();
        List<String> activity_Image = new ArrayList<>();
        for(Activity image:activityList) {
            img.add(image.getActivityImg());
        }
        activityImageFileService.loadActivityImage(img).forEach(
                path -> activity_Image.add(MvcUriComponentsBuilder.fromMethodName(ActivityController.class,
                        "serveImageFile", path).build().toString()));
        activity_Image.forEach(item->System.out.println(item));
        System.out.println(activityList.size());
        for(int i=0;i<activityList.size();i++){
            activityList.get(i).setActivityImg(activity_Image.get(i));
        }
        model.addAttribute("activityLists",activityList);
        return "index";
    }

    //	@RequestMapping("/login")
//	public String login(){
//		return "memberLogin";
//	}
//	@RequestMapping("/register")
//	public String register(){
//		return "register";
//	}
//	@RequestMapping("/memberData")
//	public String memberData(){
//		return "memberData";
//	}
//	@RequestMapping("/post")
//	public String post(){
//		return "post";
//	}
//	@RequestMapping("/result")
//	public String result(){
//		return "result";
//	}
//	@RequestMapping("/article")
//	public String article(){
//		return "article";
//	}
//	@RequestMapping("/history")
//	public String history(){
//		return "history";
//	}
    @RequestMapping("/qa")
    public String qa(
            Authentication auth,
            Model model
    ) throws Exception {

        if(auth!=null){
            Member member=memberService.getMemberInfo(auth.getName());

            BigInteger divide=new BigInteger("1000000000000000000");
            ERC20Balance erc20Balance=new ERC20Balance();
            Web3jService web3jService = new HttpService(RPC_URL);
            Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
            EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
            Constellation constellation = new Constellation(service, web3j);
            ContractGasProvider provider = new StaticGasProvider(
                    BigInteger.ZERO,
                    BigInteger.valueOf(1000000000L)
            );
            Credentials credentials = Credentials.create(member.getBlockchainPrivateKey());
            TransactionManager transactionManager = new QuorumTransactionManager(web3j,
                    credentials,
                    "",
                    Collections.emptyList(),
                    constellation,
                    TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                    50);
            double memberBalance=erc20Balance.mybalance(web3j,transactionManager,credentials.getAddress()).divide(divide).doubleValue();
            model.addAttribute("isAuth",true);
            model.addAttribute("tokenBalance",memberBalance);
            model.addAttribute("memberName",member.getName());
        }else{
            model.addAttribute("isAuth",false);
            model.addAttribute("tokenBalance","");
            model.addAttribute("memberName","");
        }
        return "qa";
    }
    //圖片連結
    @GetMapping("/activityImage/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImageFile(@PathVariable String filename) {
        System.out.println(filename);
        org.springframework.core.io.Resource file = activityImageFileService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



    public static void main(String[] args) throws Exception {
//		DeployERC20 deployERC20=new DeployERC20();
//		Credentials credentials=Credentials.create("e6177636c6982205afc63e7eaed762821dcee0b1c8d0bec1cc2f9019fe7efda5");
//		deployERC20.deploy(credentials);
//		System.out.println(deployERC20.getERC20Address());
        DatabaseServer.startH2Server();
        SpringApplication.run(ProjectAaaApplication.class, args);
    }


}


