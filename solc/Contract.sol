pragma solidity ^0.5.0;

import "./myERC20.sol";

contract smartContract{
    address owner ; //活動發起人的address
    address tempRev ;
    uint tempToken ;
    bool complete ;//完成的T/F
    address thisContrAddr ;//這合約的地址
    
    TestSupplyToken myErc;
    
    struct Reviewer{
        address addr ; //審稿人
        
        bool approve ;//同意請求的T/F
        
        bool recievePost ;//接收文章的T/F
        bool returnReview;//回覆文章的T/F
        bool getToken; //可否發錢的T/F
        
        uint tokenNum;//給審文人多少
        //bytes32 postHash;//文章的hash
        
    }
    
    mapping (address => Reviewer) public reviewers ;
    
    //合約地址的擁有者才能call的modifier
    modifier onlyOwner(){
        require(msg.sender==owner);
        _;
    }
    
    constructor(TestSupplyToken tokenAddress) public{
        owner = msg.sender ;// deploy的人 發起人address
        
        // approve = false ;
        // recievePost = false ;
        // returnReview = false ;
        // getToken = false ;
        complete = false ;
        // numRev = 0 ;
        myErc = tokenAddress;
        thisContrAddr=address(this);
    }
    
    //審文人同意審文call這個
    function isApprove() public{
        require(complete != true);
        // reviewers[msg.sender] = Reviewer(msg.sender, true, false, false, false);
        reviewers[msg.sender].addr = msg.sender;
        reviewers[msg.sender].approve = true ;
    }
    
    //活動發起人發送文章時call 把錢丟進這合約 丟多少 給誰(審文人)（要輸入地址） 
    //此時msg.sender是活動發起人
    function sendArticle(address _contractAddress, uint _uint, address _addr) public {
        // require(complete != true);
        tempRev = _addr ;
        reviewers[tempRev].tokenNum = _uint;
        
        myErc.orgTransfer(_contractAddress, _uint);

    }
    
    //接收到文章後call這個 要丟文章hash值進去?
    function isRecievePost() public {
        require(complete != true);
        require(reviewers[msg.sender].approve == true);
        // reviewers[msg.sender] = reviewer(msg.sender, true, true, false, false);
        reviewers[msg.sender].recievePost = true ;
    }
    
    //回傳review後call這個
    function isReturnReview() public {
        require(complete != true);
        require(reviewers[msg.sender].recievePost == true);
        // reviewers[msg.sender] = reviewer(msg.sender, true, true, true,false);
        reviewers[msg.sender].returnReview = true ;
    }
    

    //可以發錢了嗎 只有活動發起人可以call 看完回覆之後call的 審文人的地址
    function isGiveToken(address _addr) public {
        require(complete != true);
        tempRev = _addr;
        require(reviewers[tempRev].returnReview == true);
        reviewers[tempRev].getToken = true ;
    }
    
    //和上面方法放同一個頁面 確認轉錢 此時msg.sender是活動發起人
    //如果all true  則讓此合約裡面的token可以被轉走
    function approveToken(address _addr) public {
        tempRev = _addr;
        // require(complete != true 
        //     && reviewers[tempRev].approve==true 
        //     && reviewers[tempRev].recievePost==true
        //     && reviewers[tempRev].returnReview==true
        //     && reviewers[tempRev].getToken==true);
        require(reviewers[tempRev].approve==true 
            && reviewers[tempRev].recievePost==true
            && reviewers[tempRev].returnReview==true
            && reviewers[tempRev].getToken==true);
        
        tempToken = reviewers[tempRev].tokenNum ;
        myErc.approve(tempRev, tempToken);

    }

    //審文人拿錢 審文人從此合約把錢轉走 審文人是(msg.sender)
    function transferFromToken() public{
        myErc.orgTransferFrom(thisContrAddr,msg.sender,reviewers[msg.sender].tokenNum);
    }

//到這裡
//以下的邏輯重想一下
   
    //錯誤時 把錢從合約還回去活動發起人 沒接收到文章 沒回傳review的兩種狀況
    function returnToken() public{
        
    }
    
    //所有審文人跑完程序之後 給complete true 此合約結束
    function isComplete () public {
        require(complete != true);
        
        
        
    }
    
    
}