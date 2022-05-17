
package com.chickenbros.Servicios;

import com.chickenbros.Entidades.Administrador;
import com.chickenbros.Repositorios.RepositorioAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioAdmin {
    
    @Autowired
	private RepositorioAdmin adminRepo;

	@Transactional
	public void guardar(String nombre, String email, String clave) throws Exception {
            //String nombre, String apellido, String email, String clave
            Administrador admin = new Administrador();   

             admin.setNombre(nombre);
             admin.setEmail(email);
             admin.setClave(clave);
            adminRepo.save(admin); 
           }

}
