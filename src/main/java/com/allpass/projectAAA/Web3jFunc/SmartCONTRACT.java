package com.allpass.projectAAA.Web3jFunc;

import com.allpass.projectAAA.Contract.myContract;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class SmartCONTRACT {


    public myContract LoadContract(Web3j web3j, TransactionManager manager, String inputAddress){
        return myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
    }

    //注意 inputAddress是 load合約的地址
    //審文人同意審文call此 審文人
    public void callIsApprove(Web3j web3j, TransactionManager manager, String inputAddress) throws Exception{
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.isApprove().send();
    }

    //活動發起人發送文章時call 把錢丟進智能合約綁著_contractAddress 丟多少 給誰(審文人)（要輸入地址）
    //此時msg.sender是活動發起人
    public void callSendArticle(Web3j web3j, TransactionManager manager, String inputAddress, String _smartConAddress, BigInteger howManyTokens, String _to) throws Exception{
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.sendArticle(_smartConAddress, howManyTokens, _to).send();
    }

    //審文人接收到文章後call這個
    public void callIsRecievePost(Web3j web3j,  TransactionManager manager, String inputAddress) throws Exception {
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.isRecievePost().send();
    }

    //審文人回傳review後call這個
    public void callIsReturnReview(Web3j web3j,  TransactionManager manager, String inputAddress) throws Exception {
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.isReturnReview().send();
    }

    //活動發起人call 看完回覆之後call的 放入審文人的地址
    public void callIsGiveToken(Web3j web3j,  TransactionManager manager, String inputAddress, String reviewerAddress) throws Exception {
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.isGiveToken(reviewerAddress).send();
    }

    //活動發起人call  放入審文人的地址
    public void callApproveToken(Web3j web3j,  TransactionManager manager, String inputAddress, String reviewerAddress) throws Exception {
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.approveToken(reviewerAddress).send();
    }

    //審文人拿錢的時候call
    public void callTransferFromToken(Web3j web3j,  TransactionManager manager, String inputAddress) throws Exception {
        myContract contract = myContract.load(inputAddress, web3j, manager, BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        contract.transferFromToken().send();
    }






}
