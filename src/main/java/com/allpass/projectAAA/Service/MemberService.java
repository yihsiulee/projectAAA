package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.MemberDao;
import com.allpass.projectAAA.Do.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class MemberService {
    @Resource
    private MemberDao memberDao;

    public void addMember(Member member){
        memberDao.save(member);
    }
    public void deleteMember(String memberId){
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
    public void  findAllUsername() {
        ArrayList<String> allUserName = null;
        for (Member customer : memberDao.findAll()) {
//            allUserName.add(customer.getFirstName());
            System.out.println(customer.getName());
        }
//
    }
}