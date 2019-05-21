package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class DeployErc20 {

    public String ERC20Address;

    public void deploy() throws Exception{
        Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth

        //這裡要改
        //創建憑證 Credentials credentials = WalletUtils.loadCredentials("密碼","Keystore文件地址");
        //一開始的ERC20 token會都轉到這個地址
        Credentials credentials = WalletUtils.loadCredentials("12345",
                "/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-20.157478618Z--964a979fd34737c55ab2a2e127c5159988c247c4");

        ERC20 erc = ERC20.deploy(web3j, credentials, BigInteger.valueOf(200000), BigInteger.valueOf(20000000)).send();
        ERC20Address = erc.getContractAddress();//取得ERC20地址
        System.out.println(ERC20Address);


        //讀入測試
        ERC20 erc2 = ERC20.load(ERC20Address,web3j,credentials,BigInteger.valueOf(200000), BigInteger.valueOf(20000000));
        //看合約是否可用
        System.out.println(erc2.isValid());
    }

    public String getERC20Address() {
        return ERC20Address;
    }


}
