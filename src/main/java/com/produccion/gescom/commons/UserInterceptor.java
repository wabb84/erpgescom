package com.produccion.gescom.commons;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.repository.UserLoginRepository;

@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor  {
	private final UserLoginRepository userrepository;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UsuarioDatosLoginDto usuariologindatos = userrepository.FindByDatosLogin(authentication.getName());
    	UserContextHolder.setUserName(authentication.getName());
    	UserContextHolder.setUserId(usuariologindatos.getIdusuario());
    	//UserContextHolder.setSociedadId(usuariologindatos.gets);
    	
    	//System.out.println(usuariologindatos.getIdusuario());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContextHolder.clearContext();
    }
}