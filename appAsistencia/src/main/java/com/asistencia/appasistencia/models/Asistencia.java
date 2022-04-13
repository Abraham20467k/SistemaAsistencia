package com.asistencia.appasistencia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate fechaIngreso;

    @Column(name="ingreso_confirmado")
    private Boolean ingresoConfirmado;

    @Column(name="salida_confirmado")
    private Boolean salidaConfirmado;


    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

}
