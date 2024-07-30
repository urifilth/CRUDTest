package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
public class ListaCompraDetalle {

    @EmbeddedId
    private ListaCompraDetalleId id;

    @ManyToOne
    @MapsId("idListaCompra")
    @JoinColumn(name = "id_lista_compra", nullable = false)
    private ListaCompra listaCompra;

    @ManyToOne
    @MapsId("idCodigoProducto")
    @JoinColumn(name = "idCodigoProducto", nullable = false)
    private Producto producto;

    private Long cantidad;

    // Getters y Setters

    public ListaCompraDetalleId getId() {
        return id;
    }

    public void setId(ListaCompraDetalleId id) {
        this.id = id;
    }

    public ListaCompra getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(ListaCompra listaCompra) {
        this.listaCompra = listaCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
