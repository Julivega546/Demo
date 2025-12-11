package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Producto Management System")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "View a list of available productos")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a producto by Id")
    public Producto getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new producto")
    @PreAuthorize("hasRole('ADMIN')")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing producto")
    @PreAuthorize("hasRole('ADMIN')")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existing = productoService.getProductoById(id);
        existing.setNombre(producto.getNombre());
        existing.setDescripcion(producto.getDescripcion());
        existing.setPrecio(producto.getPrecio());
        return productoService.saveProducto(existing);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a producto")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }
}
