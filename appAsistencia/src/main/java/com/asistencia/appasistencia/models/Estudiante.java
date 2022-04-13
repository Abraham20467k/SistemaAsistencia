package com.asistencia.appasistencia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String nombre;

    private String apellidos;

    private String codigo;

    //hibernateLazyInitializer y handelr que se generar en el json  y que no se repita
    @OneToMany(mappedBy = "estudiante")   // el nombre corresponde al nombre del objeto de la otra tabla
    @JsonIgnoreProperties({"estudiante","hibernateLazyInitializer","handler"})
    private List<Asistencia> asistencia;


}
