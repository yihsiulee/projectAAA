package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.myContract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class CredentialAndLoadContract {

    Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth

    Credentials userCredentials;
    myContract userContract;

    public Credentials getUserCredentials(String password, String source) throws Exception {
        makeCredentials(password, source);
        return userCredentials;
    }

    public myContract getLoadUserContract(String inputAddress, Credentials inputCredentials){
        LoadContract(inputAddress, inputCredentials);
        return userContract;
    }

    //call contract方法的人 丟password, source進來 丟credential出去
    public void makeCredentials(String password, String source) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(password, source);
        userCredentials = credentials;
    }

    //load合約 input合約address
    public void LoadContract(String inputAddress, Credentials inputCredentials){
        myContract con = myContract.load(inputAddress, web3j, inputCredentials, BigInteger.valueOf(200000), BigInteger.valueOf(20000000));
        userContract = con ;
    }

}
