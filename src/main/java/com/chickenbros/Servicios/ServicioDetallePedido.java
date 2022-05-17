
package com.chickenbros.Servicios;

import com.chickenbros.Entidades.AuxProducto;
import com.chickenbros.Entidades.DetallePedido;
import com.chickenbros.Entidades.Pedido;
import com.chickenbros.Entidades.Producto;
import com.chickenbros.Repositorios.RepositorioAuxProducto;
import com.chickenbros.Repositorios.RepositorioDetallePedido;
import com.chickenbros.Repositorios.RepositorioPedido;
import com.chickenbros.Repositorios.RepositorioProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioDetallePedido {
    
    @Autowired
    private RepositorioDetallePedido repoDetalle;
    
    @Autowired
    private RepositorioPedido repoPedido;
    
    @Autowired
    private RepositorioProducto repoProducto;
    @Autowired
    private RepositorioAuxProducto repoAux;
    
    
    public void agregarDetalle(Pedido pedido)
    {
       
       Producto producto=new Producto();
       Integer cantidad;
       
       List<AuxProducto> aux = repoAux.buscarPorId();
       
       for (int i=0; i<aux.size();i++) {
      //AuxProducto auxiliar : aux
          AuxProducto auxiliar=aux.get(i);
          producto=repoProducto.buscarPorId(auxiliar.getId_producto());
          cantidad=auxiliar.getCantidad();
          
         DetallePedido detPedido=new DetallePedido(pedido,producto, cantidad);
          repoDetalle.save(detPedido);
       
          
       }
       
      
    repoAux.deleteAll();
    //repoDetalle.deleteAll();
    }
    
}
