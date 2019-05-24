//package com.allpass.projectAAA.Web3jFunction;
//
//import com.allpass.projectAAA.Contract.myContract;
//import org.web3j.crypto.CipherException;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.JsonRpc2_0Web3j;
//import org.web3j.protocol.core.RemoteCall;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.http.HttpService;
//import org.web3j.tx.TransactionManager;
//import org.web3j.tx.gas.DefaultGasProvider;
//import org.web3j.utils.Async;
//
//import java.io.IOException;
//import java.math.BigInteger;
//
//public class SmartContract  {
//
//    //注意 inputAddress是 load合約的地址
//    //審文人同意審文call此 審文人
//    public void callIsApprove(Web3j web3j, TransactionManager manager, String inputAddress) throws Exception{
//        myContract contract = myContract.load(inputAddress, web3j, manager, new DefaultGasProvider());
//        contract.isApprove().send();
//    }
//
//    //活動發起人發送文章時call 把錢丟進智能合約綁著_contractAddress 丟多少 給誰(審文人)（要輸入地址）
//    //此時msg.sender是活動發起人
//    public void callSendArticle(String password, String source, String inputAddress, String _smartConAddress, BigInteger howManyTokens, String _to) throws Exception{
//        Credentials creSA = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creSA).sendArticle(_smartConAddress, howManyTokens, _to);
//        tr.send();
//        System.out.println(tr+"/callSendArticle");
//    }
//
//    //審文人接收到文章後call這個
//    public void callIsRecievePost(String password, String source, String inputAddress) throws Exception {
//        Credentials creIRP = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creIRP).isRecievePost();
//        tr.send();
//        System.out.println(tr+"/callIsRecievePost");
//    }
//
//    //審文人回傳review後call這個
//    public void callIsReturnReview(String password, String source, String inputAddress) throws Exception {
//        Credentials creIRR = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creIRR).isReturnReview();
//        tr.send();
//        System.out.println(tr+"/callIsReturnReview");
//    }
//
//    //活動發起人call 看完回覆之後call的 放入審文人的地址
//    public void callIsGiveToken(String password, String source, String inputAddress, String reviewerAddress) throws Exception {
//        Credentials creIGT = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creIGT).isGiveToken(reviewerAddress);
//        tr.send();
//        System.out.println(tr+"/callIsGiveToken");
//    }
//
//    //活動發起人call  放入審文人的地址
//    public void callApproveToken(String password, String source, String inputAddress, String reviewerAddress) throws Exception {
//        Credentials creAT = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creAT).approveToken(reviewerAddress);        tr.send();
//        tr.send();
//        System.out.println(tr+"/callApproveToken");
//
//    }
//
//    //審文人拿錢的時候call
//    public void callTransferFromToken(String password, String source, String inputAddress) throws Exception {
//        Credentials creTFT = cac.getUserCredentials(password, source);
//        RemoteCall<TransactionReceipt> tr = cac.getLoadUserContract(inputAddress, creTFT).transferFromToken();
//        tr.send();
//        System.out.println(tr+"/callTransferFromToken");
//    }
//
//
//    //先讀入合約
//    //再call方法
//}
