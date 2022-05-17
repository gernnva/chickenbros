
package com.chickenbros.Servicios;

import com.chickenbros.Entidades.Cliente;
import com.chickenbros.Repositorios.RepositorioCliente;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ServicioCliente implements UserDetailsService {
    
    @Autowired
	private RepositorioCliente clienteRepo;

	//@Transactional
	public void guardarCliente(String nombre, String apellido, String email, String clave, String direccion, Long telefono, String claveRep) throws Exception {

                validar(nombre, apellido, email, clave, direccion,telefono,claveRep);
                
                Cliente cliente = new Cliente();
                    
                cliente.setNombre(nombre);
		cliente.setApellido(apellido);
                cliente.setEmail(email);
                
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		cliente.setClave(encoder.encode(clave)); 
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);
                
                 clienteRepo.save(cliente);
	}
        
        public void validar(String nombre, String apellido, String email, String clave, String direccion, Long telefono, String claveRep) throws Exception
        {
          if (nombre == null || nombre.isEmpty()) {
        throw new Exception("Es necesario colocar el nombre");
        } 
         if (apellido == null || apellido.isEmpty()) {
        throw new Exception("Es necesario colocar el apellido");
        }
          if (email == null || email.isEmpty()) {
        throw new Exception("Es necesario colocar el email ");
        }
           if (clave == null || clave.isEmpty()) {
        throw new Exception("Es necesario colocar una contrasenia");
        }
          if (direccion == null || direccion.isEmpty()) {
        throw new Exception("Es necesario colocar una contrasenia");
        }
          if (telefono == null || telefono>9999999999L) {
        throw new Exception("El telefono es invalido");
        }
           if (!clave.equals(claveRep)) {
        throw new Exception("La contraseña no coincide");
        }
        }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Cliente cliente = clienteRepo.findByEmail(email);
        if (cliente != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_");//ROLE_ADMIN O ROLE_USER
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("clienteSesion", cliente);

            User user = new User(cliente.getEmail(), cliente.getClave(), permisos);
            return user;

        } else {
            return null;
        }
    }
}
