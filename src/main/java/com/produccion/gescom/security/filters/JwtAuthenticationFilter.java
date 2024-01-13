package com.produccion.gescom.security.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.repository.UserLoginRepository;
import com.produccion.gescom.security.jwt.JwtUtils;

//import com.produccion.gescom.entity.UserEntity;
//import com.produccion.consultaenlineach.security.jwt.JwtUtils;
//import com.produccion.consultaenlineach.service.ContribuyenteService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //@Autowired
	//private ContribuyenteService contribuyenteservice;
	
    @Autowired
    private UserLoginRepository userRepository;


    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UserEntity userEntity = null;
        String username = "";
        String password = "";
        try{
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            //username = userEntity.getUsername();
            username = userEntity.getUsername();
            
            password = userEntity.getPassword();
            
            
            //logger.info("Inicio" + userEntity.getEstado());
            //logger.info(username);
            //logger.info(password);
            
            
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
    	//logger.info("Jwt 1");
    	
        User user = (User) authResult.getPrincipal();
        //logger.info("Jwt 2");
        String token = jwtUtils.generateAccesToken(user.getUsername());
        //logger.info("Jwt 3");

        response.addHeader("Authorization", token);
        //logger.info("Jwt 4");
        
        //logger.info(user.getUsername());
        //Contribuyente datoscontribuyente = contribuyenteservice.DatosContribuyente(user.getUsername());
        /*UserEntity userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + user.getUsername() + " no existe."));*/
        
        
        //UserEntity userEntity = userRepository.getName(user.getUsername());
        
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Autenticación Correcta");
        httpResponse.put("username", user.getUsername());
        //httpResponse.put("expirado", user.isAccountNonExpired());
        
        //httpResponse.put("td_c", datoscontribuyente.getTd_c());
        //httpResponse.put("co_num", datoscontribuyente.getCo_num());
        //httpResponse.put("co_nl", datoscontribuyente.getCo_nl());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        //super.successfulAuthentication(request, response, chain, authResult);
    }
}
