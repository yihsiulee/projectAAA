package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.myContract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class CredentialAndLoadContract {

//    Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth


//    public Credentials getUserCredentials(String password, String source) throws Exception {
//        return makeCredentials(password, source);
//    }

    public myContract getLoadUserContract(Web3j web3j, TransactionManager manager, String inputAddres){
        return LoadContract(web3j, manager, inputAddres);
    }

    //call contract方法的人 丟password, source進來 丟credential出去
    public String makeCredentials() throws Exception {
//        Credentials.create()
        return key.getRandomPrivateKey();
//        return WalletUtils.loadCredentials(password, source);
    }

    //load合約 input合約address
    public myContract LoadContract(Web3j web3j, TransactionManager manager, String inputAddress){
        return myContract.load(inputAddress, web3j, manager, new DefaultGasProvider());
    }

}
