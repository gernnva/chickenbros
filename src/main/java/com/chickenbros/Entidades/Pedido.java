
package com.chickenbros.Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    private String id_pedido;

    @ManyToOne
    private Cliente cliente;

    private Double monto_total;

    @Temporal(TemporalType.TIME)
    private Date hora_entrega;


    @Temporal(TemporalType.DATE)
    private Calendar fecha;

    private Boolean lugar;
    private Integer estado;

    public Pedido() {
    }

    public Pedido(Cliente cliente, Double monto_total, Date hora_entrega, Calendar fecha, Boolean lugar, Integer estado) {
        this.cliente = cliente;
        this.monto_total = monto_total;
        this.hora_entrega = hora_entrega;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = estado;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public Date getHora_entrega() {
        return hora_entrega;
    }

    public void setHora_entrega(Date hora_entrega) {
        this.hora_entrega = hora_entrega;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Boolean getLugar() {
        return lugar;
    }

    public void setLugar(Boolean lugar) {
        this.lugar = lugar;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
}
