package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.*;
import com.allpass.projectAAA.Security.MemberDetailsServiceImp;
import com.allpass.projectAAA.Service.ActivityService;
import com.allpass.projectAAA.Service.ArticleReviewService;
import com.allpass.projectAAA.Service.ArticleService;
import com.allpass.projectAAA.Service.MemberService;
import com.allpass.projectAAA.Web3jFunc.DeployCONTRACT;
import com.allpass.projectAAA.Web3jFunc.ERC20Balance;
import com.allpass.projectAAA.Web3jFunc.RandomKey;
import com.allpass.projectAAA.Web3jFunc.SmartCONTRACT;
import com.allpass.projectAAA.util.MemberVerificationAndValidationUtil;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Resource
    private MemberService memberService;
    @Resource
    private MemberDetailsServiceImp memberDetailsServiceImp;
    @Resource
    private ActivityService activityService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleReviewService articleReviewService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    //直接輸入網址
    @RequestMapping(value = "/login")
    public String loginPage(
            Authentication auth,
            Model model
    ) throws Exception {
        if(auth!=null) {
            Member member = memberService.getMemberInfo(auth.getName());

            BigInteger divide = new BigInteger("1000000000000000000");
            ERC20Balance erc20Balance = new ERC20Balance();
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
            double memberBalance = erc20Balance.mybalance(web3j, transactionManager, credentials.getAddress()).divide(divide).doubleValue();
            model.addAttribute("isAuth", true);
            model.addAttribute("tokenBalance", memberBalance);
            model.addAttribute("memberName", member.getName());
        }else{
                model.addAttribute("isAuth", false);
                model.addAttribute("tokenBalance", "");
                model.addAttribute("memberName", "");
            }

        return "memberLogin";
    }


    @RequestMapping(value = "/register")
    public String registerPage(
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
            @RequestParam("study") String study,
            @RequestParam("special") String special
    ) throws Exception {
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
            member.setBlockchainPrivateKey(RandomKey.getRandomPrivateKey());
            memberService.save(member);
            ModelAndView modelAndView = new ModelAndView("redirect:/member/login");


            SmartCONTRACT smartCONTRACT=new SmartCONTRACT();
            DeployCONTRACT deployCONTRACT=new DeployCONTRACT();
            BigInteger initValue=new BigInteger("1000"+"000000000000000000");
            Web3jService web3jService = new HttpService(RPC_URL);
            Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
            EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
            Constellation constellation = new Constellation(service, web3j);
            ContractGasProvider provider = new StaticGasProvider(
                    BigInteger.ZERO,
                    BigInteger.valueOf(1000000000L)
            );
            Credentials admin=Credentials.create(memberService.getMemberInfo("ACTIVITYHOLDER").getBlockchainPrivateKey());
            TransactionManager adminTransactionManager = new QuorumTransactionManager(web3j,
                    admin,
                    "",
                    Collections.emptyList(),
                    constellation,
                    TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                    50);
            Credentials assignedMember=Credentials.create(member.getBlockchainPrivateKey());
            TransactionManager assignedMemberTransactionManager = new QuorumTransactionManager(web3j,
                    assignedMember,
                    "",
                    Collections.emptyList(),
                    constellation,
                    TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                    50);
            String articleReviewAddress=deployCONTRACT.deployContract(web3j,admin);
            smartCONTRACT.callIsApprove(web3j,assignedMemberTransactionManager,articleReviewAddress);
            smartCONTRACT.callSendArticle(web3j,adminTransactionManager,articleReviewAddress,articleReviewAddress,initValue,assignedMember.getAddress());
            smartCONTRACT.callIsRecievePost(web3j,assignedMemberTransactionManager,articleReviewAddress);
            smartCONTRACT.callIsReturnReview(web3j,assignedMemberTransactionManager,articleReviewAddress);
            smartCONTRACT.callIsGiveToken(web3j,adminTransactionManager,articleReviewAddress,assignedMember.getAddress());
            smartCONTRACT.callApproveToken(web3j,adminTransactionManager,articleReviewAddress,assignedMember.getAddress());
            smartCONTRACT.callTransferFromToken(web3j,assignedMemberTransactionManager,articleReviewAddress);

            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            return modelAndView;
        }

    }

    //    @RequestMapping(value = "/forgotPassword")
//    public String forgotPage() {
//        return "memberForgot";
//    }
//
//    @PostMapping( value = "/forgotPassword")
    @RequestMapping(value = "/Info")
    public String InfoPage(
            Authentication authentication,
            Model model
    ) throws Exception {

        Member member=memberService.getMemberInfo(authentication.getName());
        List<Article> articles=articleService.getArticleByAuthor(member);
        List<Article> articleList=new ArrayList<>();
        for(Article article:articles){
            String[] date=article.getActivity().getActivityTime().split("-+\\s");
            System.out.println(date[1]);
            String today=dateFormat.format(new Date());
            if(today.compareTo(date[1])>0||article.getArticleState().equals("reviewFinish")){
                articleList.add(article);
            }
        }

        List<ArticleReview>articleReviews=articleReviewService.getArticleReviewListByMember(member);
        List<ArticleReview>articleReviewList=new ArrayList<>();
        for(ArticleReview articleReview:articleReviews){
            String[] date=articleReview.getArticle().getActivity().getActivityTime().split("-+\\s");
            System.out.println(date[1]);
            String today=dateFormat.format(new Date());
            if(today.compareTo(date[1])>0||articleReview.getReviewComplete()){
                articleReviewList.add(articleReview);
            }
        }

        List<Activity>activities=activityService.getActivityInfoByActivityFounder(member);
        List<Activity>activityList=new ArrayList<>();
        for(Activity activity:activities){
            String[] date=activity.getActivityTime().split("-+\\s");
            String today=dateFormat.format(new Date());
            if(today.compareTo(date[1])>0){
                activityList.add(activity);
                System.out.println(activity.getActivityName());
            }
        }

        //區塊鏈指令
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
        System.out.println(memberBalance);

        model.addAttribute("tokenBalance",memberBalance);
        model.addAttribute("memberName",member.getName());
        model.addAttribute("name",member.getName());
        model.addAttribute("memberBalance", memberBalance);
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleReviewList",articleReviewList);
        model.addAttribute("activityList",activityList);
        return "memberInfo";
    }

}