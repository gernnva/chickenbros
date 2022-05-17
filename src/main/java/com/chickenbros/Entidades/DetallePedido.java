
package com.chickenbros.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class DetallePedido implements Serializable {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    private String id_detalle;
    
    @ManyToOne
    private Pedido pedido;
    
    @ManyToOne
    private Producto producto;
    
    private Integer cant_producto;

    public DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, Integer cant_producto) {
        this.pedido = pedido;
        this.producto = producto;
        this.cant_producto = cant_producto;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCant_producto() {
        return cant_producto;
    }

    public void setCant_producto(Integer cant_producto) {
        this.cant_producto = cant_producto;
    }
}
