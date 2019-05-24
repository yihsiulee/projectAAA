package com.allpass.projectAAA.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MemberLoginSuccessHandler
// extends SimpleUrlAuthenticationSuccessHandler
//        implements AuthenticationSuccessHandler {

//    public MemberLoginSuccessHandler() {
//        super();
//        setUseReferer(true);
//    }
 implements AuthenticationSuccessHandler {



    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

//        boolean admin = false;

        logger.info("AT onAuthenticationSuccess(...) function!");
        response.sendRedirect("/");
        //        response.sendRedirect(request.getHeader("referer"));
//        for (GrantedAuthority auth : authentication.getAuthorities()) {
//            if ("ROLE_ADMIN".equals(auth.getAuthority())){
//                admin = true;
//            }
//        }
//        response.sendRedirect("/");


//        if(admin){
//        }else{
//            response.sendRedirect("/user");
//        }
    }

}