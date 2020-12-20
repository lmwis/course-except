package com.fehead.course.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.counter.open.annotation.SetPointCut;
import com.fehead.lang.response.AuthenticationReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-16 11:03
 */
@Component("lmwisAuthenticationFailureHandler")
public class FeheadAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(FeheadAuthenticationFailureHandler.class);

    @Autowired
    ObjectMapper objectMapper ;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    @SetPointCut
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info(exception.getMessage());

        logger.info("登陆失败");

        FeheadAuthenticationSuccessHandler.setCORS(response);

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper
                    .writeValueAsString( AuthenticationReturnType
                            .create(exception.getMessage()
                                    ,HttpStatus.FORBIDDEN.value())));
    }
}
