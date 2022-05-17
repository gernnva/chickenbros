
package com.chickenbros.Controladores;

import com.chickenbros.Servicios.ServicioAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class ControladorAdmin {
    
    
	@Autowired
	private ServicioAdmin adminServ;
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		//List<Admin> todos = adminServ.listarTodos();
		
		//modelo.addAttribute("admins", todos);
		
		return "list-admin";
	}
	
	@GetMapping("/registro")
	public String formulario(ModelMap modelo, @RequestParam String nombre,@RequestParam String email, @RequestParam String clave ) throws Exception {
            adminServ.guardar(nombre, email, clave); 
         return "form_admin";
	}
}
