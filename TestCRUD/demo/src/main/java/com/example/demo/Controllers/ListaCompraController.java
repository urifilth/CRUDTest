package com.example.demo.Controllers;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.ListaCompra;
import com.example.demo.Entities.ListaCompraDetalle;
import com.example.demo.Entities.ListaCompraDetalleId;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Repository.ListaCompraDetalleRepository;
import com.example.demo.Repository.ListaCompraRepository;
import com.example.demo.Repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lista-compra")
public class ListaCompraController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ListaCompraRepository listaCompraRepository;

    @Autowired
    private ListaCompraDetalleRepository listaCompraDetalleRepository;

    @PostMapping("/guardar")
    @Transactional
    public ResponseEntity<Void> guardarListas() {
        Cliente cliente720010 = clienteRepository.findById(720010L).orElseThrow();
        Cliente cliente710020 = clienteRepository.findById(710020L).orElseThrow();

        // Crear y guardar Lista Mensual
        ListaCompra listaMensual = new ListaCompra();
        listaMensual.setCliente(cliente720010);
        listaMensual.setNombre("Lista Mensual");
        listaMensual.setFechaRegistro(LocalDate.now());
        listaMensual.setActivo(true);
        listaCompraRepository.save(listaMensual);

        ListaCompraDetalle detalleMensual = new ListaCompraDetalle();
        detalleMensual.setId(new ListaCompraDetalleId(listaMensual.getId(), 18156));
        detalleMensual.setListaCompra(listaMensual);
        detalleMensual.setProducto(productoRepository.findById(18156L).orElseThrow());
        detalleMensual.setCantidad(5L);
        listaCompraDetalleRepository.save(detalleMensual);

        // Crear y guardar Lista Semanal
        ListaCompra listaSemanal = new ListaCompra();
        listaSemanal.setCliente(cliente710020);
        listaSemanal.setNombre("Lista Semanal");
        listaSemanal.setFechaRegistro(LocalDate.now());
        listaSemanal.setActivo(true);
        listaCompraRepository.save(listaSemanal);

        saveDetalle(listaSemanal, 10200, 5L);
        saveDetalle(listaSemanal, 25020, 6L);
        saveDetalle(listaSemanal, 10170, 1L);

        // Crear y guardar Lista Anual
        ListaCompra listaAnual = new ListaCompra();
        listaAnual.setCliente(cliente710020);
        listaAnual.setNombre("Lista Anual");
        listaAnual.setFechaRegistro(LocalDate.now());
        listaAnual.setActivo(true);
        listaCompraRepository.save(listaAnual);

        saveDetalle(listaAnual, 10280, 2L);
        saveDetalle(listaAnual, 30001, 3L);
        saveDetalle(listaAnual, 10200, 7L);
        saveDetalle(listaAnual, 25020, 2L);
        saveDetalle(listaAnual, 10170, 9L);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ListaCompra>> obtenerListasPorCliente(@PathVariable Long clienteId) {
        List<ListaCompra> listas = listaCompraRepository.findByClienteId(clienteId);
        return ResponseEntity.ok(listas);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> actualizarLista(@PathVariable Long id, @RequestBody ListaCompraDTO listaCompraDTO) {
        ListaCompra listaCompra = listaCompraRepository.findById(id).orElseThrow();
        listaCompra.setNombre(listaCompraDTO.getNombre());
        listaCompraRepository.save(listaCompra);

        listaCompraDetalleRepository.deleteByListaCompraId(id); // Eliminar antiguos detalles

        for (ListaCompraDetalleDTO detalleDTO : listaCompraDTO.getDetalles()) {
            ListaCompraDetalle detalle = new ListaCompraDetalle();
            detalle.setId(new ListaCompraDetalleId(id, Math.toIntExact(detalleDTO.getIdProducto())));
            detalle.setListaCompra(listaCompra);
            detalle.setProducto(productoRepository.findById(detalleDTO.getIdProducto()).orElseThrow());
            detalle.setCantidad(detalleDTO.getCantidad());
            listaCompraDetalleRepository.save(detalle);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarLista(@PathVariable Long id) {
        listaCompraDetalleRepository.deleteByListaCompraId(id);
        listaCompraRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private void saveDetalle(ListaCompra listaCompra, Integer idProducto, Long cantidad) {
        ListaCompraDetalle detalle = new ListaCompraDetalle();
        detalle.setId(new ListaCompraDetalleId(listaCompra.getId(), idProducto));
        detalle.setListaCompra(listaCompra);
        detalle.setProducto(productoRepository.findById(idProducto.longValue()).orElseThrow());
        detalle.setCantidad(cantidad);
        listaCompraDetalleRepository.save(detalle);
    }
}