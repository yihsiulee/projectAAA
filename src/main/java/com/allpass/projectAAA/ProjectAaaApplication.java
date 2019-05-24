package com.allpass.projectAAA;


import com.allpass.projectAAA.Contract.ERC20;
import com.allpass.projectAAA.Contract.myContract;
import com.allpass.projectAAA.JiaGer.PaperReviewer;
import com.allpass.projectAAA.JiaGer.TestSupplyToken;
import com.allpass.projectAAA.Web3jFunc.*;
import com.allpass.projectAAA.Web3jFunction.*;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.enclave.Constellation;
import org.web3j.quorum.enclave.protocol.EnclaveService;
import org.web3j.quorum.tx.QuorumTransactionManager;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;


import java.util.Collections;
import java.util.Scanner;


@Controller
//@EnableConfigurationProperties({ActivityImageFileProperties.class, ArticleUploadFileProperties.class})
@SpringBootApplication
public class ProjectAaaApplication {

    @RequestMapping("/aa")
    public String aa() {
        return "hello spring boost";
    }

    //	使用modle來定義參數的傳遞 傳到html
    @RequestMapping("/test") //網頁從這個地方進入這個方法（網址la）
    public String test(Model model) {
        model.addAttribute("loginName", "admin");
        model.addAttribute("loginId", "27");
        return "test";//放檔案的名字
    }

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "memberLogin";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/memberData")
    public String memberData() {
        return "memberData";
    }

    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    @RequestMapping("/result")
    public String result() {
        return "result";
    }

    //	@RequestMapping("/article")
//	public String article(){
//		return "article";
//	}
    @RequestMapping("/history")
    public String history() {
        return "history";
    }

    @RequestMapping("/QA")
    public String QA() {
        return "qa";
    }

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    public static void main(String[] args) throws Exception {
        //	DatabaseServer.startH2Server();
        //	SpringApplication.run(ProjectAaaApplication.class, args);

        Web3jService web3jService = new HttpService(RPC_URL);
        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());

        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
        Constellation constellation = new Constellation(service, web3j);
        ContractGasProvider provider = new StaticGasProvider(
                BigInteger.ZERO,
                BigInteger.valueOf(1000000000L)
        );

        //測試
//        CredentialAndLoadContract calc = new CredentialAndLoadContract();
        String privateKey1 = RandomKey.getRandomPrivateKey();
        String privateKey2 = RandomKey.getRandomPrivateKey();
        System.out.println(privateKey1+"隨機1");
        System.out.println(privateKey2+"隨機2");
        Credentials credentials1 = Credentials.create(privateKey1);
        Credentials credentials2 = Credentials.create(privateKey2);

        //可以放在創帳號的地方
        TransactionManager transactionManager1 = new QuorumTransactionManager(web3j,
                credentials1,
                "",
                Collections.emptyList(),
                constellation,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                50);

        TransactionManager transactionManager2 = new QuorumTransactionManager(web3j,
                credentials2,
                "",
                Collections.emptyList(),
                constellation,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH,
                50);

        System.out.println(credentials1.getAddress());//看credentials1的地址
        System.out.println(credentials2.getAddress());

        BigInteger Bint = new BigInteger("30000000000");
        DeployERC20 Derc20 = new DeployERC20();
        DeployCONTRACT Dcon = new DeployCONTRACT();
        ERC20Balance balance = new ERC20Balance();
        SmartCONTRACT sm = new SmartCONTRACT();

        Derc20.deploy(credentials1);
        System.out.println(Derc20.getERC20Address()+"deploy的ERC20地址");
        String DcontractAdress = Dcon.deployContract(web3j,credentials1);
        System.out.println(Dcon.deployContract(web3j,credentials1)+"contract地址");//contract deploy出去並回傳值

        balance.mybalance(web3j,transactionManager1,credentials1.getAddress());
        balance.mybalance(web3j,transactionManager2,credentials2.getAddress());

        sm.callIsApprove(web3j,transactionManager2,DcontractAdress);
        sm.callSendArticle(web3j,transactionManager1,DcontractAdress,DcontractAdress,Bint,credentials2.getAddress());
        sm.callIsRecievePost(web3j,transactionManager2,DcontractAdress);
        sm.callIsReturnReview(web3j,transactionManager2,DcontractAdress);
        sm.callIsGiveToken(web3j,transactionManager1,DcontractAdress,credentials2.getAddress());
        sm.callApproveToken(web3j,transactionManager1,DcontractAdress,credentials2.getAddress());
        sm.callTransferFromToken(web3j,transactionManager2,DcontractAdress);

