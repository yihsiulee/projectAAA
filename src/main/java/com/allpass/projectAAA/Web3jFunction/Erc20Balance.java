package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.ERC20;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Async;

import java.io.IOException;
import java.math.BigInteger;

public class Erc20Balance {

    String ERC20Address ;
    BigInteger balances;
    Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth
    DeployErc20 erc20 = new DeployErc20() ;

    public void getErc20Address(){
        ERC20Address= erc20.getERC20Address();
    }

    public BigInteger getBalance(){
        return balances ;
    }

    public void callBalance(String password, String source, String userAddress) throws Exception {

        getErc20Address();
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        Credentials credentials = WalletUtils.loadCredentials(password, source);

        //先讀入ERC20合約
        ERC20 erc20Load =ERC20.load(ERC20Address, web3j, credentials,contractGasProvider);
        //查看合約是否可用
        System.out.println(erc20Load.isValid());
        BigInteger mybalance = erc20Load.balanceOf(userAddress).send();//userAddress應該也能從source抓
        balances = mybalance ;
        System.out.println(balances);

        getBalance();
    }


}
