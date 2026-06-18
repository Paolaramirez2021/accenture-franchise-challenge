package com.accenture.challenge.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "franquicias") // Esto le dice a Mongo que cree una tabla llamada franquicias
public class Franquicia {
    @Id
    private String id; // El ID principal de la franquicia
    private String nombre;
    private List<Sucursal> sucursales = new ArrayList<>();
}