        System.out.println(balance.mybalance(web3j,transactionManager1,credentials1.getAddress()));
        System.out.println(balance.mybalance(web3j,transactionManager2,credentials2.getAddress()));



//
//        String ERC20ADDRESS;
//        String ALLER_TOKEN = "0x964a979fd34737c55ab2a2e127c5159988c247c4";//一開始收到所有token的地址 活動發起人
//        String MY_CONTRACT_ADDRESS;
//        //發起人
//        String HOLDER_SOURCE = "/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-20.157478618Z--964a979fd34737c55ab2a2e127c5159988c247c4";
//        //審文人
//        String RECIEVER_SOURCE = "/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-40.551892911Z--2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95";
//
//
//        derc20.deploy();//佈ERC20合約
//        ERC20ADDRESS = derc20.getERC20Address();
//        System.out.println(ERC20ADDRESS + "這裏");//顯示ERC20合約的地址
//        String a = berc20.myGetErc20Address();
//        System.out.println(a + "顯示");
//        berc20.getBalance("12345", HOLDER_SOURCE, ALLER_TOKEN);
////		System.out.println(berc20.getBalance("12345","/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-20.157478618Z--964a979fd34737c55ab2a2e127c5159988c247c4",ALLERCTOKEN));
//        //發起人 佈一般的合約 佈的時候要丟入ERC20的地址
//        MY_CONTRACT_ADDRESS = dcon.getContractAddress("12345", HOLDER_SOURCE, ERC20ADDRESS);
//        System.out.println(MY_CONTRACT_ADDRESS + "佈上去的合約地址");
//
//        //accounts[1]= 0x964a979fd34737c55ab2a2e127c5159988c247c4 發起人 source:"/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-20.157478618Z--964a979fd34737c55ab2a2e127c5159988c247c4"
//        //accounts[2]= 0x2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95 審文人 source:"/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-40.551892911Z--2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95"
//
//        //審文人同意審文 審文人丟入資訊 丟入要load合約的地址
//        sm.callIsApprove(web3j,transactionManager1,"合約地址");
//        //發起人的資訊 這裡注意一下biginterger用法
//        sm.callSendArticle("12345", HOLDER_SOURCE, MY_CONTRACT_ADDRESS, MY_CONTRACT_ADDRESS, Bint, "0x2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95");
//        sm.callIsRecievePost("12345", RECIEVER_SOURCE, MY_CONTRACT_ADDRESS);
//        sm.callIsReturnReview("12345", RECIEVER_SOURCE, MY_CONTRACT_ADDRESS);
//        sm.callIsGiveToken("12345", HOLDER_SOURCE, MY_CONTRACT_ADDRESS, "0x2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95");
//        sm.callApproveToken("12345", HOLDER_SOURCE, MY_CONTRACT_ADDRESS, "0x2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95");
//        sm.callTransferFromToken("12345", RECIEVER_SOURCE, MY_CONTRACT_ADDRESS);
//        System.out.println("轉完後holder餘額：");
//        BigInteger bigInteger = berc20.getBalance("12345", HOLDER_SOURCE, ALLER_TOKEN);
//        System.out.println("reciever餘額：" + bigInteger);
//        bigInteger = berc20.getBalance("12345", RECIEVER_SOURCE, "0x2c1d2db6896ecac3df6c3e2a48e2e78326cd9c95");
//        System.out.println(bigInteger);

        /**
        TestSupplyToken token = TestSupplyToken.deploy(web3j, credentials1, provider).send();

        System.out.println(token.isValid());
        System.out.println(token.getContractAddress());
        System.out.println(token.owner().send());
        System.out.println(token.name().send());
        System.out.println(token.decimals().send());
        System.out.println(token.balanceOf(credentials1.getAddress()).send());

        PaperReviewer paper = PaperReviewer.deploy(web3j, transactionManager1, provider, token.getContractAddress()).send();

        PaperReviewer reviewerPaper = PaperReviewer.load(paper.getContractAddress(), web3j, transactionManager2, provider);

        TransactionReceipt receipt;
        receipt = reviewerPaper.isApprove().send();
        System.out.println(receipt.isStatusOK());

        paper.sendArticle(BigInteger.TEN, transactionManager2.getFromAddress()).send();

        receipt = reviewerPaper.isRecievePost().send();
        System.out.println(receipt.isStatusOK());
        receipt = reviewerPaper.isReturnReview().send();
        System.out.println(receipt.isStatusOK());

        Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger> tuple6 = paper.reviewers(transactionManager2.getFromAddress()).send();
        System.out.println("~~");
        System.out.println(tuple6.getValue1());
        System.out.println(tuple6.getValue2());
        System.out.println(tuple6.getValue3());
        System.out.println(tuple6.getValue4());
        System.out.println(tuple6.getValue5());
        System.out.println(tuple6.getValue6());
        System.out.println("~~");
        receipt = paper.isGiveToken(transactionManager2.getFromAddress()).send();
        System.out.println(receipt.isStatusOK());
        receipt = paper.approveToken(transactionManager2.getFromAddress()).send();
        System.out.println(receipt.isStatusOK());

        receipt = reviewerPaper.transferFromToken().send();
        System.out.println(receipt.isStatusOK());
        System.out.println(token.balanceOf(transactionManager2.getFromAddress()).send());
        **/
    }
}


