package com.allpass.projectAAA.JiaGer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class PaperReviewer extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051602080610fa98339810180604052602081101561003057600080fd5b8101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600360006101000a81548160ff02191690831515021790555080600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555030600360016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610e7b8061012e6000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806399026a5b1161007157806399026a5b1461016c578063b2fa1c9e146101ba578063c564f576146101c4578063dc3db9ae146101ce578063f7cbb1b2146101d8578063fb1a89711461028f576100a9565b80632e4a3657146100ae578063522e1177146100b857806380b2edd8146100da5780638f2133fe1461011e57806396d5d35d14610162575b600080fd5b6100b6610299565b005b6100c061029b565b604051808215151515815260200191505060405180910390f35b61011c600480360360208110156100f057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102ae565b005b6101606004803603602081101561013457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061066b565b005b61016a6107cf565b005b6101b86004803603604081101561018257600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108ce565b005b6101c2610a61565b005b6101cc610a84565b005b6101d6610b62565b005b61021a600480360360208110156101ee57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c40565b604051808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200186151515158152602001851515151581526020018415151515815260200183151515158152602001828152602001965050505050505060405180910390f35b610297610cd0565b005b565b600360009054906101000a900460ff1681565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff1615151480156103e957506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160159054906101000a900460ff161515145b801561046a57506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160169054906101000a900460ff161515145b80156104eb57506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160179054906101000a900460ff161515145b6104f457600080fd5b60056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154600281905550600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663095ea7b3600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166002546040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561062c57600080fd5b505af1158015610640573d6000803e3d6000fd5b505050506040513d602081101561065657600080fd5b81019080805190602001909291905050505050565b60011515600360009054906101000a900460ff161515141561068c57600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160169054906101000a900460ff1615151461074f57600080fd5b600160056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160176101000a81548160ff02191690831515021790555050565b60011515600360009054906101000a900460ff16151514156107f057600080fd5b33600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160146101000a81548160ff021916908315150217905550565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010181905550600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f6af3d1930846040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b158015610a2157600080fd5b505af1158015610a35573d6000803e3d6000fd5b505050506040513d6020811015610a4b57600080fd5b8101908080519060200190929190505050505050565b60011515600360009054906101000a900460ff1615151415610a8257600080fd5b565b60011515600360009054906101000a900460ff1615151415610aa557600080fd5b60011515600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160159054906101000a900460ff16151514610b0557600080fd5b6001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160166101000a81548160ff021916908315150217905550565b60011515600360009054906101000a900460ff1615151415610b8357600080fd5b60011515600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff16151514610be357600080fd5b6001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160156101000a81548160ff021916908315150217905550565b60056020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060000160149054906101000a900460ff16908060000160159054906101000a900460ff16908060000160169054906101000a900460ff16908060000160179054906101000a900460ff16908060010154905086565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166377ef169b600360019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1633600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101546040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b158015610e1157600080fd5b505af1158015610e25573d6000803e3d6000fd5b505050506040513d6020811015610e3b57600080fd5b81019080805190602001909291905050505056fea165627a7a72305820c7c4f4697a0458e068dfa1da17bdec35296f47bc8dd232eae38b46350295f5e80029";

    public static final String FUNC_RETURNTOKEN = "returnToken";

    public static final String FUNC_COMPLETE = "complete";

    public static final String FUNC_APPROVETOKEN = "approveToken";

    public static final String FUNC_ISGIVETOKEN = "isGiveToken";

    public static final String FUNC_ISAPPROVE = "isApprove";

    public static final String FUNC_SENDARTICLE = "sendArticle";

    public static final String FUNC_ISCOMPLETE = "isComplete";

    public static final String FUNC_ISRETURNREVIEW = "isReturnReview";

    public static final String FUNC_ISRECIEVEPOST = "isRecievePost";

    public static final String FUNC_REVIEWERS = "reviewers";

    public static final String FUNC_TRANSFERFROMTOKEN = "transferFromToken";

    @Deprecated
    protected PaperReviewer(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaperReviewer(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaperReviewer(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaperReviewer(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> returnToken() {
        final Function function = new Function(
                FUNC_RETURNTOKEN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> complete() {
        final Function function = new Function(FUNC_COMPLETE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> approveToken(String _addr) {
        final Function function = new Function(
                FUNC_APPROVETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isGiveToken(String _addr) {
        final Function function = new Function(
                FUNC_ISGIVETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isApprove() {
        final Function function = new Function(
                FUNC_ISAPPROVE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendArticle(BigInteger _uint, String _addr) {
        final Function function = new Function(
                FUNC_SENDARTICLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_uint), 
                new org.web3j.abi.datatypes.Address(_addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isComplete() {
        final Function function = new Function(
                FUNC_ISCOMPLETE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isReturnReview() {
        final Function function = new Function(
                FUNC_ISRETURNREVIEW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> isRecievePost() {
        final Function function = new Function(
                FUNC_ISRECIEVEPOST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger>> reviewers(String param0) {
        final Function function = new Function(FUNC_REVIEWERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger>>(
                new Callable<Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger>>() {
                    @Override
                    public Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, Boolean, Boolean, Boolean, Boolean, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transferFromToken() {
        final Function function = new Function(
                FUNC_TRANSFERFROMTOKEN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PaperReviewer load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaperReviewer(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaperReviewer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaperReviewer(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaperReviewer load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PaperReviewer(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaperReviewer load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaperReviewer(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaperReviewer> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(PaperReviewer.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PaperReviewer> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(PaperReviewer.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PaperReviewer> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(PaperReviewer.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PaperReviewer> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(PaperReviewer.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
