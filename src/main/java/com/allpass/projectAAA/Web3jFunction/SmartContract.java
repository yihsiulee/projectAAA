package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.myContract;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.io.IOException;
import java.math.BigInteger;

public class SmartContract  {

    Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth

    CredentialAndLoadContract cac = new CredentialAndLoadContract();


    //審文人同意審文call此
    public void callIsApprove(String password, String source, String inputAddress) throws Exception{
        Credentials creIA = cac.getUserCredentials(password, source);//取得憑證
        cac.getLoadUserContract(inputAddress, creIA).isApprove();
    }

    //活動發起人發送文章時call 把錢丟進智能合約綁著_contractAddress 丟多少 給誰(審文人)（要輸入地址）
    //此時msg.sender是活動發起人
    public void callSendArticle(String password, String source, String inputAddress, String _smartConAddress, BigInteger howManyTokens, String _to) throws Exception{
        Credentials creSA = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creSA).sendArticle(_smartConAddress, howManyTokens, _to);
    }

    //審文人接收到文章後call這個
    public void callIsRecievePost(String password, String source, String inputAddress) throws Exception {
        Credentials creIRP = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creIRP).isRecievePost();
    }

    //審文人回傳review後call這個
    public void callIsReturnReview(String password, String source, String inputAddress) throws Exception {
        Credentials creIRR = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creIRR).isReturnReview();
    }

    //活動發起人call 看完回覆之後call的 放入審文人的地址
    public void callIsGiveToken(String password, String source, String inputAddress, String reviewerAddress) throws Exception {
        Credentials creIGT = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creIGT).isGiveToken(reviewerAddress);

    }

    //活動發起人call  放入審文人的地址
    public void callApproveToken(String password, String source, String inputAddress, String reviewerAddress) throws Exception {
        Credentials creAT = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creAT).approveToken(reviewerAddress);

    }

    //審文人拿錢的時候call
    public void callTransferFromToken(String password, String source, String inputAddress) throws Exception {
        Credentials creTFT = cac.getUserCredentials(password, source);
        cac.getLoadUserContract(inputAddress, creTFT).transferFromToken();

    }


    //先讀入合約
    //再call方法
}
