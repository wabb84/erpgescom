package com.produccion.gescom.services.impl;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.produccion.gescom.commons.UserContextHolder;
import com.produccion.gescom.dto.ReinicioPasswordDtoR;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioDtoReq;
import com.produccion.gescom.dto.UsuarioDtoRes;
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.EEstadoUsuario;
import com.produccion.gescom.entity.ESexo;
import com.produccion.gescom.entity.Perfil;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.entity.Usuarioper;
import com.produccion.gescom.exceptions.CustomException;
import com.produccion.gescom.mapper.UsuarioMapper;
import com.produccion.gescom.repository.PerfilRepository;
import com.produccion.gescom.repository.SociedaRepository;
import com.produccion.gescom.repository.UserLoginRepository;
import com.produccion.gescom.repository.UsuarioperRepository;
//import com.produccion.gescom.services.SociedaService;
import com.produccion.gescom.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
//	private static final Log logger = LogFactory.getLog(UsuarioService.class);
	
    private final UserLoginRepository userrepository;
    private final SociedaRepository sociedarepository;
    private final PerfilRepository perfilrepository;
    private final UsuarioMapper usuariomapper;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioperRepository usuariorepository;
    private Long idusuario;  
    
	@Override
	public UsuarioDatosLoginDto findByDatosLogin(String codusuario) {
		UsuarioDatosLoginDto usuariologindatos = userrepository.FindByDatosLogin(codusuario);
		//System.out.println(usuariologindatos);
		if (usuariologindatos == null){
			throw new CustomException("SEGU-0001");
        }
		return usuariologindatos;
   	}	
    
	@Override
	@Transactional
	public UsuarioDtoRes nuevo(UsuarioDtoReq userDtoR) {
		String encriptPassword = "";
		SociedaDto socieda = sociedarepository.FindBySocieda(userDtoR.getIdsocieda());
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		UsuarioDatosLoginDto usuariologindatos = userrepository.FindByDatosLogin(userDtoR.getCodusuario()+'@' + socieda.getSerie());
		if (usuariologindatos != null){
			throw new CustomException("SEGU-0003");
		}
		Perfil perfil = perfilrepository.findById(userDtoR.getIdperfil()).orElse(null);
		if (perfil == null){
			throw new CustomException("SEGU-0004");
		}
		encriptPassword = passwordEncoder.encode(userDtoR.getPassword());
		UserEntity usuarionew = usuariomapper.toUsuario(userDtoR);
		usuarionew.setUsername(userDtoR.getCodusuario()+'@'+socieda.getSerie());
		usuarionew.setPassword(encriptPassword);
		usuarionew.setEstadousuario(userDtoR.getEstadousuario().equals("A") ? EEstadoUsuario.A : userDtoR.getEstadousuario().equals("I") ? EEstadoUsuario.I :  EEstadoUsuario.T);
		usuarionew.setSexo(userDtoR.getSexo().equals("M") ? ESexo.M : ESexo.F);
		usuarionew.setEstadopas("0");
		usuarionew.setIdperfil(userDtoR.getIdperfil());
		try {
			usuarionew.prePersist();
			userrepository.save(usuarionew);
			Usuarioper usuarioper = new Usuarioper();
			usuarioper.setIdusuario(usuarionew.getIduser());
			usuarioper.setIdperfil(userDtoR.getIdperfil());
			usuariorepository.save(usuarioper);
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		return usuariomapper.UsuarioTo(usuarionew);
	}
	
	@Override
	@Transactional
	public UsuarioDtoRes edita(UsuarioDtoReq userDtoR) {
		String encriptPassword = "";
		UserEntity usuarioedit = userrepository.findById(userDtoR.getIduser()).orElse(null);
		if (usuarioedit == null){
			throw new CustomException("SEGU-0001");
		}
		SociedaDto socieda = sociedarepository.FindBySocieda(userDtoR.getIdsocieda());
		if (socieda == null){
			throw new CustomException("SEGU-0002");
		}
		Perfil perfil = perfilrepository.findById(userDtoR.getIdperfil()).orElse(null);
		if (perfil == null){
			throw new CustomException("SEGU-0004");
		}
		encriptPassword = passwordEncoder.encode(userDtoR.getPassword());
		usuarioedit.setPassword(encriptPassword);
		usuarioedit.setEstadousuario(userDtoR.getEstadousuario().equals("A") ? EEstadoUsuario.A : userDtoR.getEstadousuario().equals("I") ? EEstadoUsuario.I :  EEstadoUsuario.T);
		usuarioedit.setSexo(userDtoR.getSexo().equals("M") ? ESexo.M : ESexo.F);

		usuariomapper.toUsuarioE(userDtoR, usuarioedit);
		return usuariomapper.UsuarioTo(userrepository.save(usuarioedit));
	};
	
	@Override
	@Transactional
	public String reinicioPassword(ReinicioPasswordDtoR reiniciodtor)
	{
		idusuario = UserContextHolder.getUserId();
		//UserEntity usuarioreset = userrepository.findById(reiniciodtor.getIdusuario()).orElse(null);
		//System.out.println(idusuario);
		
		UserEntity usuarioreset = userrepository.findById(idusuario).orElse(null);
		if (usuarioreset == null){
			throw new CustomException("SEGU-0001");
		}
		if (!passwordEncoder.matches(reiniciodtor.getPassant(), usuarioreset.getPassword()))
		{
			throw new CustomException("SEGU-0006");
		}
		if (!reiniciodtor.getPassnew().equals(reiniciodtor.getPasscon()) )
		{
			throw new CustomException("SEGU-0007");
		}
				
		usuarioreset.setPassword(passwordEncoder.encode(reiniciodtor.getPassnew()));
		
		userrepository.save(usuarioreset);
		//Map<String, Object> response = new HashMap<>();
		//response.put("mensaje", "Password actualizado con éxito");
		return "";
	}	
	
    @Override
	public List<UsuarioListaDto> listaUsuarios(Long idsocieda) {
		//logger.info("Service " + idsocieda);
		return userrepository.usuarioLista(idsocieda);
	}
    
    @Override
	public UsuarioEditaDto consulta(Long idusuario) {
		UserEntity usuarioedit = userrepository.findById(idusuario).orElse(null);
		if (usuarioedit == null){
			throw new CustomException("SEGU-0001");
		}
		return userrepository.EditaUsuario(idusuario);
	};
	
		
	
	
	
	
	/*@Override
	public UserEntity Datospruebauser(Long iduser) {
		//logger.info("wbarrantes2");
		return userRepository.findById(iduser).orElse(null);
	};
	
	@Override
	public UsuarioEditaDto consulta(Long idusuario) {
		return userRepository.EditaUsuario(idusuario);
	};
	
	public UserEntity edita(Long idusuario){
		return userRepository.findById(idusuario).orElse(null);
	};*/
}
