
package com.chickenbros.Repositorios;

import com.chickenbros.Entidades.AuxProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAuxProducto extends JpaRepository <AuxProducto, String> {
    
   
    
    @Query("SELECT p from AuxProducto p ")
    public List<AuxProducto> buscarPorId();
}
