package com.allpass.projectAAA.Contract;


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
 * <p>Generated with web3j version 4.2.0.
 */
public class myContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051602080610f8a8339810180604052602081101561003057600080fd5b8101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600360006101000a81548160ff02191690831515021790555080600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555030600360016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610e5c8061012e6000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063b2fa1c9e11610066578063b2fa1c9e146101ad578063c564f576146101b7578063dc3db9ae146101c1578063f7cbb1b2146101cb578063fb1a8971146102825761009e565b80632e4a3657146100a357806351c202b4146100ad57806380b2edd81461011b5780638f2133fe1461015f57806396d5d35d146101a3575b600080fd5b6100ab61028c565b005b610119600480360360608110156100c357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061028e565b005b61015d6004803603602081101561013157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610422565b005b6101a16004803603602081101561017557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107df565b005b6101ab610943565b005b6101b5610a42565b005b6101bf610a65565b005b6101c9610b43565b005b61020d600480360360208110156101e157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c21565b604051808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200186151515158152602001851515151581526020018415151515815260200183151515158152602001828152602001965050505050505060405180910390f35b61028a610cb1565b005b565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010181905550600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f6af3d1984846040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b1580156103e157600080fd5b505af11580156103f5573d6000803e3d6000fd5b505050506040513d602081101561040b57600080fd5b810190808051906020019092919050505050505050565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff16151514801561055d57506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160159054906101000a900460ff161515145b80156105de57506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160169054906101000a900460ff161515145b801561065f57506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160179054906101000a900460ff161515145b61066857600080fd5b60056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154600281905550600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663095ea7b3600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166002546040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b1580156107a057600080fd5b505af11580156107b4573d6000803e3d6000fd5b505050506040513d60208110156107ca57600080fd5b81019080805190602001909291905050505050565b60011515600360009054906101000a900460ff161515141561080057600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001151560056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160169054906101000a900460ff161515146108c357600080fd5b600160056000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160176101000a81548160ff02191690831515021790555050565b60011515600360009054906101000a900460ff161515141561096457600080fd5b33600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160146101000a81548160ff021916908315150217905550565b60011515600360009054906101000a900460ff1615151415610a6357600080fd5b565b60011515600360009054906101000a900460ff1615151415610a8657600080fd5b60011515600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160159054906101000a900460ff16151514610ae657600080fd5b6001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160166101000a81548160ff021916908315150217905550565b60011515600360009054906101000a900460ff1615151415610b6457600080fd5b60011515600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff16151514610bc457600080fd5b6001600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160156101000a81548160ff021916908315150217905550565b60056020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060000160149054906101000a900460ff16908060000160159054906101000a900460ff16908060000160169054906101000a900460ff16908060000160179054906101000a900460ff16908060010154905086565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166377ef169b600360019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1633600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101546040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b158015610df257600080fd5b505af1158015610e06573d6000803e3d6000fd5b505050506040513d6020811015610e1c57600080fd5b81019080805190602001909291905050505056fea165627a7a72305820bde42c8e0b6acd08b2b5045f3f18b8027877ccc64248c6ad99606c930999264f0029";

    public static final String FUNC_APPROVETOKEN = "approveToken";

    public static final String FUNC_ISAPPROVE = "isApprove";

    public static final String FUNC_ISCOMPLETE = "isComplete";

    public static final String FUNC_ISGIVETOKEN = "isGiveToken";

    public static final String FUNC_ISRECIEVEPOST = "isRecievePost";

    public static final String FUNC_ISRETURNREVIEW = "isReturnReview";

    public static final String FUNC_RETURNTOKEN = "returnToken";

    public static final String FUNC_SENDARTICLE = "sendArticle";

    public static final String FUNC_TRANSFERFROMTOKEN = "transferFromToken";

    public static final String FUNC_REVIEWERS = "reviewers";

    @Deprecated
    protected myContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected myContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected myContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected myContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> approveToken(String _addr) {
        final Function function = new Function(
                FUNC_APPROVETOKEN,
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

    public RemoteCall<TransactionReceipt> isComplete() {
        final Function function = new Function(
                FUNC_ISCOMPLETE,
                Arrays.<Type>asList(),
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

    public RemoteCall<TransactionReceipt> isRecievePost() {
        final Function function = new Function(
                FUNC_ISRECIEVEPOST,
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

    public RemoteCall<TransactionReceipt> returnToken() {
        final Function function = new Function(
                FUNC_RETURNTOKEN,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendArticle(String _contractAddress, BigInteger _uint, String _addr) {
        final Function function = new Function(
                FUNC_SENDARTICLE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_contractAddress),
                        new org.web3j.abi.datatypes.generated.Uint256(_uint),
                        new org.web3j.abi.datatypes.Address(_addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFromToken() {
        final Function function = new Function(
                FUNC_TRANSFERFROMTOKEN,
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

    @Deprecated
    public static myContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new myContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static myContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new myContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static myContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new myContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static myContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new myContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<myContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(myContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<myContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(myContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<myContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(myContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<myContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(tokenAddress)));
        return deployRemoteCall(myContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
