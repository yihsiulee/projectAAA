package com.allpass.projectAAA.Dao;


import com.allpass.projectAAA.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
//<自創的型別,key值的型態>
public interface MemberDao extends JpaRepository<Member,Long> {

    Member findByName(String name);

    Member findByPassword(String password);

    Member findByIdCardNumber(String idCardNumber);

}
