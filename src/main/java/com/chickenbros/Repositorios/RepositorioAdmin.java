
package com.chickenbros.Repositorios;

import com.chickenbros.Entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAdmin extends JpaRepository <Administrador, String>{
    
}
