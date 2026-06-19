package com.accenture.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    private String id;
    private String nombre;
    private List<Producto> productos = new ArrayList<>();
}