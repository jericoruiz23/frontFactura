/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author JR
 */
package org.ibarra.crudfacturafront.model;

import java.util.Date;

public class Detalle {

    private Integer cantidad;
    private Double subtotal;
    private String producto;
    private Double precio;
    private Date fechaRegistra;

    public Detalle(Integer cantidad, Double subtotal, String producto, Double precio, Date fechaRegistra) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
        this.precio = precio;
        this.fechaRegistra = fechaRegistra;
    }

    public Detalle() {
    }

    // Getters y Setters

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaRegistra() {
        return fechaRegistra;
    }

    public void setFechaRegistra(Date fechaRegistra) {
        this.fechaRegistra = fechaRegistra;
    }
}
