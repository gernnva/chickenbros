
package com.chickenbros.Controladores;

import com.chickenbros.Entidades.AuxProducto;
import com.chickenbros.Entidades.Pedido;
import com.chickenbros.Entidades.Producto;
import com.chickenbros.Servicios.ServicioDetallePedido;
import com.chickenbros.Servicios.ServicioPedido;
import com.chickenbros.Servicios.ServicioProducto;
import com.chickenbros.Servicios.ServiciosAuxProducto;
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
@RequestMapping("/pedido")
public class ControladorPedido {
    
    
    @Autowired
    private  ServicioPedido servPedido;
    @Autowired 
    private ServicioProducto servProducto; 
    @Autowired
    private ServiciosAuxProducto servAux;
    @Autowired
    private ServicioDetallePedido servDetalle;
    
    
    @GetMapping("/listar") //En el lado del Admin
    public String agregarpedido(ModelMap modelo)
    {
        List<String> listaHoras = servPedido.listarHoras(); //Me trae una lista con horarios de 30 min extra 
        
        modelo.put("listaHora", listaHoras);
        
        return "pedido";
    }
    
    @GetMapping("/tomarpedido") //Verificar
    public String tomarPedido(ModelMap modelo)
    {
       List<Producto> listaProductos = servProducto.listarProducto();
      modelo.put("listaProducto", listaProductos);
      
     return "tomarpedido";
    }
    
    @PostMapping("/tomarpedido")
    public String tomarPedido(ModelMap modelo, @RequestParam String id_cliente,@RequestParam String hora_entrega, @RequestParam String domicilio)
    {
      List<Producto> listaProductos = servProducto.listarProducto();
      modelo.put("listaProducto", listaProductos);
     return "tomarpedido";
    }
    
     @GetMapping("/realizarPedido/{id}")
    public String agregarDirec_Horario(ModelMap modelo,@PathVariable("id") String id_usuario)
    {
       
       List<String> listaHoras = servPedido.listarHoras(); //Me trae una lista con horarios de 30 min extra 
        
        modelo.put("listaHora", listaHoras);
        modelo.put("id_usuario", id_usuario);
     return "realizarPedido";
    }
    
    @GetMapping("/selectProductos")
    public String agregarProductosVista(ModelMap modelo)
    {  
        List<Producto> listaProductos = servProducto.listarProducto();
        modelo.put("listaProducto", listaProductos);
        
       return "seleccionarProducto";
    }
    
    @PostMapping("/selectProductos/{id}")
    public String agregarProductos(ModelMap modelo , @RequestParam String direccion, 
            @RequestParam String hora_entrega,@PathVariable("id") String id_usuario)
    {  
        List<Producto> listaProductos = servProducto.listarProducto();
        modelo.put("listaProducto", listaProductos);
        modelo.put("direccion", direccion);
        modelo.put("hora", hora_entrega);
        modelo.put("id_usuario", id_usuario);
        
     return "seleccionarProducto";
    }
    
    @PostMapping("/agregarPedido/{id}")
    public String agregarProductos(ModelMap modelo , @RequestParam String direccion, 
            @RequestParam String hora_entrega, @RequestParam String producto, @RequestParam String cantidad, 
            @RequestParam String boton,@PathVariable("id") String id_cliente)
    {  
        
        
        List<Producto> listaProductos = servProducto.listarProducto();
       
        servAux.agregraAuxProducto(producto, Integer.parseInt(cantidad), id_cliente);
        modelo.put("listaProducto", listaProductos);
        modelo.put("cantidad", cantidad);
        modelo.put("producto", producto);
        modelo.put("hora", hora_entrega);
        modelo.put("direccion", direccion);
        modelo.put("id_usuario",id_cliente);
        
        if(boton.equals("Agregar producto"))
        {
         modelo.put("boton", boton);
         
        }
        if(boton.equals("Finalizar"))
        {
         
          Double monto=servPedido.calcularMonto(id_cliente);
          
          Pedido pedido=servPedido.agregarPedido(id_cliente, monto, hora_entrega, direccion);
          
          List<AuxProducto> aux = servAux.traerProdAux();
          /*modelo.put("monto", monto);
      
          modelo.put("pedido", pedido);
          
          modelo.put("auxprod", aux);*/
         servDetalle.agregarDetalle(pedido);
          
         modelo.put("id_usuario",id_cliente);
         
         return "finalizarPedido";
        }
       
     return "seleccionarProducto";
    }
    
    @PostMapping("/agregar")
    public String guardar( ModelMap modelo, @RequestParam String id_cliente, 
                @RequestParam Double monto_total, @RequestParam String hora_entrega, 
                @RequestParam String lugar )
    {
		
		try {
                       servPedido.agregarPedido(id_cliente, monto_total, hora_entrega, lugar);
                       List<String> listaHoras = servPedido.listarHoras(); //Me trae una lista con horarios de 30 min extra 
        
                       modelo.put("listaHora", listaHoras);
                       
                      //modelo.put("hora", hora_entrega); Para mostrar en la vista
                       return "pedido";

		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "pedido";
		}
	}
}
