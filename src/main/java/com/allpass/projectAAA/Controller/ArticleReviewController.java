package com.allpass.projectAAA.Controller;

import com.allpass.projectAAA.Mail.EmailService;
import com.allpass.projectAAA.Mail.Mail;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Service.*;
import com.allpass.projectAAA.Web3jFunc.DeployCONTRACT;
import com.allpass.projectAAA.Web3jFunc.SmartCONTRACT;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/articleReview")
public class ArticleReviewController {

    @Resource
    private ArticleReviewService articleReviewService;
    @Resource
    private ArticleService articleService;
    @Resource
    private MemberService memberService;
    @Resource
    private ArticleFileService articleFileService;
    @Resource
    private ActivityService activityService;
    @Resource
    private EmailService emailService;

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;


    private  static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @RequestMapping(value = "list")
    private String articleReviewListPage(
            Authentication auth,
            Model model
    ){
        List<ArticleReview> articleReviewList=articleReviewService.getArticleReviewListByMember(memberService.getMemberInfo(auth.getName()));
        articleReviewList.forEach(i->System.out.println(i.getArticle().getArticleName()));
        model.addAttribute("articleReviewList",articleReviewList);
        return "articleReviewList";
    }
    @RequestMapping("authorReviewList")
    private String  authorReviewListPage(
            Authentication auth,
            Model model
    ){
       List<Article> articleList=articleService.getArticleByAuthor(memberService.getMemberInfo(auth.getName()));

        if(articleList==null){
            return "redirect:/article";
        } else {
            List<Article> articles;
            articles=articleService.getArticleByAuthor(memberService.getMemberInfo(auth.getName()));
            model.addAttribute("articleList",articles);
            return "articleAuthorReviewList";
        }



    }
    @GetMapping("authorReview")
    private String authorReviewPage(
            @RequestParam("articleId")Long articleId,
            Model model
    ){
        Article article=articleService.getArticleById(articleId);
        String articleURL=articleFileService.loadArticle(article.getUploadFile());
        String articleFile=MvcUriComponentsBuilder.fromMethodName(ArticleReviewController.class,
                "serveArticleFile", articleURL).build().toString();
        article.setUploadFile(articleFile);
        model.addAttribute("article",article);
        return "articleAuthorReview";
    }



    @GetMapping("/post")
    private String saveArticleReviewPage(
            @RequestParam("articleReviewId")Long articleReviewId,
            Model model
    ){
        ArticleReview articleReview=articleReviewService.getArticleReviewById(articleReviewId);
        String articleFile;
        String articleURL;
        articleURL=articleFileService.loadArticle(articleReview.getArticle().getFileName());
        articleFile=MvcUriComponentsBuilder.fromMethodName(ArticleReviewController.class,
                "serveArticleFile", articleURL).build().toString();
        articleReview.getArticle().setUploadFile(articleFile);

        model.addAttribute("articleReview",articleReview);
        return "articleReviewPost";
    }
    @GetMapping("/saveDraft")
    private String saveDraft(
            @RequestParam("articleReviewId")String articleReviewId,
            @RequestParam("reviewText")String reviewText
    ){
        ArticleReview articleReview=articleReviewService.getArticleReviewById(Long.parseLong(articleReviewId));
        articleReview.setReviewText(reviewText);
        System.out.println(reviewText);
        articleReview.setReviewTime(date.format(new Date()));
        articleReviewService.save(articleReview);
        return "redirect:/article";
    }

