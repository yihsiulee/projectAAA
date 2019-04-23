package com.allpass.projectAAA.Security;

import com.allpass.projectAAA.Dao.MemberDao;
import com.allpass.projectAAA.Do.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberDetailsServiceImp implements UserDetailsService {

    @Resource MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String idCardNumber) throws UsernameNotFoundException {

    /*Here we are using dummy data, you need to load user data from
     database or other third party application*/
        Member member = memberDao.findByIdCardNumber(idCardNumber);

        User.UserBuilder builder;
        if (member != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(idCardNumber);
            builder.password(member.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

//    private User findUserbyUername(String username) {
//        if(username.equalsIgnoreCase("admin")) {
//            return new User(username, "admin123", "ADMIN");
//        }
//        return null;
//    }
}
