package com.accenture.challenge.controller;

import com.accenture.challenge.model.Franquicia;
import com.accenture.challenge.model.Sucursal;
import com.accenture.challenge.model.Producto;
import com.accenture.challenge.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService service;

    // 2. Agregar una nueva franquicia
    @PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(service.agregarFranquicia(franquicia));
    }

    // 3. Agregar una nueva sucursal a la franquicia
    @PostMapping("/{franquiciaId}/sucursales")
    public ResponseEntity<Franquicia> agregarSucursal(
            @PathVariable String franquiciaId, 
            @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.agregarSucursal(franquiciaId, sucursal));
    }

    // 4. Agregar un nuevo producto a la sucursal
    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Franquicia> agregarProducto(
            @PathVariable String franquiciaId,
            @PathVariable String sucursalId,
            @RequestBody Producto producto) {
        return ResponseEntity.ok(service.agregarProducto(franquiciaId, sucursalId, producto));
    }

    // 5. Eliminar un producto de una sucursal
    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Franquicia> eliminarProducto(
            @PathVariable String franquiciaId,
            @PathVariable String sucursalId,
            @PathVariable String productoId) {
        return ResponseEntity.ok(service.eliminarProducto(franquiciaId, sucursalId, productoId));
    }

    // 6. Modificar el Stock de un producto
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<Franquicia> modificarStock(
            @PathVariable String franquiciaId,
            @PathVariable String sucursalId,
            @PathVariable String productoId,
            @RequestParam int nuevoStock) {
        return ResponseEntity.ok(service.modificarStock(franquiciaId, sucursalId, productoId, nuevoStock));
    }

    // 7. Mostrar el producto que más stock tiene por sucursal para una franquicia puntual
    @GetMapping("/{franquiciaId}/max-stock")
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosMaxStock(@PathVariable String franquiciaId) {
        return ResponseEntity.ok(service.obtenerProductosMaxStock(franquiciaId));
    }

    // PLUS: Actualizar el nombre de la franquicia
    @PatchMapping("/{franquiciaId}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreFranquicia(
            @PathVariable String franquiciaId,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreFranquicia(franquiciaId, nuevoNombre));
    }

    // PLUS: Actualizar el nombre de la sucursal
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreSucursal(
            @PathVariable String franquiciaId,
            @PathVariable String sucursalId,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreSucursal(franquiciaId, sucursalId, nuevoNombre));
    }

    // PLUS: Actualizar el nombre del producto
    @PatchMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/nombre")
    public ResponseEntity<Franquicia> actualizarNombreProducto(
            @PathVariable String franquiciaId,
            @PathVariable String sucursalId,
            @PathVariable String productoId,
            @RequestParam String nuevoNombre) {
        return ResponseEntity.ok(service.actualizarNombreProducto(franquiciaId, sucursalId, productoId, nuevoNombre));
    }
}