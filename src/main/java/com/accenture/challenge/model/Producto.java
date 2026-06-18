package com.accenture.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private String id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
    private String nombre;
    private int stock;
}