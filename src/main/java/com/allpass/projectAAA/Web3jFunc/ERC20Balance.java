package com.allpass.projectAAA.Web3jFunc;

import com.allpass.projectAAA.Contract.ERC20;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class ERC20Balance {

//    public static String ERC20Address;//存發佈ERC20合約回傳的地址

    private static final String URL = "https://eth.pli.tw/port";

    private static final int PORT = 23002;

    private static final String RPC_URL = URL + "/" + PORT;

    public String ERC20Address(){
        DeployERC20 erc = new DeployERC20();
        System.out.println(erc.getERC20Address()+"合約地址");
        return erc.getERC20Address();
    }

    public BigInteger mybalance(Web3j web3j, TransactionManager manager, String userAddress) throws Exception {

        //先讀入ERC20合約
        ERC20 erc20Load = ERC20.load(ERC20Address(),web3j,manager,BigInteger.valueOf(0), BigInteger.valueOf(20000000));
        return erc20Load.balanceOf(userAddress).send();
    }

}
