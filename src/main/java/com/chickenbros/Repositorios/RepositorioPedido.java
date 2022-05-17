
package com.chickenbros.Repositorios;

import com.chickenbros.Entidades.Cliente;
import com.chickenbros.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioPedido extends JpaRepository <Pedido, String>  {
    
    /*
       SELECT nombre
    FROM producto AS p
       INNER JOIN detalle_pedido AS d ON producto_id_producto = id_producto
       INNER JOIN pedido as pd ON pedido_id_pedido = id_pedido;
    */
}
