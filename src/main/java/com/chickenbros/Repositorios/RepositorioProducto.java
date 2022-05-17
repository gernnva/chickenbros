
package com.chickenbros.Repositorios;

import com.chickenbros.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, String>{
    
   @Query("SELECT precio FROM Producto p WHERE p.id_producto = :id")
    public Integer buscarPrecioPr(@Param("id") String id);
    
    @Query("SELECT p FROM Producto p WHERE p.id_producto = :id")
    public Producto buscarPorId(@Param("id") String id);
    
}
