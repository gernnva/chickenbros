
package com.chickenbros.Servicios;

import com.chickenbros.Entidades.AuxProducto;
import com.chickenbros.Repositorios.RepositorioAuxProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiciosAuxProducto {
    
    
    @Autowired
    private RepositorioAuxProducto repoAux;
    
    public void agregraAuxProducto(String id_prod, Integer cantidad, String id_cliente)
    {
      AuxProducto auxPro= new AuxProducto();
      
      auxPro.setId_producto(id_prod);
      auxPro.setCantidad(cantidad);
      auxPro.setId_cliente(id_cliente);
      
      repoAux.save(auxPro);
    }
    
    public List<AuxProducto> traerProdAux()
    {
    
        return repoAux.buscarPorId();
    }
    
   
    
   /* public void eliminarAuxProductos(String id_usuario)
    {
     repoAux.eliminarPorId(id_usuario);
    }*/
}
