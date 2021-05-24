package com.imadelfetouh.adminservice.security;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.jwt.ValidateJWTToken;
import com.imadelfetouh.adminservice.model.jwt.UserData;
import com.imadelfetouh.adminservice.rabbit.RabbitConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class CookieFilter implements Filter {

    private final static Logger logger = Logger.getLogger(CookieFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null) {
            Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("jwt-token")).findFirst().orElse(null);
            if(cookie == null) {
                logger.info("cookie jwt-token not found");
                httpServletResponse.setStatus(401);
                return;
            }
            else{
                String userData = ValidateJWTToken.getInstance().getUserData(cookie.getValue());
                Gson gson = new Gson();

                if(userData == null) {
                    httpServletResponse.setStatus(401);
                    return;
                }

                UserData u = gson.fromJson(userData, UserData.class);
                if(u.getRole().equals(Role.ADMINISTRATOR.name())) {
                    if(!RabbitConfiguration.getInstance().getConnection().isOpen()) {
                        logger.info("Request made, but rabbit is down");
                        httpServletResponse.setStatus(503);
                        return;
                    }

                    httpServletRequest.setAttribute("userdata", userData);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                    return;
                }
                else{
                    logger.info("User is an administrator");
                    httpServletResponse.setStatus(401);
                    return;
                }
            }
        }
        else{
            logger.info("No cookies found");
        }

        httpServletResponse.setStatus(401);
    }
}
