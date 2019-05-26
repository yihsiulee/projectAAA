package com.allpass.projectAAA.Web3jFunc;

import com.allpass.projectAAA.Contract.myContract;
import okhttp3.OkHttpClient;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.JsonRpc2_0Quorum;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.enclave.Constellation;
import org.web3j.quorum.enclave.protocol.EnclaveService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;

public class DeployCONTRACT {
    //    public static String ERC20Address;//存發佈ERC20合約回傳的地址
    DeployERC20 de = new DeployERC20();

//    private static final String URL = "https://eth.pli.tw/port";
//
//    private static final int PORT = 23002;
//
//    private static final String RPC_URL = URL + "/" + PORT;

    // 合約發起人的credentials 外面傳入
    public String deployContract(Web3j web3j, Credentials credentials)  throws Exception{

//        /** Quorum的web3j東西 **/
//        Web3jService web3jService = new HttpService(RPC_URL);
//        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());
//
//        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
//        Constellation constellation = new Constellation(service, web3j);
//        ContractGasProvider provider = new StaticGasProvider(
//                BigInteger.ZERO,
//                BigInteger.valueOf(1000000000L)
//        );

        //要傳入使用的ERC20 的Address
        myContract contract = myContract.deploy(web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(20000000), de.getERC20Address()).send();
        return contract.getContractAddress();
    }
}