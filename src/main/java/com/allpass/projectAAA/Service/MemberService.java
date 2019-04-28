package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.MemberDao;
import com.allpass.projectAAA.Model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MemberService {
    @Resource
    private MemberDao memberDao;

    public void save(Member member){
        memberDao.save(member);
    }
    public void deleteMember(Long memberId){
        memberDao.deleteById(memberId);
    }
    //    public Customer findByLastName(){
//        customerdao.
//    }
//    public boolean login(String firstname,String lastname){
//        List allusername=customerdao.findAll();
//
//       customerdao.exists(new Customer());
//        return true;
//    }
    public Member getMemberInfo(Long id){
        Member member=memberDao.findById(id).get();
        return member;

    }
    public boolean verifyIdCardNumber(String idCardNumber){
        if(memberDao.findByIdCardNumber(idCardNumber)==null){
            return true;
        }else{
            return false;
        }
    }
//    public List<Member> findAll() {
//        List<Member> allUserName = null;
//        for (Member member : memberDao.findAll()) {
////            allUserName.add(customer.getFirstName());
//          allUserName.add(member);
//        }
//        return allUserName;
//    }

}