    @PostMapping("/post")
    private String saveArticleReview(
            @RequestParam("articleReviewId")Long articleReviewId,
            @RequestParam("reviewText")String reviewText
    ) throws Exception {
        ArticleReview articleReview=articleReviewService.getArticleReviewById(articleReviewId);
        articleReview.setReviewText(reviewText);
        articleReview.setReviewTime(date.format(new Date()));
        articleReview.setReviewComplete(true);

        SmartCONTRACT smartCONTRACT=new SmartCONTRACT();
        Web3jService web3jService = new HttpService(RPC_URL);
        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
        Constellation constellation = new Constellation(service, web3j);
        ContractGasProvider provider = new StaticGasProvider(
                BigInteger.ZERO,
                BigInteger.valueOf(1000000000L)
        );
        Credentials assignedMember=Credentials.create(articleReview.getMember().getBlockchainPrivateKey());
        TransactionManager assignedMemberTransactionManager = new QuorumTransactionManager(web3j,
                assignedMember,
                "",
                Collections.emptyList(),
                constellation,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                50);
        smartCONTRACT.callIsReturnReview(web3j,assignedMemberTransactionManager,articleReview.getArticleReviewAddress());

        articleReviewService.save(articleReview);
        return "redirect:/article";
    }
    @RequestMapping("/reviewCheck")
    private String reviewCheck(
            Authentication auth,
            Model model
    ){
        List<ArticleReview> articleReviews=new ArrayList<>();
        List<ArticleReview> articleReviewList=articleReviewService.getArticleReviewListByMember(memberService.getMemberInfo(auth.getName()));
        for (ArticleReview articleReview:articleReviewList){
            if(articleReview.getAcceptTask()==false)
                articleReviews.add(articleReview);
        }
        model.addAttribute("articleReview",articleReviews);

        return "activityCheckTable";
    }
    @GetMapping("/acceptTask")
    private String acceptTask(
            @RequestParam("articleReviewId")Long articleReviewId
    ) throws Exception {
        ArticleReview articleReview= articleReviewService.getArticleReviewById(articleReviewId);
        String articleReviewAddress=articleReview.getArticleReviewAddress();
        Set<Member> activityParticipants_Reviewer=activityService.getActivityById(articleReview.getArticle().getActivity().getId()).getActivityParticipants_Reviewer();
        articleReview.setAcceptTask(true);
        Article article=articleReview.getArticle();
//        Article article=articleService.getArticleByActivity(articleReview.getArticle().getActivity()).stream().findFirst().get();

        if(article.getArticleReviewAssignNumber()>0){
            article.setArticleReviewAssignNumber(article.getArticleReviewAssignNumber()-1);
            articleService.update(article);
        }
        boolean last= true;
        boolean isArticleReviewAccept=false;
        for(Member member:activityParticipants_Reviewer){
              ArticleReview acceptArticleReview= articleReviewService.getArticleReviewByAcceptMember(member,articleReview);
            if(acceptArticleReview.getAcceptTask() && last){
                isArticleReviewAccept=true;
                last=true;
            }else {
                last=false;
                isArticleReviewAccept=false;
            }
        }
        if(isArticleReviewAccept==true){

            article.setArticleReviewAssignNumber(activityParticipants_Reviewer.size());
            article.setArticleState("reviewing");
            articleService.save(article);
        }

        SmartCONTRACT smartCONTRACT=new SmartCONTRACT();
        Web3jService web3jService = new HttpService(RPC_URL);
        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
        Constellation constellation = new Constellation(service, web3j);
        ContractGasProvider provider = new StaticGasProvider(
                BigInteger.ZERO,
                BigInteger.valueOf(1000000000L)
        );
        Credentials activityOrganizer=Credentials.create(articleReview.getArticle().getActivity().getActivityOrganizer().getBlockchainPrivateKey());
        TransactionManager organizerTransactionManager = new QuorumTransactionManager(web3j,
                activityOrganizer,
                "",
                Collections.emptyList(),
                constellation,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                50);
        Credentials assignedMember=Credentials.create(articleReview.getMember().getBlockchainPrivateKey());
        TransactionManager assignedMemberTransactionManager = new QuorumTransactionManager(web3j,
                assignedMember,
                "",
                Collections.emptyList(),
                constellation,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                50);


        smartCONTRACT.callIsApprove(web3j,assignedMemberTransactionManager,articleReviewAddress);
        BigInteger articleValue=new BigInteger(articleReview.getArticle().getArticleValue()+"000000000000000000");
        smartCONTRACT.callSendArticle(web3j,organizerTransactionManager,articleReviewAddress,articleReviewAddress,articleValue,assignedMember.getAddress());
        smartCONTRACT.callIsRecievePost(web3j,assignedMemberTransactionManager,articleReviewAddress);

        articleReviewService.update(articleReview);

        //寄信通知活動主辦人
        Mail mailAcceptConfirm = new Mail();
        mailAcceptConfirm.setFrom("no-reply@memorynotfound.com");
        mailAcceptConfirm.setTo(articleReview.getArticle().getActivity().getActivityOrganizer().getEmail());
        mailAcceptConfirm.setSubject("<PaperReview!>您的指派被確認了");
        Map<String, Object> model = new HashMap<>();
        model.put("name", articleReview.getArticle().getActivity().getActivityOrganizer().getName());
        model.put("location", "Taipei");
        model.put("signature", "PaperReview");
        model.put("activityName",articleReview.getArticle().getActivity().getActivityName());
        model.put("articleName",articleReview.getArticle().getArticleName());
        model.put("memberName",articleReview.getMember().getName());
        mailAcceptConfirm.setModel(model);
        emailService.sendSimpleMessageConfirm(mailAcceptConfirm);

        return "redirect:/articleReview/list";
    }

    @GetMapping("/refuseTask")
    private String refuseTask(
            @RequestParam("articleReviewId")Long articleReviewId
    ) throws IOException, MessagingException {
        ArticleReview articleReview=articleReviewService.getArticleReviewById(articleReviewId);
        Article article=articleService.getArticleByActivity(articleReview.getArticle().getActivity()).stream().findFirst().get();
        article.setArticleReviewAssignNumber(article.getArticleReviewAssignNumber()+1);
//        articleReview.setAcceptTask(false);
//        articleReviewService.save(articleReview);
        articleService.update(article);
        articleReviewService.delete(articleReview);

        //寄信通知活動主辦人
        Mail mailRefuse = new Mail();
        mailRefuse.setFrom("no-reply@memorynotfound.com");
        mailRefuse.setTo(articleReview.getArticle().getActivity().getActivityOrganizer().getEmail());
        mailRefuse.setSubject("<PaperReview!>您的指派被拒絕了");
        Map<String, Object> model = new HashMap<>();
        model.put("name", articleReview.getArticle().getActivity().getActivityOrganizer().getName());
        model.put("location", "Taipei");
        model.put("signature", "PaperReview");
        model.put("activityName",articleReview.getArticle().getActivity().getActivityName());
        model.put("articleName",articleReview.getArticle().getArticleName());
        model.put("memberName",articleReview.getMember().getName());
        mailRefuse.setModel(model);
        emailService.sendSimpleMessageRefused(mailRefuse);
        return "redirect:/activity";
    }


    //文章連結
    @GetMapping("/articleUpload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> serveArticleFile(@PathVariable String filename) throws UnsupportedEncodingException {
        System.out.println(filename);
        org.springframework.core.io.Resource file = articleFileService.loadAsResource(filename);
        String fileName=URLEncoder.encode(file.getFilename(),"UTF-16");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body(file);
    }

}
