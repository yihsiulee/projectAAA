package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.myContract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class DeployContract {

    String contractAddress ;

    public String getContractAddress(){
        return contractAddress;
    }

    public void deployContract(String password, String source, String ERC20Address) throws Exception{


        Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth

        //這裡要改
        //創建憑證 Credentials credentials = WalletUtils.loadCredentials("密碼","Keystore文件地址");
        Credentials credentials = WalletUtils.loadCredentials(password, source);

        //要傳入使用的ERC20 的Address
        myContract contract = myContract.deploy(web3j, credentials, BigInteger.valueOf(200000), BigInteger.valueOf(20000000), ERC20Address).send();
        contractAddress = contract.getContractAddress();//丟進去存著
        System.out.println(contractAddress);

        getContractAddress();
    }


}