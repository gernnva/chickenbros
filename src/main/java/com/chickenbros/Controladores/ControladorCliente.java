
package com.chickenbros.Controladores;

import com.chickenbros.Entidades.Cliente;
import com.chickenbros.Servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ControladorCliente {
    
    
	@Autowired
	private ServicioCliente servCliente;
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		//List<Cliente> todos = clienteServ.listarTodos();
		
		//modelo.addAttribute("clientes", todos);
		
		return "lista_cliente";
	}
	
	@GetMapping("/registro")
	public String formulario() throws Exception {
            //clienteServ.guardarCliente(); *Esto solo realiza una prueba sin post
		return "registroUsuario";
	}
	
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String clave, @RequestParam String direccion, @RequestParam String telefono, @RequestParam String claveRep) {
		
		try {
                    Long telefonoL = Long.parseLong(telefono);
                       servCliente.guardarCliente(nombre,apellido, email, clave,  direccion, telefonoL,claveRep);
                      modelo.put("exito", "Guardado con exito");
                       return "registroUsuario";

		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "registroUsuario";
		}
	}
        
        @GetMapping("/login")
        public String iniciarSesion()
        {
          return "login";
        }
//        @PostMapping("/login")
//        public String iniciarSesionPost(Model modelo,@RequestParam String email, @RequestParam String clave)
//        {
//            try {
//                Cliente cliente=servCliente.buscarCliente(email, clave);
//                String id_cliente=cliente.getId_cliente();
//                modelo.addAttribute("id_cliente", cliente.getApellido());
//   
//                return "redirect:/producto/listado/"+id_cliente;
//                
//            } catch (Exception e) {
//                modelo.addAttribute("error", "Error al iniciar sesion");
//                return "login";
//            }
//            
//        }
}
