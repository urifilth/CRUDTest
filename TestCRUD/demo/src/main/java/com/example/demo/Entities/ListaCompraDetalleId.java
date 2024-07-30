package com.example.demo.Entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListaCompraDetalleId implements Serializable {
    private Integer idListaCompra;
    private Integer idCodigoProducto;

    public ListaCompraDetalleId(Long id, int i) {
    }

    public Integer getIdListaCompra() {
        return idListaCompra;
    }

    public void setIdListaCompra(Integer idListaCompra) {
        this.idListaCompra = idListaCompra;
    }

    public Integer getIdCodigoProducto() {
        return idCodigoProducto;
    }

    public void setIdCodigoProducto(Integer idCodigoProducto) {
        this.idCodigoProducto = idCodigoProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaCompraDetalleId that = (ListaCompraDetalleId) o;
        return Objects.equals(idListaCompra, that.idListaCompra) && Objects.equals(idCodigoProducto, that.idCodigoProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idListaCompra, idCodigoProducto);
    }
}
