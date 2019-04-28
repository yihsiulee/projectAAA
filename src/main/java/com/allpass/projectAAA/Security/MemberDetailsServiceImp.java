package com.allpass.projectAAA.Security;

import com.allpass.projectAAA.Dao.MemberDao;
import com.allpass.projectAAA.Model.Member;
import com.allpass.projectAAA.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MemberDetailsServiceImp implements UserDetailsService {

    @Resource
    private MemberDao memberDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String idCardNumber) throws UsernameNotFoundException {
        Member member = memberDao.findByIdCardNumber(idCardNumber);
        if (member == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(member.getIdCardNumber(),
                member.getPassword(),
                mapRolesToAuthorities(member.getRoles()));
    }

    public Member findByIdCardNumber(String idCardNumber){
        return memberDao.findByIdCardNumber(idCardNumber);
    }

//    public User save(UserRegistrationDto registration){
//        User user = new User();
//        user.setFirstName(registration.getFirstName());
//        user.setLastName(registration.getLastName());
//        user.setEmail(registration.getEmail());
//        user.setPassword(passwordEncoder.encode(registration.getPassword()));
//        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//        return userRepository.save(user);
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
//        if (member == null) {
//            throw new UsernameNotFoundException(idCardNumber);
//        }
//        return  member;
//    }



//    private User findUserbyUername(String username) {
//        if(username.equalsIgnoreCase("admin")) {
//            return new User(username, "admin123", "ADMIN");
//        }
//        return null;
//    }

