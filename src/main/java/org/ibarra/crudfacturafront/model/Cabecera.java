package org.ibarra.crudfacturafront.model;

import java.util.List;

public class Cabecera {
    
    private Long id;
    private String ci;
    private String cliente;
    private String direccion;
    private String telefono;
    private String estado;
    private String fecha;
    private Double total;
    private List<Detalle> detalles;

    // Constructor con todos los campos
    public Cabecera(Long id, String ci, String cliente, String direccion, String telefono, String estado, String fecha, Double total, List<Detalle> detalles) {
        this.id = id;
        this.ci = ci;
        this.cliente = cliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    // Constructor sin detalles para casos simplificados
    public Cabecera(String ci, String cliente, String direccion, String telefono, String estado) {
        this.ci = ci;
        this.cliente = cliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Cabecera() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
}
