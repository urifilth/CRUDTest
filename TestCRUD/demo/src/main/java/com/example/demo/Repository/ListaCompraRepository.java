package com.example.demo.Repository;

import com.example.demo.Entities.ListaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long> {
        List<ListaCompra> findByClienteId(Long idCliente);
        //    Optional<ListaCompra> findByIdAndClientId( Long listaId, Long clienteID );
}
