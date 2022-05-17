
package com.chickenbros.Controladores;

import com.chickenbros.Entidades.Producto;
import com.chickenbros.Repositorios.RepositorioProducto;
import com.chickenbros.Servicios.ServicioProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/producto")
public class ControladorProducto {
    
    
    @Autowired
    RepositorioProducto repoProducto;
    
     @Autowired
    ServicioProducto servProducto;
    
 //   LISTADO DE PRODUCTOS ADMIN C/BOTONES PARA EDITAR 
    @GetMapping("/listadoAdmin")
    public String productosAdmin(ModelMap modelo) {
        List<Producto> listaProductos = servProducto.listarProducto();
        modelo.put("listaProducto", listaProductos);
        return "productosAdmin";
    }
    
    //lista de producto clientes, sin botones 
     @GetMapping("/listado/{id}")
    public String productos(ModelMap modelo, @PathVariable("id") String id_usuario) {
        //(@PathVariable("id") int va)
        List<Producto> listaProductos = servProducto.listarProducto();
        modelo.put("listaProducto", listaProductos);
        modelo.put("id_usuario", id_usuario);
        return "listadoProductos";
    }
    
    // VISTA DE FORMULARIO PARA AGREGAR PRODUCTO
    @GetMapping("/agregarProducto")
    public String agregarProducto() throws Exception {
    //    servProducto.agregarProducto("Pollo Picante", "Con salsa de soja", 450,"pollo.jpg"); //Lo agergue de prueba
        return "agregarProducto.html";
    }
  
    @PostMapping("/agregarProducto")
    public String registrarProducto(ModelMap modelo,@RequestParam String nombre,@RequestParam String descripcion, @RequestParam String precio, @RequestParam String imagen ) {
        try {
            servProducto.agregarProducto(nombre, descripcion, Integer.parseInt(precio),imagen);
            modelo.put("exito", "Guardado con exito");
            List<Producto> productos = servProducto.listarProducto();
            modelo.put("productos", productos);
            return "agregarProducto.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "agregarProducto.html"; 
        }
    }


//EDITAR
    
    @GetMapping("/modificar/{id}")
    public String modificarProducto(@PathVariable String id, ModelMap modelo) throws Exception {
        modelo.put("producto", servProducto.getById(id));
        return "modificarProducto.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarProducto(ModelMap modelo, @PathVariable String id, @RequestParam String nombre,@RequestParam String descripcion, @RequestParam Integer precio, @RequestParam String imagen) throws Exception {
        try {
            servProducto.modificarProducto(id,nombre,descripcion, precio,imagen);
            return "redirect:/productos/listadoProductos";
        } catch (Exception e) {
            modelo.put("error", "No se pudo modificar el producto");
            modelo.put("producto", servProducto.getById(id));
            return "modificarProducto.html";
        }
    }
    
    // eliminar
     @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id){
        try {
            servProducto.eliminarProducto(id);
            
            return "redirect:/productos/listadoProductos";
        } catch (Exception e) {
            return "redirect:/productos/listadoProductos";
        }
    }
    
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id){
        
        try {
            servProducto.darDeBaja(id);
            
            return "redirect:/producto/listadoAdmin";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id){
        
        try {
            servProducto.darDeAlta(id);
            
            return "redirect:/producto/listadoAdmin";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
  
}
