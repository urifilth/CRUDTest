package com.example.demo.Controllers;

import java.util.List;

public class ListaCompraDTO {
    private String nombre;
    private List<ListaCompraDetalleDTO> detalles;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ListaCompraDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ListaCompraDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
