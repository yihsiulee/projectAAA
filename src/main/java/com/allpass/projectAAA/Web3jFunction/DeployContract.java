package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.myContract;
import okhttp3.OkHttpClient;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.enclave.Constellation;
import org.web3j.quorum.enclave.protocol.EnclaveService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class DeployContract {

//    //call這裡獲得合約地址
//    public String getContractAddress(String password, String source, String ERC20Address) throws Exception {
//        return deployContract(password,  source,  ERC20Address);
//    }
//
//    // 合約發起人的credentials
//    public String deployContract(String password, String source, String ERC20Address)  throws Exception{
//        Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth
//
//        //這裡要改
//        //創建憑證 Credentials credentials = WalletUtils.loadCredentials("密碼","Keystore文件地址");
//        Credentials credentials = WalletUtils.loadCredentials(password, source);
//
//        //要傳入使用的ERC20 的Address
//        myContract contract = myContract.deploy(web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(20000000), ERC20Address).send();
//        return contract.getContractAddress();
//    }

    //call這裡獲得合約地址
//    public String getContractAddress(String password, String source, String ERC20Address) throws Exception {
//        return deployContract(password,  source,  ERC20Address);
//    }

    public static String ERC20Address;//存發佈ERC20合約回傳的地址

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    // 合約發起人的credentials
    public String deployContract(Credentials credentials, String ERC20Address)  throws Exception{
//        Web3j web3j = new JsonRpc2_0Web3j(new HttpService(),50, Async.defaultExecutorService());//連線上geth
        Web3jService web3jService = new HttpService(RPC_URL);
        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());

        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
        Constellation constellation = new Constellation(service, web3j);
        ContractGasProvider provider = new StaticGasProvider(
                BigInteger.ZERO,
                BigInteger.valueOf(1000000000L)
        );

        //要傳入使用的ERC20 的Address
        myContract contract = myContract.deploy(web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(20000000), ERC20Address).send();
        return contract.getContractAddress();
    }




}