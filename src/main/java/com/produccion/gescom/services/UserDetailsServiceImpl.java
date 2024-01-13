package com.produccion.gescom.services;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.repository.UserLoginRepository;

//import com.produccion.gescom.repository.UserLoginRepository;
//import com.producion.gescom.entity.UserEntity;

//import com.produccion.consultaenlineach.controller.ContribuyenteController;
//import com.produccion.consultaenlineach.entity.UserEntity;
//import com.produccion.consultaenlineach.repository.ContribuyenteLoginRepository;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);
	
    @Autowired
    private UserLoginRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//boolean 
        //UserEntity userEntity = userRepository.findByUsername(username)
        //        .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
    	//logger.info("Detail 1");
    	
    	UserEntity userEntity = userRepository.findByUsername(username)
    	        .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
    	//logger.info("Detail 2");
    	//logger.info("Prueba");
        //logger.info(userEntity.getUsername());
        //logger.info(userEntity.getPassword());
    	
    	
    	
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getDesrol().name())))
                .collect(Collectors.toSet());
        
        //logger.info("Detail 3");

        return new User(userEntity.getUsername(),
        		userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
