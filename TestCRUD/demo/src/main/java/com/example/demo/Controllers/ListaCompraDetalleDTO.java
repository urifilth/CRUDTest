package com.example.demo.Controllers;

public class ListaCompraDetalleDTO {
    private Integer idProducto;
    private Long cantidad;

    public Long getIdProducto() {
        return Long.valueOf(idProducto);
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
