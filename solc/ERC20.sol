pragma solidity ^0.5.0;

library SafeMath {
    function add(uint a, uint b) internal pure returns (uint c) {
        c = a + b;
            require(c >= a);
        
    }
    function sub(uint a, uint b) internal pure returns (uint c) {
        require(b <= a);
        c = a - b;
        
    }
    function mul(uint a, uint b) internal pure returns (uint c) {
        c = a * b;
        require(a == 0 || c / a == b);
        
    }
    function div(uint a, uint b) internal pure returns (uint c) {
        require(b > 0);
        c = a / b;
        
    }
    
}
 
 
contract ERC20Interface {
    function totalSupply() public view returns (uint);
    function balanceOf(address tokenOwner) public view returns (uint balance);
    function allowance(address tokenOwner, address spender) public view returns (uint remaining);
    function transfer(address to, uint tokens) public returns (bool success);
    function approve(address spender, uint tokens) public returns (bool success);
    function transferFrom(address from, address to, uint tokens) public returns (bool success);
    function orgTransfer(address to, uint tokens) public returns (bool success);
    function orgTransferFrom(address from, address to, uint tokens) public returns (bool success);
    
    event Transfer(address indexed from, address indexed to, uint tokens);
    event Approval(address indexed tokenOwner, address indexed spender, uint tokens);
    }
    
    
//擁有者合約
contract Owned {
    address public owner;
    address public newOwner;
    
    event OwnershipTransferred(address indexed _from, address indexed _to);
    
    constructor() public {
        owner = msg.sender;
        
    }
    
    modifier onlyOwner {
        require(msg.sender == owner);
        _;
        
    }

    //輸入的_newOwner
    function transferOwnership(address _newOwner) public onlyOwner {
        newOwner = _newOwner;
        
    }
    
    //當new owner call了才能用
    function acceptOwnership() public {
        require(msg.sender == newOwner);
        emit OwnershipTransferred(owner, newOwner);
        owner = newOwner;
        newOwner = address(0);
        
    }
    
}
 

contract TestSupplyToken is ERC20Interface, Owned{
    
    using SafeMath for uint;
    
    string public symbol;
    string public  name;
    uint8 public decimals;
    uint256 _totalSupply;//總發行token數量
    
    
    
    mapping(address => uint) balances;//地址map到token數量
    mapping(address => mapping(address => uint)) allowed;//另一地址（B）map到Ａ的token數量
    
        constructor() public {
            symbol = "PRV";
            name = "Paper Review Token";
            decimals = 18;
            _totalSupply = 1000000 * 10**uint(decimals);//  發行總數
            balances[owner] = _totalSupply;//把所有丟到owner地址
            emit Transfer(address(0), owner, _totalSupply);
            
        }
        //查看總數
        function totalSupply() public view returns (uint){
            return _totalSupply.sub(balances[address(0)]);
            
        }
        
        //查看餘額
        function balanceOf(address tokenOwner) public view returns (uint balance){
            return balances[tokenOwner];
        }
        
        //從call的人轉到to  .sub() 減法
        function transfer(address to, uint tokens) public returns (bool success){
            balances[msg.sender]=balances[msg.sender].sub(tokens);
            balances[to]=balances[to].add(tokens);
            emit Transfer(msg.sender, to, tokens);
            return true;
        }
        
        //前一張合約的呼叫者地址是tx.origin  前一張合約call此地址是msg.sender 
        //從tx.origin轉到to    .sub() 減法
        function orgTransfer(address to, uint tokens) public returns (bool success){
            balances[tx.origin]=balances[tx.origin].sub(tokens);
            balances[to]=balances[to].add(tokens);
            emit Transfer(tx.origin, to, tokens);
            return true;
        }
        
        //把token綁起來 
        //msg.sender execute tokenContract.approve(0x2222222222222222222222222222222222222222, 30)
        function approve(address spender, uint tokens) public returns (bool success){
            allowed[msg.sender][spender] = tokens;//msg.sender 允許 spender 從msg.sender中 轉走tokens數量
            emit Approval(msg.sender, spender, tokens); 
            return true;
        }
        
        //提領綁起來的錢
        function transferFrom(address from, address to, uint tokens) public returns (bool success){
            balances[from] = balances[from].sub(tokens);//從from錢包裡扣
            allowed[from][msg.sender] = allowed[from][msg.sender].sub(tokens);//更改陣列值
            balances[to] = balances[to].add(tokens);//加到to錢包
            emit Transfer(from, to, tokens);
            return true;
        }
        
        //tx.origin(審文人)提領綁起來的錢
        function orgTransferFrom(address from, address to, uint tokens) public returns (bool success){
            balances[from] = balances[from].sub(tokens);//從from錢包裡扣
            allowed[from][tx.origin] = allowed[from][tx.origin].sub(tokens);//更改陣列值
            balances[to] = balances[to].add(tokens);//加到to錢包
            emit Transfer(from, to, tokens);
            return true;
        }
        
        //看綁起來的有多少錢
        function allowance(address tokenOwner, address spender) public view returns (uint remaining){
            return allowed[tokenOwner][spender];
        }
        
        // //自己加的 call這方法 被審稿人把錢轉到合約
        // function
        
        
    
    
}