package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Service.*;
import com.allpass.projectAAA.Web3jFunc.ERC20Balance;
import okhttp3.OkHttpClient;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleFileService articleFileService;
    @Resource
    private MemberService memberService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ArticleReviewService articleReviewService;

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    private  static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @RequestMapping(value = "")
    private String articleFuctionPage(
            Model model,
            Authentication auth
    ) throws Exception {

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
        model.addAttribute("tokenBalance",memberBalance);
        model.addAttribute("memberName",member.getName());
        return "article";
    }




    //文章上傳頁面
    @RequestMapping(value = "/post")
    public String UploadArticlePage(
            Model model,
            Authentication authentication
    ) throws Exception {
        Member member=memberService.getMemberInfo(authentication.getName());
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
        model.addAttribute("tokenBalance",memberBalance);
        model.addAttribute("memberName",member.getName());



        if(memberService.getMemberInfo(authentication.getName()).getActivityParticipant_Author().isEmpty()){
            return "redirect:/article";
        }
        Set<Activity> activityList=memberService.getMemberInfo(authentication.getName()).getActivityParticipant_Author();
        activityList.forEach(i->System.out.println(i.getActivityName()));
        model.addAttribute("activityList",activityList);
        return "articlePost";
    }

    //文章上傳
    @PostMapping(value = "/post")
    public String UploadArticle(
            @RequestParam("activityId")Long activity_Id,
            @RequestParam("articleName")String articleName,
            @RequestParam("textNumber")Integer textNumber,
            @RequestParam("formulaNumber")Integer formulaNumber,
            @RequestParam("uploadFile") MultipartFile uploadFile,
            @RequestParam("articleValue")Double articleValue,
            Authentication auth
    ) {
        Article article=new Article();
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        article.setPostTime(date.format(currentDate));
        article.setDeadline(date.format(currentDate.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant())));
        article.setArticleName(articleName);
        article.setActivity(activityService.getActivityById(activity_Id));
        article.setFormulaNumber(formulaNumber);
        article.setTextNumber(textNumber);
        article.setArticleValue(articleValue);
        article.setAuthor(memberService.getMemberInfo(auth.getName()));
        if(!uploadFile.isEmpty()){
            article.setFileName(uploadFile.getOriginalFilename());
            //檔案上傳
            articleFileService.store(uploadFile);
        }
        articleService.save(article);
        activityService.getActivityById(activity_Id).getActivityParticipants_Author().remove(memberService.getMemberInfo(auth.getName()));
        return "redirect:/article";
    }

//    //文章連結
//    @GetMapping("/articleFile/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename) {
//        System.out.println(filename);
//        org.springframework.core.io.Resource file = articleFileService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }


}
