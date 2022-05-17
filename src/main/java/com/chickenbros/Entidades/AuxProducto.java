
package com.chickenbros.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class AuxProducto implements Serializable {
   
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String id_producto;
    private Integer cantidad;
    private String id_cliente;

    public AuxProducto() {
    }

    public AuxProducto(String id_producto, Integer cantidad, String id_cliente) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_cliente = id_cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_usuaario) {
        this.id_cliente = id_usuaario;
    }
    
    
}
