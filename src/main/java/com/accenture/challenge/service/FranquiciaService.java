package com.accenture.challenge.service;

import com.accenture.challenge.model.Franquicia;
import com.accenture.challenge.model.Sucursal;
import com.accenture.challenge.model.Producto;
import com.accenture.challenge.repository.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository repository;

    // 2. Agregar una nueva franquicia
    public Franquicia agregarFranquicia(Franquicia franquicia) {
        return repository.save(franquicia);
    }

    // 3. Agregar una nueva sucursal a la franquicia
    public Franquicia agregarSucursal(String franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        franquicia.getSucursales().add(sucursal);
        return repository.save(franquicia);
    }

    // 4. Agregar un nuevo producto a la sucursal
    public Franquicia agregarProducto(String franquiciaId, String sucursalId, Producto producto) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        
        Sucursal sucursal = franquicia.getSucursales().stream()
                .filter(s -> s.getId().equals(sucursalId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        sucursal.getProductos().add(producto);
        return repository.save(franquicia);
    }

    // 5. Eliminar un producto de una sucursal
    public Franquicia eliminarProducto(String franquiciaId, String sucursalId, String productoId) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        Sucursal sucursal = franquicia.getSucursales().stream()
                .filter(s -> s.getId().equals(sucursalId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        sucursal.getProductos().removeIf(p -> p.getId().equals(productoId));
        return repository.save(franquicia);
    }

    // 6. Modificar el stock de un producto
    public Franquicia modificarStock(String franquiciaId, String sucursalId, String productoId, int nuevoStock) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        franquicia.getSucursales().stream()
                .filter(s -> s.getId().equals(sucursalId))
                .flatMap(s -> s.getProductos().stream())
                .filter(p -> p.getId().equals(productoId))
                .forEach(p -> p.setStock(nuevoStock));

        return repository.save(franquicia);
    }

    // 7. Obtener el producto con más stock por sucursal para una franquicia puntual
    public List<Map<String, Object>> obtenerProductosMaxStock(String franquiciaId) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        return franquicia.getSucursales().stream().map(sucursal -> {
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("sucursal", sucursal.getNombre());
            
            // Usamos programación funcional avanzada (Streams) para buscar el mayor
            Optional<Producto> maxProducto = sucursal.getProductos().stream()
                    .max(Comparator.comparingInt(Producto::getStock));
            
            resultado.put("producto", maxProducto.orElse(null));
            return resultado;
        }).collect(Collectors.toList());
    }

    // PLUS: Actualizar nombre de la franquicia
    public Franquicia actualizarNombreFranquicia(String franquiciaId, String nuevoNombre) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        franquicia.setNombre(nuevoNombre);
        return repository.save(franquicia);
    }

    // PLUS: Actualizar nombre de la sucursal
    public Franquicia actualizarNombreSucursal(String franquiciaId, String sucursalId, String nuevoNombre) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        franquicia.getSucursales().stream()
                .filter(s -> s.getId().equals(sucursalId))
                .findFirst()
                .ifPresent(s -> s.setNombre(nuevoNombre));

        return repository.save(franquicia);
    }

    // PLUS: Actualizar nombre del producto
    public Franquicia actualizarNombreProducto(String franquiciaId, String sucursalId, String productoId, String nuevoNombre) {
        Franquicia franquicia = repository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        franquicia.getSucursales().stream()
                .filter(s -> s.getId().equals(sucursalId))
                .flatMap(s -> s.getProductos().stream())
                .filter(p -> p.getId().equals(productoId))
                .forEach(p -> p.setNombre(nuevoNombre));

        return repository.save(franquicia);
    }
}