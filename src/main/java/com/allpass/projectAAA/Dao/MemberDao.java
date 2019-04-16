package com.allpass.projectAAA.Dao;


import com.allpass.projectAAA.Do.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberDao extends JpaRepository<Member,String> {

    List<Member> findByLastName(String lastName);

    List<Member>findByFirstName(String firstName);

}
