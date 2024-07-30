package com.example.demo.Repository;

import com.example.demo.Entities.ListaCompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaCompraDetalleRepository extends JpaRepository<ListaCompraDetalle, Long> {
    void deleteByListaCompraId(Long id);
}
