package com.allpass.projectAAA.Web3jFunction;

import com.allpass.projectAAA.Contract.ERC20;
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

public class DeployErc20 {

    public static String ERC20Address;//存發佈ERC20合約回傳的地址

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    public void deploy() throws Exception{

        Web3jService web3jService = new HttpService(RPC_URL);
        Quorum web3j = new JsonRpc2_0Quorum(web3jService, 50, Async.defaultExecutorService());

        EnclaveService service = new EnclaveService(URL, PORT, new OkHttpClient());
        Constellation constellation = new Constellation(service, web3j);
        ContractGasProvider provider = new StaticGasProvider(
                BigInteger.ZERO,
                BigInteger.valueOf(1000000000L)
        );
        //這裡要改
        //創建憑證 Credentials credentials = WalletUtils.loadCredentials("密碼","Keystore文件地址");
        //一開始的ERC20 token會都轉到這個地址
//        Credentials credentials = WalletUtils.loadCredentials("12345",
//                "/Users/yihsiu/Desktop/passChain/keystore/UTC--2019-05-15T11-54-20.157478618Z--964a979fd34737c55ab2a2e127c5159988c247c4");

        String aaa = key.getRandomPrivateKey();
        Credentials credentials1 = Credentials.create(aaa);
        ERC20 erc = ERC20.deploy(web3j, credentials1, BigInteger.valueOf(0), BigInteger.valueOf(20000000)).send();

        System.out.println(aaa);
        ERC20Address = erc.getContractAddress();//取得ERC20地址
        System.out.println(ERC20Address+"ERC20地址");


        //讀入測試
        ERC20 erc2 = ERC20.load(ERC20Address,web3j,credentials1,BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        //看合約是否可用
        System.out.println(erc2.isValid());
    }

    public String getERC20Address() {
        return ERC20Address;
    }


}
