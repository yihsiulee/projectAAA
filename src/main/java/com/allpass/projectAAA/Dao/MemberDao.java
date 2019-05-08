package com.allpass.projectAAA.Dao;


import com.allpass.projectAAA.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member,Long> {

    Member findByName(String name);

    Member findByPassword(String password);

    Member findByIdCardNumber(String idCardNumber);

}
